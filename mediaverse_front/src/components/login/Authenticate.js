import QueryBack from "../../util/QueryBack";
import header from "../Header";

function  Authenticate (username, password) {



    const requestbody = {
        "username": username,
        "password": password
    }

    return QueryBack("auth/login", "post", null, requestbody)
        .then((response) => {
            if (response !== 403 && response !== 500) {
                return response[1].get("authorization");

            } else {
                return response;
            }
        })
        .catch((msg) => {
            console.log("catch")
            return msg})
}

export  {Authenticate}
