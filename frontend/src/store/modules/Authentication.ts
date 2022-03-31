import { createSession, deleteSession } from "@/service/SessionService";
import { Session } from "@/types/Session";
import { UserCredentials } from "@/types/UserCredentials";
import {
    ActionContext,
    ActionTree,
    GetterTree,
    Module,
    MutationTree,
} from "vuex";
import { RootState } from "../types";

interface AuthenticationState {
  session: Session | null;
}

const state: AuthenticationState = {
    session: null,
};

const namespaced = true;

const actions: ActionTree<AuthenticationState, RootState> = {
    async login(
        context: ActionContext<AuthenticationState, RootState>,
        user: UserCredentials
    ): Promise<void> {
        const session = await createSession(user);
        context.commit("LOGIN_SUCCESS", session);
    },

    async logout(
        context: ActionContext<AuthenticationState, RootState>,
    ): Promise<void> {
        if (state.session === null) return;

        try {
            await deleteSession(state.session);
            context.commit("LOGOUT_SUCCESS");
        } catch (e) {
            console.error("unable to log out", e);
        }
    },
};

const mutations: MutationTree<AuthenticationState> = {
    LOGIN_SUCCESS(state: AuthenticationState, session: Session) {
        state.session = session;
    },

    LOGOUT_SUCCESS(state: AuthenticationState) {
        state.session = null;
    },
};

const getters: GetterTree<AuthenticationState, RootState> = {
    session(state: AuthenticationState) {
        return state.session;
    },
};

export const authentication: Module<AuthenticationState, RootState> = {
    namespaced,
    state,
    getters,
    actions,
    mutations,
};
