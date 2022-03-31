export class Session {
    private _token: string

    constructor(token: string) {
        this._token = token;
    }

    public get token(): string {
        return this._token;
    }
}