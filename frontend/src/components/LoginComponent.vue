<template>
  <div class="box">
    <img src="../assets/logo.png" alt="logo" />
    <form class="box" action="" v-on="submit">
      <input
        :value="email"
        type="text"
        class="input"
        placeholder="ola@nordman.no"
      />
      <input
        :value="password"
        type="text"
        class="input"
        placeholder="$m3llycat"
      />
      <button class="submit-button" type="submit">Submit</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { Login } from "../types/Login";
import LoginService from "../service/LoginService";

export default defineComponent({
  name: "LoginComponent",
  data: (): Login => {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async submit() {
      let response = await LoginService.doLogin(this);
      //If OK => Route to home page
      //If not OK => handle error
    },
  },
});
</script>

<style lang="scss" scoped>
.box {
  border: 2px solid black;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.input {
  margin: 10px;
}

.submit-button {
  background-color: rgb(70, 70, 255);
  border: none;
  border-radius: 20px;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>
