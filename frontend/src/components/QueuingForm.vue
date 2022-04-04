<template>
    <div class="box">
        <form class="box" @submit.prevent="onSubmit">            
            <label>Øvinger</label>
            <div class="checkbox-list-options" v-for="(doneAssignment, index) in doneAssignments" :key="index" >
                    <BaseCheckbox :label="doneAssignment.name" v-model="doneMarker" />
            </div>
            <div class="checkbox-list-options" v-for="(pickableAssignment, index) in pickedAssignments" :key="index" >
                    <BaseCheckbox :label="pickableAssignment.assignment.name" v-model="pickableAssignment.picked" />
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
        failedEnqueueMessage: string
        doneAssignments: Array<Assignment>
        doneMarker: boolean
        //pickableAssignments: Array<{picked: boolean, assignment: Assignment}>
        queueTypes: QueueType | undefined
        message: string } => {
        return {
            failedEnqueueMessage: "",
            doneAssignments: [
                new Assignment("1", "oblig 1", new Subject(2021, "V", "IDATT2105", "Full-stack")),
                new Assignment("2", "oblig 2", new Subject(2021, "V", "IDATT2105", "Full-stack"))
            ],
            doneMarker: true,
            // pickableAssignments: [
            //     {picked: false, assignment: new Assignment("3", "oblig 3", new Subject(2021, "V", "IDATT2105", "Full-stack"))},
            //     {picked: false, assignment: new Assignment("4", "oblig 4", new Subject(2021, "V", "IDATT2105", "Full-stack"))}
            // ],
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
                    if (submissionData.pickedAssignments !== undefined && submissionData.queueType) {
                        const assignments = submissionData.pickedAssignments.filter(v => v !== undefined)
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
            if (assignments !== undefined && submissions !== undefined) {
                const result = assignments.map((assignment) => {
                    return { available: (submissions.find((submission) => submission.id.assignment.id === assignment.id && submission.isApproved) !== undefined) ? true : false,
                        assignment: assignment
                    };
                });
                let temp: Array<{picked: boolean, assignment: Assignment}> = []
                result.forEach((pickableAssignment) => {
                    if (pickableAssignment.available) {
                        temp.push({ picked: false, assignment: pickableAssignment.assignment })
                    } else {
                        this.doneAssignments.push(pickableAssignment.assignment)
                    }
                });
                this.pickedAssignments = temp
                // const pickedAssigments = assignments?.filter((assignment) => submissions?.find((submission) => submission.id.assignment.id === assignment.id && submission.isApproved) !== undefined).map((assignment) => {
                //     return { picked: false,
                //         assignment: assignment
                //     }
                // })
                // this.pickableAssignments = pickedAssigments;

            } else {
                //Handle "undefined" behaviour
            }
        } catch (e) {
            //Handle error
        }
    },
    setup() {
        const validationSchema = object({
            pickedAssignments: array().test({
                name: "pickedAssignments_test",
                exclusive: true,
                message: "Velg minst en øving/oblig",
                test: (v) => {
                    return (v === undefined) ? false : v.length > 0
                }
            }),
            queueType: string().required("queue type is required"),
        });

        const { handleSubmit, errors } = useForm({
            validationSchema,
            initialValues: {
                pickedAssignments: [],
                queueType: "",
            },
        });

        const { value: pickedAssignments } = useField("pickedAssignments");
        const { value: queueType } = useField("queueType");

        const submit =  handleSubmit((values) => {
            return values;
        });

        return {
            validationSchema,
            errors,
            pickedAssignments,
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
