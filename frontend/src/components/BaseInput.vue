<template>
  <label :for="uuid" v-if="label">{{ label }}</label>
  <input
    :id="uuid"
    v-bind="$attrs"
    :value="modelValue"
    :placeholder="label"
    @input="$emit('update:modelValue', $event.target.value)"
    class="field"
    :aria-describedby="error ? `${uuid}-error` : null"
    :aria-invalid="error ? true : null"
  />
  <p
    v-if="error"
    class="error-message"
    :id="`${uuid}-error`"
    aria-live="assertive"
  >
    {{ error }}
  </p>
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

<style lang="scss" scoped>
label {
  display: inline-block;
  width: 60px;
  text-align: right;
}

p {
  color: #b40000;
}
</style>
