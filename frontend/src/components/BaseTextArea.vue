<template>
  <textarea
    :id="uid"
    v-bind="$attrs"
    :placeholder="label"
    @input="$emit('update:modelValue', $event.target.value)"
    class="text-field"
    :aria-describedby="error ? `${uid}-error` : null"
    :aria-invalid="error ? true : null"
  ></textarea>
  <p
    v-if="error"
    class="error-message"
    :id="`${uid}-error`"
    aria-live="assertive"
  >
    {{ error }}
  </p>
</template>

<script lang="ts">
import { uid } from "@/uid";

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
        uid() {
            return uid();
        },
    },
};
</script>

<style lang="scss" scoped>
p {
  color: #b40000;
}
</style>
