import { boolean } from "yup"

export type Assignment = {
    id: number,
    name: string,
    //available: boolean, //or ENUM if possible (already done, not picked, picked)
}
