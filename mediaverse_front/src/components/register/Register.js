import QueryBack from "../../util/QueryBack";

function  Register(name, secondName, username, email, password){
    const requestbody = {
        "firstName": name,
        "secondName": secondName,
        "username": username,
        "email": email,
        "password": password,
    }

    return QueryBack("auth/register", "post", null, requestbody)
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



export  default Register;