import { createStore } from "vuex";
import { RootState } from "./types.js";
import { authentication } from "./modules/authentication";

const store = createStore<RootState>({
  state: {
    version: "0.0.1",
  },
  modules: {
    authentication,
  },
});
export default store;
