import chai, { expect } from "chai";
import chaiAsPromised from "chai-as-promised";
import sinon from "sinon";
import axios, { AxiosResponse, AxiosError } from "axios";
import { Assignment } from "@/types/Assignment";
import { getAssignments, getSubmissions } from "@/service/QueingService";
import { SubjectNotFoundError } from "@/types/Subject";

type GetAssignments = { assignments: { id: number, name: string }[] } | undefined;
type AssignmentsResponse = AxiosResponse<GetAssignments, unknown>;
type AssignmentsError = AxiosError<GetAssignments, unknown>;

type GetSubmissions = { submissions: { id: { student: undefined, assignment: Assignment}, isApproved: boolean, feedback: string }[] } | undefined;
type SubmissionsResponse = AxiosResponse<GetSubmissions, unknown>;
type SubmissionsError = AxiosError<GetSubmissions, unknown>;

chai.use(chaiAsPromised);

describe("QueuingService.ts", () => {
    afterEach(() => {
        sinon.restore();
    });

    it("Retrieves assignments when a valid subject is provided", async () => {
        const expected = { assignments: [{ id: 1, name: "Oblig 1" }, { id: 2, name: "Oblig 2" }] };
        
        const response: AssignmentsResponse = {
            data: expected,
            status: 200,
            statusText: "Retrieved",
            headers: {},
            config: {}
        };
        sinon.stub(axios, "get").resolves(response);

        const subject = {termYear: 2022, termPeriod: "H", code: "IDAT2105"} 
        const assignments = await getAssignments(subject);

        expect(assignments).to.equal(expected)
    });

    it("Throws error if subject is not recognized by endpoint", async () => {     
        const response: AssignmentsError = {
            config: {},
            isAxiosError: true,
            toJSON: function (): object {
                throw new Error("Function not implemented.");
            },
            name: "",
            message: ""
        };
        sinon.stub(axios, "get").rejects(response);

        const subject = {termYear: 2023, termPeriod: "H", code: "IDAT2105"} 
        await expect(getAssignments(subject)).to.be.rejected;
    });

    it("Throws SubjectNotFoundError if the subject is not recognized by endpoint", async () => {
        const response: AssignmentsError = {
            config: {},
            isAxiosError: true,
            toJSON: function (): object {
                throw new Error("Function not implemented.");
            },
            name: "",
            message: "",
            response: {
                data: undefined,
                status: 404,
                statusText: "Not found",
                headers: {},
                config: {}
            }
        };
        sinon.stub(axios, "get").rejects(response);

        const subject = {termYear: 2023, termPeriod: "H", code: "IDAT2105"} 
        await expect(getAssignments(subject)).to.be.rejectedWith(SubjectNotFoundError);
    });

    it("Retrieves submissions when a valid subject is provided", async () => {
        const expected = { submissions: [
            { id: { student: undefined, assignment: { id: 1, name: "Oblig 1" }}, isApproved: true, feedback: "Bra jobba!" }, 
            { id: { student: undefined, assignment: { id: 2, name: "Oblig 2" }}, isApproved: false, feedback: "Møter dessverre ikke kravet for godkjent!" }
        ]};
        
        const response: SubmissionsResponse = {
            data: expected,
            status: 200,
            statusText: "Retrieved",
            headers: {},
            config: {}
        };
        sinon.stub(axios, "get").resolves(response);

        const subject = {termYear: 2022, termPeriod: "H", code: "IDAT2105"} 
        const submissions = await getSubmissions(subject);

        expect(submissions).to.equal(expected)
    });

    it("Throws error if subject is not recognized by endpoint", async () => {     
        const response: SubmissionsError = {
            config: {},
            isAxiosError: true,
            toJSON: function (): object {
                throw new Error("Function not implemented.");
            },
            name: "",
            message: ""
        };
        sinon.stub(axios, "get").rejects(response);

        const subject = {termYear: 2023, termPeriod: "H", code: "IDAT2105"} 
        await expect(getSubmissions(subject)).to.be.rejected;
    });

    it("Throws SubjectNotFoundError if the subject is not recognized by endpoint", async () => {
        const response: SubmissionsError = {
            config: {},
            isAxiosError: true,
            toJSON: function (): object {
                throw new Error("Function not implemented.");
            },
            name: "",
            message: "",
            response: {
                data: undefined,
                status: 404,
                statusText: "Not found",
                headers: {},
                config: {}
            }
        };
        sinon.stub(axios, "get").rejects(response);

        const subject = {termYear: 2023, termPeriod: "H", code: "IDAT2105"} 
        await expect(getSubmissions(subject)).to.be.rejectedWith(SubjectNotFoundError);
    });
});
