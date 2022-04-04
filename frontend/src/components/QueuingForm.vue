<template>
    <div class="box">
        <form class="box" @submit.prevent="onSubmit">            
            <label>Øvinger</label>
            <div class="checkbox-list-options" v-for="(assignment, index) in assignments" :key="index" >
                    <BaseCheckbox :label="assignment.name" v-model="assignment.available" />
            </div>

            <label>Type</label>
            <div class="radio-list-options" v-for="(type, key) in queuingType" :key="key" >
                <BaseRadio name="queueform" :label="type.name" v-model="type.value"/>
            </div>

            <label>Melding</label>
            <BaseTextArea />

            <button type="submit" class="submit-button" data-testid="queuing-button">Bekreft</button>
            <label>{{ failedEnqueueMessage }}</label>
        </form>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import BaseCheckbox from "@/components/BaseCheckbox.vue"
import BaseRadio from "@/components/BaseRadio.vue"
import BaseTextArea from "@/components/BaseTextArea.vue"
import { Assignment } from "@/types/Assignment"
import { QueueNotFoundError, QueueType } from "@/types/QueueType"
import { Subject, SubjectNotFoundError } from "@/types/Subject"

import { getQueueTypeAsText } from "@/utils/utils"
import { enqueue, getAssignments, getSubmissions } from "@/service/QueingService";
import { object, string, array, number } from "yup";
import { useField, useForm } from "vee-validate";
import store from "@/store";
import { mapGetters } from "vuex";

export default defineComponent({
    name: "QueuingForm",
    components: { BaseCheckbox, BaseRadio, BaseTextArea },
    props: {
        subject: {
            type: Subject,
        },
    },
    data: (): {
        failedEnqueueMessage: string,
        assignments: Array<{available: boolean, assignment: Assignment}>
        queueTypes: QueueType | undefined
        message: string } => {
        return {
            failedEnqueueMessage: "",
            assignments: [],
            queueTypes: undefined,
            message: ""
        }
    },
    computed: {
        queuingType() {
            return Object.values(QueueType)
                .filter(key => typeof key === "number")
                .map(key => {
                    if (typeof key !== "string") {
                        return { value: key, name: getQueueTypeAsText(key)}
                    }
                })
        }
    },
    methods: {
        ...mapGetters("Authentication", [
            "session"
        ]),
        async onsubmit() {
            //Gather all checked submission choices
            await this.submit().then(async (submissionData) => {
                if (submissionData !== undefined) {
                    if (submissionData.pickedAssigments !== undefined && submissionData.queueType) {
                        const assignments = submissionData.pickedAssigments.filter(v => v !== undefined)
                        const queueType = submissionData.queueType
                        try {
                            await enqueue(store.getters.session, this.subject, assignments, queueType);
                        } catch (e) {
                            if (e instanceof SubjectNotFoundError) {
                                this.failedEnqueueMessage = "Fant ikke ønsket emne";
                            }
                            // if (e instanceof QueueNotFoundError) {
                            //     this.failedEnqueueMessage = "Fant ikke kø for ønsket emne";
                            // }
                        }
                    }
                }
            })
        }
    },

    async mounted() {
        try {
            const assignments = await getAssignments(this.subject)
            const submissions = await getSubmissions(this.subject)
            //If submission have an assignment then mark said assignment as done
            //Set assignments data 
            const result = assignments?.map((v) => {
                return { available: (submissions?.find((submission) => submission.id.assignment.id === v.id && submission.isApproved) !== undefined) ? true : false,
                    assignment: {id: v.id, name: v.name}
                }
            });
            if (result !== undefined) {
                this.assignments = result;
            } else {
                //Handle undefined behaviour
            }
        } catch (e) {
            //Handle error
        }
    },
    setup() {
        const validationSchema = object({
            pickedAssigments: array().of(
                number()
            ).required("Må velge minst én øving/oblig"),
            queueType: string().required("queue type is required"),
        });

        const { handleSubmit, errors } = useForm({
            validationSchema,
            initialValues: {
                pickedAssigments: undefined,
                queueType: undefined,
            },
        });

        const { value: pickedAssigments } = useField("pickedAssigments");
        const { value: queueType } = useField("queueType");

        const submit =  handleSubmit((values) => {
            return values;
        });

        return {
            validationSchema,
            errors,
            pickedAssigments,
            queueType,
            submit,
        }
    },
})
</script>

<style lang="scss" scoped>
.box { 
  max-width: 400px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.submit-button {
  background-color: rgb(70, 70, 255);
  border: none;
  border-radius: 20px;
  color: white;
  padding: 15px 32px;
  margin: 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

.submit-button:hover {
  background-color: #5656ff;
  cursor: pointer;
}

.box label {
    align-self:flex-start;
    text-decoration-line: underline;
    margin: 20px 10px 5px;
}

:deep(.checkbox-interactable) {
    background: white;
    border-radius: 10px;
    border: blue 2px solid;
}

:deep(.radio-container) {
    display: flex;
    flex-direction: row;
}

:deep(.radio-interactable) {
    margin: 5px;
    border: blue 2px solid;
}

:deep(.radio-label) {
    margin: 5px;
}

:deep(.text-area-interactable) {
    width: 300px;
    height: 100px;
    border-radius: 5px;
    border: blue 2px solid;
}

@media screen and (max-width: 500px) {
    .box { 
        max-width: 200px;
    }

    :deep(.text-area-interactable) {
        width: 200px;
        height: 50px;
        border-radius: 5px;
        border: blue 2px solid;
    }
}
</style>
