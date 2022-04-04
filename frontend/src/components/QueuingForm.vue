<template>
    <div class="box">
        <form class="box" @submit.prevent="submit">            
            <label>Øvinger</label>
            <div class="checkbox-list-options" v-for="(doneAssignment, index) in doneAssignments" :key="index"  >
                    <BaseCheckbox :label="doneAssignment.name" v-model="doneMarker" />
            </div>
            <div class="checkbox-list-options" v-for="(pickableAssignment, index) in pickableAssignments" :key="index" >
                    <BaseCheckbox :label="pickableAssignment.assignment.name" v-model="pickableAssignment.picked" />
            </div>

            <label>Type</label>
            <div class="radio-list-options" v-for="(queueType, key) in queueTypes" :key="key" >
                <BaseRadio name="queueform" :label="queueType.name" :value="queueType.value" v-model="selectedQueueType" />
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
import { QueueType } from "@/types/QueueType"
import { Subject, SubjectNotFoundError } from "@/types/Subject"

import { getQueueTypeAsText } from "@/utils/utils"
import { enqueue, getSubmissions } from "@/service/SubmissionService";
import { getSubjectAssignments } from "@/service/SubjectService"
import { Submission } from "@/types/Submission";

export default defineComponent({
    name: "QueuingForm",
    components: { BaseCheckbox, BaseRadio, BaseTextArea },
    data: (): {
        subject: Subject
        failedEnqueueMessage: string
        doneAssignments: Array<Assignment>
        doneMarker: boolean
        pickableAssignments: Array<{picked: boolean, assignment: Assignment}>
        queueTypes: Array<({ value: QueueType, name: string, modelValue: QueueType})>
        selectedQueueType: QueueType,
        message: string } => {
        return {
            subject: new Subject(2021, "V", "IDATT2105", "Full-stack"), //Test data
            failedEnqueueMessage: "",
            doneAssignments: [ //LOCAL DATA: Can be remnoved when getAssignements and getSubmissions properly works with backend
                new Assignment("1", "oblig 1", new Subject(2021, "V", "IDATT2105", "Full-stack")),
                new Assignment("2", "oblig 2", new Subject(2021, "V", "IDATT2105", "Full-stack"))
            ],
            pickableAssignments: [ //LOCAL DATA: Can be remnoved when getAssignements and getSubmissions properly works with backend
                {picked: false, assignment: new Assignment("3", "oblig 3", new Subject(2021, "V", "IDATT2105", "Full-stack"))},
                {picked: false, assignment: new Assignment("4", "oblig 4", new Subject(2021, "V", "IDATT2105", "Full-stack"))}
            ],
            doneMarker: true,
            queueTypes: Object.values(QueueType)
                .filter(key => typeof key === "number")
                .map(key => { key = key as number; return { value: key, name: getQueueTypeAsText(key), modelValue: key }}),
            selectedQueueType: QueueType.Submission,
            message: ""
        }
    },
    methods: {
        async submit() {
            const assignments = this.pickableAssignments.filter(v => v.picked).map(v => v.assignment)
            const queueType = this.selectedQueueType
            if (assignments.length > 0) {
                try {
                    await enqueue(this.subject, assignments, queueType, this.message);
                } catch (e) {
                    if (e instanceof SubjectNotFoundError) {
                        this.failedEnqueueMessage = "Fant ikke ønsket emne";
                    }
                }
            } else {
                this.failedEnqueueMessage = "Velg øvinger/obliger samt køtype for å gå videre"
            }
        }
    },

    //The following code will work if getSubjectAssignments() and getSubmissions() works with the backend endpoints.
    //Since it doesn't fully work yet, and we run out of time, we substitute it here with local data.

    // async mounted() {
    //     try {
    //         const assignments: Array<Assignment> = await getSubjectAssignments(this.subject.termYear, this.subject.termPeriod, this.subject.code)
    //         const submissions: Array<Submission> = await getSubmissions()
    //         const result = assignments.map((assignment) => {
    //             return { available: (submissions.find((submission) => submission.id.assignment.id === assignment.id && submission.isApproved) !== undefined) ? true : false,
    //                 assignment: assignment
    //             };
    //         });
    //         let temp: Array<{picked: boolean, assignment: Assignment}> = []
    //         result.forEach((pickableAssignment) => {
    //             if (pickableAssignment.available) {
    //                 temp.push({ picked: false, assignment: pickableAssignment.assignment })
    //             } else {
    //                 this.doneAssignments.push(pickableAssignment.assignment)
    //             }
    //         });
    //         this.pickableAssignments = temp
    //     } catch (e) {
    //         //Handle Not_found_error from API response
    //     }
    // },
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
