import { expect } from 'chai';import { shallowMount, VueWrapper } from "@vue/test-utils";
import QueuingForm from "@/components/QueuingForm.vue";
import store from "@/store/index";
import router from "@/router/index";
import { nextTick } from 'vue';

let wrapper: VueWrapper<any>;

const campusesSelector = "[data-testid=campuses-select]"
const buildingsSelector = "[data-testid=buildings-select]"
const roomsSelector = "[data-testid=rooms-select]"
const tablesSelector = "[data-testid=tables-select]"

describe("QueuingForm.vue", () => {
    beforeEach(() => {
        wrapper = shallowMount(QueuingForm, {
            global: {
                mocks: {
                    $store: store,
                    $router: router,
                },
            },
        });
    });

    it("BaseCheckbox components recieve input and are rendered correctly", () => {
        //
    });

    it("BaseRadio components recieve input and are rendered correctly", () => {
        //
    });

    it("BaseTextArea components recieve input and are rendered correctly", () => {
        //
    });
});
