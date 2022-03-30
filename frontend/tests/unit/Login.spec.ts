import { expect } from "chai";
import { flushPromises, shallowMount, VueWrapper } from "@vue/test-utils";
import LoginForm from "@/views/LoginForm.vue";
import store from "@/store/index.js";
import router from "@/router/index.js";

let wrapper: VueWrapper<any>;

describe("LoginForm.vue", () => {
  beforeEach(() => {
    wrapper = shallowMount(LoginForm, {
      global: {
        mocks: {
          $store: store,
          $router: router,
        },
      },
    });
  });

  it("Check state is updated upon succesfull login", async () => {
    const usernameinput = wrapper.find('input[data-testid="username-input"');
    const passwordinput = wrapper.find('input[data-testid="password-input"');
    const submitButton = wrapper.find("button");

    await usernameinput.setValue("user");
    await passwordinput.setValue("pass");
    await submitButton.trigger("click");

    await flushPromises();
  });
});
