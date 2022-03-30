import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import BaseSelect from "@/components/BaseSelect.vue";

describe("BaseSelect.vue", () => {
  it("renders label when set", () => {
    const label = "Some label";
    const wrapper = shallowMount(BaseSelect, {
      props: {
        label,
        modelValue: "",
        options: [],
      },
    });

    expect(wrapper.text()).to.equal(label);
  });

  it("renders option elements", () => {
    const options = ["Opt1", "Opt2", "Opt3"];
    const wrapper = shallowMount(BaseSelect, {
      props: {
        label: "",
        modelValue: "",
        options,
      },
    });

    const wrapperOptions = wrapper.find("select").findAll("option");
    expect(wrapperOptions.map((opt) => opt.text())).to.deep.equal(options);
  });

  it("emits update on select @change", () => {
    const wrapper = shallowMount(BaseSelect);
    wrapper
      .find("select")
      .trigger("change")
      .then(() => expect(wrapper.emitted().update).to.equal(true));
  });
});
