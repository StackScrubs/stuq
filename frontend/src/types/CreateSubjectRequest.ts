export class CreateSubjectRequest {
    constructor(
        readonly termYear: number,
        readonly termPeriod: string,
        readonly code: string,
        readonly name: string,
    ) {}
}
