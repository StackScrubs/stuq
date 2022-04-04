import axios, { AxiosResponse } from "axios";
import { SubjectNotFoundError, Subject } from "@/types/Subject";
import { Submission } from "@/types/Submission";
import { Assignment } from "@/types/Assignment";
import { QueueType } from "@/types/QueueType";

const CONFIG = {
    baseURL: process.env.VUE_APP_DEV_API_ENDPOINT,
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    },
};

export async function getSubmissions() {
    try {
        const response: AxiosResponse<Array<Submission>, unknown> = await axios.get(
            "/student/submissions", CONFIG
        )
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            if (e.response && e.response.status == 404) {
                throw new SubjectNotFoundError();
            }
        }
        throw e;
    }
}

export async function enqueue(subject: Subject, assignments: Array<Assignment>, queueType: QueueType, message: string) {
    try {
        const response: AxiosResponse<Array<Submission>, unknown> = await axios.post(
            "/queue/add/", { subject: subject, assignments: assignments, queueType: queueType, message: message } , CONFIG 
        )
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            if (e.response && e.response.status == 404) {
                throw new SubjectNotFoundError(); //Or student not found error
            }
        }
        throw e;
    }
}
