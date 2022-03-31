import chai, { expect } from "chai";
import chaiAsPromised from "chai-as-promised";
import sinon from "sinon";
import axios, { AxiosResponse, AxiosError, AxiosInstance } from "axios";
import { createSession, deleteSession } from "@/service/SessionService";
import { Session } from "@/types/Session";
import { InvalidCredentialsError } from "@/types/UserCredentials";

type CreateData = {token: string} | undefined;
type AxiosCreateResponse = AxiosResponse<CreateData, unknown>;
type AxiosCreateError = AxiosError<CreateData, unknown>;

type DeleteData = void;
type AxiosDeleteResponse = AxiosResponse<DeleteData, unknown>;
type AxiosDeleteError = AxiosError<DeleteData, unknown>;

chai.use(chaiAsPromised);

describe("SessionService.ts", () => {
    afterEach(() => {
        sinon.restore();
    });

    it("Creates session if logged in successfully", async () => {
        const expected = "Th1$154T0K3n";
        
        const response: AxiosCreateResponse = {
            data: { token: expected },
            status: 201,
            statusText: "Created",
            headers: {},
            config: {}
        };
        sinon.stub(axios, "post").resolves(response);

        const userCredentials = {email: "monke@mail.com", password: "$m3lly6at"}
        const session = await createSession(userCredentials);

        expect(session.token).to.equal(expected)
    });

    it("Throws error if usercredentials are invalid", async () => {     
        const response: AxiosCreateError = {
            config: {},
            isAxiosError: true,
            toJSON: function (): object {
                throw new Error("Function not implemented.");
            },
            name: "",
            message: ""
        };
        sinon.stub(axios, "post").rejects(response);

        const userCredentials = {email: "monke@mail.com", password: "$m3lly6at"}
        await expect(createSession(userCredentials)).to.be.rejected;
    });

    it("Throws InvalidCredentialsError if the user credentials are invalid", async () => {
        const response: AxiosCreateError = {
            config: {},
            isAxiosError: true,
            toJSON: function (): object {
                throw new Error("Function not implemented.");
            },
            name: "",
            message: "",
            response: {
                data: undefined,
                status: 401,
                statusText: "Unauthorized",
                headers: {},
                config: {}
            }
        };
        sinon.stub(axios, "post").rejects(response);

        const userCredentials = {email: "monke@mail.com", password: "$m3lly6at"}
        await expect(createSession(userCredentials)).to.be.rejectedWith(InvalidCredentialsError);
    });

    it("Returns nothing if session was deleted successfully", async () => {
        const response: AxiosDeleteResponse = {
            status: 201,
            statusText: "Deleted",
            headers: {},
            config: {},
            data: undefined
        };
        sinon.stub(axios, "delete").rejects(response);
        
        const session = new Session("1010101010100101010101010100101010101010")
        
        expect(deleteSession(session)).to.eventually.be.undefined;
    });

    it("Throws error if session was not properly deleted", async () => {
        const response: AxiosDeleteError = {
            config: {},
            isAxiosError: true,
            toJSON: function (): object {
                throw new Error("Function not implemented.");
            },
            name: "",
            message: ""
        };
        sinon.stub(axios, "delete").rejects(response);
        
        const session = new Session("1010101010100101010101010100101010101010")
        expect(deleteSession(session)).to.eventually.throw()
    });
});