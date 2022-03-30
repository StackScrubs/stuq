<template>
  <textarea
    :id="uuid"
    v-bind="$attrs"
    :placeholder="label"
    @input="$emit('update:modelValue', $event.target.value)"
    class="text-field"
    :aria-describedby="error ? `${uuid}-error` : null"
    :aria-invalid="error ? true : null"
  ></textarea>
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
      type: String,
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
p {
  color: #b40000;
}
</style>
