import axios from "axios";
import { Login } from "../types/Login";
const API = axios.create({
  baseURL: "API URL" + "/login", //Set from .env
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
  doLogin(login: Login) {
    return API.post("", login).then(
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
