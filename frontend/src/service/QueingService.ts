import axios, { AxiosResponse } from "axios";
import { Assignment } from "@/types/Assignment";
import { SubjectNotFoundError, Subject } from "@/types/Subject";
import { Submission } from "@/types/Submission";

const CONFIG = {
    baseURL: process.env.VUE_APP_DEV_API_ENDPOINT + "/queue",
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    },
};

export async function getAssignments(subject: Subject | undefined) {
    if (subject !== undefined) {
        try {
            const response: AxiosResponse<Array<Assignment>, unknown> = await axios.get(
                `/subject
                /${subject.termYear}
                /${subject.termPeriod}
                /${subject.code}
                /assignments`, CONFIG
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
}

export async function getSubmissions(subject: Subject | undefined) {
    if (subject !== undefined) {
        try {
            const response: AxiosResponse<Array<Submission>, unknown> = await axios.get(
                `/subject
                /${subject.termYear}
                /${subject.termPeriod}
                /${subject.code}
                /assignments/submissions`, CONFIG //Not added yet
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
}

export async function enqueue(studentId: number, subject: Subject | undefined, assignmentNumbers: Array<number | undefined>, queueType: string) {
    if (subject !== undefined) {
        try {
            const response: AxiosResponse<Array<Submission>, unknown> = await axios.post(
                `queue/add/${studentId}`, { subject: subject, assignmentNumbers: assignmentNumbers, queueType: queueType} , CONFIG 
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
}
