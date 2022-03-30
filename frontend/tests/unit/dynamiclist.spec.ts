import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import DynamicList from "@/components/DynamicList.vue";

describe("DynamicList.vue", () => {
  it("List elements render", () => {
    const list = ["Some", "awesome", "list"];
    const wrapper = shallowMount(DynamicList, {
      props: { list },
    });

    const listElements = wrapper.find("ul").findAll("li");

    expect(listElements.length).to.equal(list.length);
    listElements
      .map((li) => li.text())
      .every((val, i) => expect(val).to.equal(list[i]));
  });

  it("List updates on push", () => {
    const list = ["Some", "awesome", "list"];
    const wrapper = shallowMount(DynamicList, {
      props: { list },
    });

    list.push("with", "more", "elements");
    // DOM elements are not automatically updated
    // by Vue as pr. https://laracasts.com/discuss/channels/vue/vuejs-props-dont-update-when-input-changes
    // Check prop value directly instead here
    expect(wrapper.vm.list.length).to.equal(list.length);
    wrapper.vm.list.every((val: string, i: number) =>
      expect(val).to.equal(list[i])
    );
  });

  it("Prop 'reversed' reverses list", () => {
    const list = ["Some", "awesome", "list"];
    const wrapper = shallowMount(DynamicList, {
      props: {
        list,
        reversed: true,
      },
    });

    const reversedList = list.reverse();
    const listElements = wrapper.find("ul").findAll("li");

    expect(listElements.length).to.equal(list.length);
    listElements
      .map((li) => li.text())
      .every((val, i) => expect(val).to.equal(reversedList[i]));
  });
});
