<template>
<div class="base-input-container">
    <input
        type="radio"
        :name="name"
        :checked="modelValue === value"
        :value="value"
        v-bind="$attrs"
        @change="$emit('update:modelValue', value)"
    />
    <label v-if="label">{{ label }}</label>
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
.base-input-container {
    display: flex;
    flex-direction: row;
}

input {
    margin: 0px;
    vertical-align: middle;
}

label {
    display: table-cell;
    vertical-align: middle;
}
</style>
