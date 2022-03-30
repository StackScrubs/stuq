<template>
    <textarea
        v-bind="$attrs"
        :placeholder="label"
        @input="$emit('update:modelValue', $event.target.value)"
        class="text-field"
        :id="uuid"
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

<script>
import UniqueID from '../features/UniqueID'
export default {
  props: {
    label: {
      type: String,
      default: ''
    },
    modelValue: {
      type: String,
      default: ''
    },
    error: {
      type: String,
      default: ''
    }
  },
  setup () {
    const uuid = UniqueID().getID()
    return {
      uuid
    }
  }
}
</script>