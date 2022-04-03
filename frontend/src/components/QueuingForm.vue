<template>
    <div class="box">
        <form class="box" @submit.prevent="onSubmit">
            <div>
                
            </div>
            <label>Lokasjon</label>
            <div class="list-options">
                <BaseSelect :options="campuses"/>
                <BaseSelect :options="buildings"/>
                <BaseSelect :options="rooms"/>
                <BaseSelect :options="tables"/>
            </div>
            
            <label>Øvinger</label>
            <div class="list-options" v-for="(assignment, index) in assignments" :key="index" >
                    <BaseCheckbox :label="assignment.name" v-model="assignment.available" />
            </div>

            <label>Type</label>
            <div class="list-options" v-for="(type, key) in queuingType" :key="key" >
                <BaseRadio name="queueform" :label="type.name" v-model="type.value"/>
            </div>

            <label>Melding</label>
            <BaseTextArea />

            <button type="submit" class="submit-button" data-testid="queuing-button">Bekreft</button>
        </form>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import BaseSelect from "@/components/BaseSelect.vue"
import BaseCheckbox from "@/components/BaseCheckbox.vue"
import BaseRadio from "@/components/BaseRadio.vue"
import BaseTextArea from "@/components/BaseTextArea.vue"
import { Campus, Building, Room, Table } from "@/types/Location"
import { Assignment } from "@/types/Assignment"
import { QueueType } from "@/types/QueueType"
import { getQueueTypeAsText } from "@/utils/utils"

export default defineComponent({
    name: "QueuingForm",
    components: { BaseSelect, BaseCheckbox, BaseRadio, BaseTextArea },
    data: (): {
        campuses: Array<Campus>, 
        buildings: Array<Building>, 
        rooms: Array<Room>, 
        tables: Array<Table>
        assignments: Array<Assignment>
        queueTypes: QueueType | undefined
        message: string } => {
        return {
            campuses: [],
            buildings: [],
            rooms: [],
            tables: [],
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
    }
})
</script>

<style lang="scss" scoped>
.box { 
  max-width: 200px;
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

.list-options, ul{
    display: flex;
    flex-direction: row;
}

:deep(.select-interactable) {
    background: white;
    border-radius: 10px;
    border: blue 2px solid;
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
    border-radius: 5px;
    border: blue 2px solid;
}
</style>
