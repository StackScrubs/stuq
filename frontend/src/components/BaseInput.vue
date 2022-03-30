<template>
  <label v-if="label">{{ label }}</label>
  <input
    v-bind="$attrs"
    :value="modelValue"
    :placeholder="label"
    @input="$emit('update:modelValue', $event.target.value)"
    class="field"
    :aria-invalid="error ? true : null"
  />
  <p v-if="error" class="error-message" aria-live="assertive">
    {{ error }}
  </p>
</template>

<script lang="ts">
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
};
</script>

<style scoped>
label {
  display: inline-block;
  width: 60px;
  text-align: right;
}

p {
  color: #b40000;
}
</style>
