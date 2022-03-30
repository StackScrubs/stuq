<template>
  <form class="box" action="" v-on="submit">
    <input
      :value="email"
      type="email"
      class="input"
      placeholder="ola@nordman.no"
      data-testid="email-input"
    />
    <input
      :value="password"
      type="text"
      class="input"
      placeholder="$m3llycat"
      data-testid="password-input"
    />
    <button v-on:click="submit" class="submit-button">Submit</button>
  </form>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { User } from "../types/User";
import store from "../store";

export default defineComponent({
    name: "LoginComponent",
    data: (): User => {
        return {
            email: "",
            password: "",
        };
    },
    methods: {
        submit() {
            const email = this.email;
            const password = this.password;
            if (this.email && this.password) {
                store.dispatch("login", { email, password });
                //If OK => Route to home page
                //If not OK => handle error
            }
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
