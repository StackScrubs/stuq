import { createStore } from "vuex";
import { RootState } from "./types.js";
import { authentication } from "./modules/Authentication";

const store = createStore<RootState>({
    state: {
        version: "0.0.1",
    },
    modules: {
        authentication: authentication,
    },
    // getters: {
    //     statusGetter: (state, getters, rootState, rootGetters) => {
    //         rootGetters.someOtherGetter;
    //         rootGetters["authentication/status"];
    //     },
    //     tokenGetter: (state, getters, rootState, rootGetters) => {
    //         rootGetters.someOtherGetter;
    //         rootGetters["authentication/token"];
    //     },
    // }
});
export default store;
