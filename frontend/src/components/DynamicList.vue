<template>
    <ul :class="$attrs.class">
        <li v-for="(item, i) in list.slice().reverse()" :key="i">
            {{ item }}
        </li>
    </ul>
</template>

<script>
export default {
    props: {
        list: {
            type: Array,
            required: true
        }
    }
}
</script>

<style>

</style>