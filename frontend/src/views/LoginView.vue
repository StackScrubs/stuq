<template>
  <div class="box">
    <img src="../assets/logo.png" alt="logo" />
    <LoginForm />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import LoginForm from "@/components/LoginForm.vue";

export default defineComponent({
    name: "LoginView",
    components: {
        LoginForm,
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