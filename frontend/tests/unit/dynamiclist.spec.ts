import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import DynamicList from "@/components/DynamicList.vue";

describe("DynamicList.vue", () => {
  it("renders list elements", () => {
    const list = ["Some", "awesome", "list"];
    const wrapper = shallowMount(DynamicList, {
      props: { list },
    });

    const listElements = wrapper.find("ul").findAll("li");

    expect(listElements.length).to.equal(list.length);
    expect(listElements.map((li) => li.text())).to.deep.equal(list);
  });

  it("updates list when items are pushed into said list", () => {
    const list = ["Some", "awesome", "list"];
    const wrapper = shallowMount(DynamicList, {
      props: { list },
    });

    list.push("with", "more", "elements");
    // DOM elements are not automatically updated
    // by Vue as pr. https://laracasts.com/discuss/channels/vue/vuejs-props-dont-update-when-input-changes
    // Check prop value directly instead here
    expect(wrapper.vm.list.length).to.equal(list.length);
    expect(wrapper.vm.list).to.deep.equal(list);
  });

  it("reverses list when prop 'reverse' is set to 'true", () => {
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
    expect(listElements.map((li) => li.text())).to.deep.equal(reversedList);
  });
});
