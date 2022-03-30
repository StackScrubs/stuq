import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import BaseInput from "@/components/BaseInput.vue";

describe("BaseInput.vue", () => {
  it("Label renders when set", () => {
    const label = "Some label";
    const wrapper = shallowMount(BaseInput, {
      props: {
        label,
        modelValue: "",
        error: "",
      },
    });

    expect(wrapper.text()).to.equal(label);
  });

  it("Input updates modelValue when set", () => {
    const initialInput = "Some val";
    const wrapper = shallowMount(BaseInput);
    const input = wrapper.find("input");
    input.element.value = initialInput;
    input
      .trigger("input")
      .then(() => expect(wrapper.vm.modelValue).to.equal(initialInput));

    const otherInput = "Some other val";
    input.element.value = otherInput;
    input
      .trigger("input")
      .then(() => expect(wrapper.vm.modelValue).to.equal(otherInput));
  });

  it("Input emits update", () => {
    const wrapper = shallowMount(BaseInput);
    wrapper
      .find("input")
      .trigger("input")
      .then(() => expect(wrapper.emitted().update).to.equal(true));
  });

  it("Error renders when set", () => {
    const error = "Some error";
    const wrapper = shallowMount(BaseInput, {
      props: {
        label: "",
        modelValue: "",
        error,
      },
    });

    expect(wrapper.text()).to.equal(error);
  });
});
