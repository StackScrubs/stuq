import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import BaseCheckbox from "@/components/BaseCheckbox.vue";

describe("BaseCheckbox.vue", () => {
  it("Label renders when set", () => {
    const label = "Some label";
    const wrapper = shallowMount(BaseCheckbox, {
      props: {
        label,
        modelValue: "",
        error: "",
      },
    });

    expect(wrapper.text()).to.equal(label);
  });

  it("Checkbox emits update", () => {
    const wrapper = shallowMount(BaseCheckbox);
    wrapper
      .find("input")
      .trigger("change")
      .then(() => expect(wrapper.emitted().update).to.equal(true));
  });

  it("Error renders when set", () => {
    const error = "Some error";
    const wrapper = shallowMount(BaseCheckbox, {
      props: {
        label: "",
        modelValue: "",
        error,
      },
    });

    expect(wrapper.text()).to.equal(error);
  });
});
