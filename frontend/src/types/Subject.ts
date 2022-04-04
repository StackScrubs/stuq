// export interface Subject {
//     termYear: number, 
//     termPeriod: string, 
//     code: string
// }

export type Subject = {
    termYear: number, 
    termPeriod: string, 
    code: string
}

interface SubjectConstructor {
    new(termYear: number, termPeriod: string, code: string): Subject;
}

export let Subject: SubjectConstructor;

export class SubjectNotFoundError extends Error {}
