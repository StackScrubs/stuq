import { Assignment } from "@/types/Assignment";
import { CreateSubjectRequest } from "@/types/CreateSubjectRequest";
import { NotFoundError } from "@/types/NotFoundError";
import { Subject } from "@/types/Subject";
import { Teacher, TeachingAssistant } from "@/types/User";
import axios, { AxiosResponse } from "axios";

const CONFIG = {
    baseURL: process.env.VUE_APP_DEV_API_ENDPOINT,
    withCredentials: false,
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json",
    },
};
function subjectUrl(termYear: number, termPeriod: string, code: string): string {
    const encode = encodeURIComponent;
    return `subject/${encode(termYear)}/${encode(termPeriod)}/${encode(code)}`;
}

function translateError(error: unknown): unknown {
    if (axios.isAxiosError(error)) {
        if (error.response && error.response.status == 404) {
            return new NotFoundError(error.response.data);
        }
    }
    return error;
}

function subjectRequest<TData, TReturn>(handler: (url: string, data: TData) => Promise<AxiosResponse<TReturn, unknown>>) {
    return (termYear: number, termPeriod: string, code: string, data: TData) => 
        handler(subjectUrl(termYear, termPeriod, code), data)
            .then(x => x.data)
            .catch(e => { throw translateError(e) });
}


export async function createSubject(data: CreateSubjectRequest) {
    try {
        await axios.post("/subject", data, CONFIG).catch(e => { throw translateError(e) });
    } catch (e) {
        throw translateError(e);
    }
}
export const updateSubject = subjectRequest<CreateSubjectRequest, void>((url, data) => axios.put(url, data, CONFIG));
export const getSubject = subjectRequest<void, void>(url => axios.get(url, CONFIG));
export const deleteSubject = subjectRequest<void, void>((url) => axios.delete(url, CONFIG));
export const getSubjectTeachingAssistants = subjectRequest<void, TeachingAssistant[]>((url) => axios.get(url + "/teaching-assistants", CONFIG));
export const getSubjectTeachers = subjectRequest<void, Teacher[]>((url) => axios.get(url + "/teachers", CONFIG));
export const getSubjectAssignments = subjectRequest<void, Assignment[]>((url) => axios.get(url + "/assignments", CONFIG));
export async function getAllSubjects() {
    try {
        const response: AxiosResponse<Subject[], unknown> = await axios.get("/subject", CONFIG);
        return response.data;
    } catch (e) {
        throw translateError(e);
    }
}
