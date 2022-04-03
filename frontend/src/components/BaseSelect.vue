<template>
  <label class="select-label" :for="uid" v-if="label">{{ label }}</label>
  <select
    v-bind="$attrs"
    :id="uid"
    :value="modelValue"
    @change="$emit('update:modelValue', $event.target.value)"
    class="select-interactable"
  >
    <option
      v-for="option in options"
      :value="option"
      :key="option"
      :selected="option === modelValue"
    >
      {{ option }}
    </option>
  </select>
</template>

<script lang="ts">
import { uid } from "@/utils/uid";

export default {
    props: {
        label: {
            type: String,
            default: "",
        },
        modelValue: {
            type: [String, Number],
            default: "",
        },
        options: {
            type: Array,
            required: true,
        },
    },
    computed: {
        uid() {
            return uid();
        },
    },
};
</script>
