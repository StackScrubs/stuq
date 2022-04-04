import chai, { expect } from "chai";
import chaiAsPromised from "chai-as-promised";
import sinon from "sinon";
import axios, { AxiosResponse, AxiosError } from "axios";
import { Assignment } from "@/types/Assignment";
import { getSubmissions } from "@/service/SubmissionService";
import { getSubjectAssignments } from "@/service/SubjectService";
import { Subject, SubjectNotFoundError } from "@/types/Subject";

type GetSubmissions = { submissions: { id: { student: undefined, assignment: Assignment}, isApproved: boolean, feedback: string }[] } | undefined;
type SubmissionsResponse = AxiosResponse<GetSubmissions, unknown>;
type SubmissionsError = AxiosError<GetSubmissions, unknown>;

chai.use(chaiAsPromised);

describe("SubmissionService.ts", () => {

    afterEach(() => {
        sinon.restore();
    });

    it("Retrieves submissions when a valid subject is provided", async () => {
        const subject = new Subject(2022, "H", "Full-stack", "IDATT2105") 

        const expected = { submissions: [
            { id: { student: undefined, assignment: new Assignment( "1", "Oblig 1", subject)}, isApproved: true, feedback: "Bra jobba!" }, 
            { id: { student: undefined, assignment: new Assignment( "2", "Oblig 2", subject)}, isApproved: false, feedback: "Møter dessverre ikke kravet for godkjent!" }
        ]};
        
        const response: SubmissionsResponse = {
            data: expected,
            status: 200,
            statusText: "Retrieved",
            headers: {},
            config: {}
        };
        sinon.stub(axios, "get").resolves(response);

        const submissions = await getSubmissions();

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

        await expect(getSubmissions()).to.be.rejected;
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

        await expect(getSubmissions()).to.be.rejectedWith(SubjectNotFoundError);
    });
});
