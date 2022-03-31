<template>
  <form class="box" action="" v-on="submit">
    <BaseInput
      id="email"
      label="Email"
      v-model="email"
      type="email"
      class="login-form-item base-input-field"
      placeholder="ola@nordman.no"
      data-testid="email-input"
    />
    <BaseInput
      label="Password"
      v-model="password"
      type="password"
      class="login-form-item base-input-field"
      data-testid="password-input"
    />
    <button v-on:click="submit" class="login-form-item submit-button" data-testid="login-button">Submit</button>
    <label>{{ failedLoginMessage }}</label>
  </form>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { UserCredentials, InvalidCredentialsError } from "../types/UserCredentials";

import store from "../store";
import BaseInput from "./BaseInput.vue";

export default defineComponent({
    components: { BaseInput },
    name: "LoginComponent",
    prop: {
        failedLoginMessage: "",
    },
    data: (): {credentials: UserCredentials, failedLoginMessage: string } => {
        return {
            credentials: { email: "", password: ""},
            failedLoginMessage: "",
        };
    },
    methods: {
        async submit() {
            const email = this.credentials.email;
            const password = this.credentials.password;
            if (email && password) {
                try {
                    await store.dispatch("login", { email, password });
                //TODO: If OK => Route to home/queues-page (not created yet)
                } catch (e) {
                    if (e instanceof InvalidCredentialsError) {
                        this.failedLoginMessage = "Ugyldig brukernavn eller passord";
                    }
                }
            }
        },
    },
});
</script>

<style lang="scss" scoped>
.box {
  max-width: 200px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
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

label {
  color: red;
}
</style>

<style lang="scss">
.base-input-label {
  align-self: flex-start;
  text-align: left;
}

.base-input-field {
  margin: 10px;
  padding: 10px 5px;
}

.login-form-item {
  margin: 0px 0px 20px 0px;
}
</style>