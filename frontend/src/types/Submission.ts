import { Assignment } from "./Assignment"

export type Submission = {
    id: {
        student: undefined,
        assignment: Assignment
    },
    isApproved: boolean,
    feedback: string
}
