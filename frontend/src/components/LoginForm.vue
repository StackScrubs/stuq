<template>
  <form class="box" @submit.prevent="onSubmit">
    <BaseInput
      id="email"
      label="Email"
      v-model="email"
      type="email"
      data-testid="email-input"
      :error="errors.email"
    />
    <BaseInput
      label="Password"
      v-model="password"
      type="password"
      data-testid="password-input"
      :error="errors.password"
    />
    <button type="submit" class="submit-button" data-testid="login-button">Submit</button>
    <label>{{ failedLoginMessage }}</label>
  </form>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useField, useForm } from "vee-validate";
import { object, string } from "yup"; 

import { InvalidCredentialsError } from "../types/UserCredentials";
import store from "../store";
import BaseInput from "./BaseInput.vue";
import { mapActions } from "vuex";
import router from "@/router";

export default defineComponent({
    components: { BaseInput },
    name: "LoginComponent",
    data: () => {
        return {
            failedLoginMessage: "",
        };
    },
    methods: {
        ...mapActions("Authentication", [
            "login", 
            "logout"
        ]),
        async onSubmit() {
            await this.submit().then(async (userCredentials) => {
                if (userCredentials !== undefined) {
                    const email = userCredentials.email;
                    const password = userCredentials.password;
                    try {
                        await store.dispatch("login", { email, password });
                        router.push("/queuing")
                    //TODO: If OK => Route to home/queues-page (not created yet)
                    } catch (e) {
                        if (e instanceof InvalidCredentialsError) {
                            this.failedLoginMessage = "Ugyldig brukernavn eller passord";
                        }
                    }                
                }
            })
        },
    },
    setup() {
        const validationSchema = object({
            email: string().email("ugyldig epost adresse").required("epost adresse er påkrevd"),
            password: string().required("passord er påkrevd")
        });

        const { handleSubmit, errors } = useForm({
            validationSchema,
            initialValues: {
                email: "",
                password: "",
            },
        });

        const { value: email } = useField("email");
        const { value: password } = useField("password");

        const submit =  handleSubmit((values) => {
            return values;
        });

        return {
            validationSchema,
            errors,
            email,
            password,
            submit,
        }
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

.submit-button:hover {
  background-color: #5656ff;
  cursor: pointer;
}

:deep(.input-interactable) {
  margin: 10px;
  padding: 10px 5px;
}

:deep(label) {
  margin: 10px 0px 0px 0px;
  align-self: flex-start;
  text-align: left;
}

@media screen and (min-width: 600px) {
    .box {
      width: 400px;
      max-width: 400px;
    }

    :deep(.input-interactable) {
      width: 400px
    }
}
</style>
