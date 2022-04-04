<template>
  <div class="box">
    <QueuingForm />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import QueuingForm from "@/components/QueuingForm.vue";

export default defineComponent({
    name: "QueuingView",
    components: {
        QueuingForm,
    },
});
</script>


<style lang="scss" scoped>
.box {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
