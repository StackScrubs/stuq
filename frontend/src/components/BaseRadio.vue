<template>
<div class="radio-container">
    <input
        class="radio-interactable"
        type="radio"
        :name="name"
        :checked="modelValue === value"
        :value="value"
        v-bind="$attrs"
        @change="$emit('update:modelValue', value)"
    />
    <label class="radio-label" v-if="label">{{ label }}</label>
</div>
  
</template>

<script>
export default {
    props: {
        name: {
            type: String,
            default: "",
        },
        label: {
            type: String,
            default: ""
        },
        modelValue: {
            type: [String, Number],
            default: ""
        },
        value: {
            type: [String, Number],
            required: true
        }
    }
}
</script>

<style lang="scss" scoped>
input {
    margin: 0px;
    vertical-align: middle;
}

label {
    display: table-cell;
    vertical-align: middle;
}
</style>
