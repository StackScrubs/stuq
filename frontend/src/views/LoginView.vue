<template>
  <div class="box">
    <img src="@/assets/stuq-big.png" alt="logo" />
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

img {
  max-width: 50vw;
}

@media screen and (min-width: 600px) {
  img {
    max-width: 500px;
  }
}
</style>
