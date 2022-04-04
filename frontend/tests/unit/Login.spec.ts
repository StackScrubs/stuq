import { expect } from 'chai';import { shallowMount, VueWrapper } from "@vue/test-utils";
import LoginForm from "@/components/LoginForm.vue";
import store from "@/store/index";
import router from "@/router/index";

let wrapper: VueWrapper<any>;

const emailSelector = "[data-testid=email-input]"
const passwordSelector = "[data-testid=password-input]"
const loginButtonSelector = "[data-testid=login-button]"

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

    it("checks that BaseInput components are used correctly and recieve data", () => {
        const initialEmail = "ola@nordmann.no"
        const initialPassword = "$m3lly6at"

        const emailInput = wrapper.findComponent(emailSelector);
        const passwordInput = wrapper.findComponent(passwordSelector);

        emailInput.setValue(initialEmail);
        passwordInput.setValue(initialPassword);

        expect(wrapper.vm.email).to.equal(initialEmail);
        expect(wrapper.vm.password).to.equal(initialPassword);
    });
});
