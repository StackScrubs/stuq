<template>
  <label :for="uuid" v-if="label">{{ label }}</label>
  <select
    v-bind="$attrs"
    :id="uuid"
    :value="modelValue"
    @change="$emit('update:modelValue', $event.target.value)"
    class="field"
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
import { v4 as uuidv4 } from "uuid";

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
    uuid() {
      return uuidv4();
    },
  },
};
</script>
