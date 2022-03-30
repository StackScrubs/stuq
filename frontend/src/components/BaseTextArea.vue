<template>
  <textarea
    v-bind="$attrs"
    :placeholder="label"
    @input="$emit('update:modelValue', $event.target.value)"
    class="text-field"
    :aria-invalid="error ? true : null"
  ></textarea>
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
      type: String,
      default: "",
    },
    error: {
      type: String,
      default: "",
    },
  },
};
</script>

<style lang="scss" scoped>
p {
  color: #b40000;
}
</style>
