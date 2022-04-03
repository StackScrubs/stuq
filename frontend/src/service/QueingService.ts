import { UserCredentials, InvalidCredentialsError } from "@/types/UserCredentials";
import { Session } from "@/types/Session";
import axios, { AxiosResponse } from "axios";
import { Building, Campus, Room, Table } from "@/types/Location";
import { Assignment } from "@/types/Assignment";

const CONFIG = {
    baseURL: process.env.VUE_APP_DEV_API_ENDPOINT + "/[path]",
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
    },
};

type Locations = {
    campuses: Array<Campus>;
    buildings: Array<Building>;
    rooms: Array<Room>;
    tables: Array<Table>;
};

export async function getLocations() {
    try {
        //Placeholder return type
        const response: AxiosResponse<Locations, unknown> = await axios.get("/location/all", CONFIG)
    } catch (e) {
        //handle errors from API
    }
}

export async function getAssignments() {
    try {
        //Placeholder return type
        const response: AxiosResponse<Array<Assignment>, unknown> = await axios.get("/assignment/all", CONFIG)
    } catch (e) {
        //Handle errors from API
    }
}
