<template>
  <input
    v-bind="$attrs"
    :checked="modelValue"
    type="checkbox"
    :id="uuid"
    @change="$emit('update:modelValue', $event.target.checked)"
    class="field"
    :aria-describedby="error ? `${uuid}-error` : null"
    :aria-invalid="error ? true : null"
  />
  <label :for="uuid" v-if="label">
    {{ label }}
  </label>
  <p
    v-if="error"
    class="error-message"
    :id="`${uuid}-error`"
    aria-live="assertive"
  >
    {{ error }}
  </p>
</template>

<script langs="ts">
import { v4 as uuidv4 } from "uuid";

export default {
  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: Boolean,
    },
    error: {
      type: String,
      default: "",
    },
  },
  computed: {
    uuid() {
      return uuidv4();
    },
  },
};
</script>
