<template>
  <label class="input-label" :for="uid" v-if="label">{{ label }}</label>
  <input
    :id="uid"
    v-bind="$attrs"
    :value="modelValue"
    :placeholder="label"
    @input="$emit('update:modelValue', $event.target.value)"
    class="input-interactable"
    :aria-describedby="error ? `${uid}-error` : null"
    :aria-invalid="error ? true : null"
  />
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
label {
  display: inline-block;
  width: 60px;
}

p {
  color: #b40000;
}
</style>
