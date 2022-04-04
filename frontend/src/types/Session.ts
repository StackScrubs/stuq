export class Session {
    private _token: string
    private _studentId: number

    constructor(token: string, studentId: number ) {
        this._token = token;
        this._studentId = studentId;
    }

    public get token(): string {
        return this._token;
    }

    public get studentId(): number {
        return this._studentId;
    }
}
