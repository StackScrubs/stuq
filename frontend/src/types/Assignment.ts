import { boolean } from "yup"

export type Assignment = {
    name: string,
    available: boolean, //or ENUM if possible (already done, not picked, picked)
}
