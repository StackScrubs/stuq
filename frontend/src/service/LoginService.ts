import axios from "axios";
import { User } from "../types/User";
const API = axios.create({
  baseURL: "API URL" + "/login", //Set from .env
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
  doLogin(user: User) {
    return API.post("", user).then(
      function (value) {
        /* code if successful */
        //set recieved token in state
        return value.data;
      },
      function (error) /*set type :ERROR ? */ {
        /* code if some error */

        //Handle error and return new frontend error
        return error;
      }
    );
  },
};
