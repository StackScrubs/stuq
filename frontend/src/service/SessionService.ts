import { UserCredentials, InvalidCredentialsError } from "@/types/UserCredentials";
import { Session } from "@/types/Session";
import axios, { AxiosResponse } from "axios";

const CONFIG = {
    baseURL: process.env.VUE_APP_DEV_API_ENDPOINT + "/session",
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    },
};

export async function createSession(userCredentials: UserCredentials): Promise<Session> {
    try {
        const response: AxiosResponse<{ token: string }, unknown> = await axios.post("", userCredentials, CONFIG);
        return new Session(response.data.token);
    } catch (e) {
        if (axios.isAxiosError(e)) {
            if (e.response && e.response.status == 401) {
                throw new InvalidCredentialsError();
            }
        }
        throw e;
    }
}

export async function deleteSession(session: Session): Promise<void> {
    const token = encodeURIComponent(session.token);
    await axios.delete(`/${token}`, CONFIG);
}
