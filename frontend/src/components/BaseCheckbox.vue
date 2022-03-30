<template>
  <input
    v-bind="$attrs"
    :checked="modelValue"
    type="checkbox"
    @change="$emit('update:modelValue', $event.target.checked)"
    class="field"
  />
  <label v-if="label">
    {{ label }}
  </label>
  <p v-if="error" class="error-message" aria-live="assertive">
    {{ error }}
  </p>
</template>

<script langs="ts">
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
};
</script>
