<template>
  <input
    v-bind="$attrs"
    :checked="modelValue"
    type="checkbox"
    :id="uid"
    @change="$emit('update:modelValue', $event.target.checked)"
    class="field"
    :aria-describedby="error ? `${uid}-error` : null"
    :aria-invalid="error ? true : null"
  />
  <label :for="uid" v-if="label">
    {{ label }}
  </label>
  <p
    v-if="error"
    class="error-message"
    :id="`${uid}-error`"
    aria-live="assertive"
  >
    {{ error }}
  </p>
</template>

<script langs="ts">
import { uid } from "@/uid";

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
        uid() {
            return uid();
        },
    },
};
</script>
