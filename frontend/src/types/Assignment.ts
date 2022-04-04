import { Subject } from "./Subject";

export class Assignment {
    constructor(
        readonly id: string,
        readonly name: string,
        readonly subject: Subject,
    ) {}
}
