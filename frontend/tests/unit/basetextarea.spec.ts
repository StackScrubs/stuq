import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import BaseTextArea from "@/components/BaseTextArea.vue";

describe("BaseTextArea.vue", () => {
  it("Label renders when set", () => {
    const label = "Some label";
    const wrapper = shallowMount(BaseTextArea, {
      props: {
        label,
        modelValue: "",
        error: "",
      },
    });
    const placeholder = wrapper.find("textarea").attributes("placeholder");
    expect(placeholder).to.equal(label);
  });

  it("Input updates modelValue when set", () => {
    const initialInput = "Some val";
    const wrapper = shallowMount(BaseTextArea);
    const textarea = wrapper.find("textarea");
    textarea.element.value = initialInput;
    textarea
      .trigger("input")
      .then(() => expect(wrapper.vm.modelValue).to.equal(initialInput));

    const otherInput = "Some other val";
    textarea.element.value = otherInput;
    textarea
      .trigger("input")
      .then(() => expect(wrapper.vm.modelValue).to.equal(otherInput));
  });

  it("Input emits update", () => {
    const wrapper = shallowMount(BaseTextArea);
    wrapper
      .find("textarea")
      .trigger("input")
      .then(() => expect(wrapper.emitted().update).to.equal(true));
  });

  it("Error renders when set", () => {
    const error = "Some error";
    const wrapper = shallowMount(BaseTextArea, {
      props: {
        label: "",
        modelValue: "",
        error,
      },
    });

    expect(wrapper.text()).to.equal(error);
  });
});
