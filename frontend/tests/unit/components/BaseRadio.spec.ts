import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import BaseRadio from "@/components/BaseRadio.vue";

describe("BaseRadio.vue", () => {
    it("renders label when set", () => {
        const label = "Some label";
        const wrapper = shallowMount(BaseRadio, {
            props: {
                label,
                modelValue: "",
                error: "",
            },
        });

        expect(wrapper.text()).to.equal(label);
    });

    it("emits update on radiobutton @change", () => {
        const wrapper = shallowMount(BaseRadio);
        wrapper
            .find("input")
            .trigger("change")
            .then(() => expect(wrapper.emitted().update).to.equal(true));
    });
});
