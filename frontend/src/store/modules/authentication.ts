import LoginService from "@/service/LoginService";
import { User } from "@/types/User";
import {
  ActionContext,
  ActionTree,
  GetterTree,
  Module,
  MutationTree,
} from "vuex";
import { RootState } from "../types";

export interface AuthenticationState {
  loggedIn: boolean;
  token: null | string;
}

export const state: AuthenticationState = {
  loggedIn: false,
  token: "",
};

const namespaced = true;

export const actions: ActionTree<AuthenticationState, RootState> = {
  login(
    context: ActionContext<AuthenticationState, RootState>,
    user: User
  ): void {
    LoginService.doLogin(user).then(
      (token: string) => {
        context.commit("LOGIN_SUCCESS", token);
        Promise.resolve(token);
      },
      (error: string) => {
        context.commit("LOGIN_FAIL");
        Promise.reject(error);
      }
    );
  },
  // logout({ commit }) {
  //   LoginService.doLogout();
  //   commit("logout");
  // },
};

export const mutations: MutationTree<AuthenticationState> = {
  LOGIN_SUCCESS(state: AuthenticationState, token: string) {
    state.loggedIn = true;
    state.token = token;
  },
  LOGIN_FAIL(state: AuthenticationState) {
    state.loggedIn = false;
    state.token = "";
  },
};

export const getters: GetterTree<AuthenticationState, RootState> = {
  token(state: AuthenticationState) {
    return state.token;
  },
};

export const authentication: Module<AuthenticationState, RootState> = {
  namespaced,
  state,
  getters,
  actions,
  mutations,
};
