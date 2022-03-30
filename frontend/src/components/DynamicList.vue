<template>
  <ul :class="$attrs.class">
    <li v-for="(item, i) in listToRender" :key="i">
      {{ item }}
    </li>
  </ul>
</template>

<script langs="ts">
export default {
    props: {
        list: {
            type: Array,
            required: true,
        },
        reversed: {
            type: Boolean,
            default: false,
        },
    },
    computed: {
        listToRender() {
            let list = this.list.slice();

            return this.reversed ? list.reverse() : list;
        },
    },
};
</script>
