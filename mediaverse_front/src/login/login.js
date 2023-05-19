import {useLocalState} from "../util/useLocalStorage";
import {useState} from "react";

const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [jwt, setJwt] = useLocalState("", "jwt");

    function authenticate() {
        const requestbody = {
            "username": username,
            "password": password
        }

        fetch("auth/login", {
            headers: {
                "Content-Type": "application/json",
            },
            method: "post",
            body: JSON.stringify(requestbody)

        }).then((response) => {
            if (response.status === 200) {
                return Promise.all([response.json(), response.headers]);
            } else {
                return Promise.reject("Invalid login attempt");
            }
        })
            .then(([body, headers]) => {
                setJwt(headers.get("authorization"));
                window.location.href = "my-profile";
            })
            .catch((msg) => {
                alert(msg);
            });
    }


    return (
        <div>
            <form>
                <fieldset>
                    <legend>Sign In</legend>
                    <div>
                        <label htmlFor="username"></label>
                        <div>
                            <input id="username" name="username" type="text" placeholder="Username"
                                   onChange={(e) => setUsername(e.target.value)}
                                   required=""/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="passworLabel"></label>
                        <div>
                            <input id="passwordinput" name="passwordinput" type="password" placeholder="Password"
                                   onChange={(e) => setPassword(e.target.value)}/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="loginBtn"></label>
                        <div>
                            <button id="loginBtn" name="loginBtn" type={"submit"}
                                    onClick={() => authenticate()}>Sign In
                            </button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    );
}


export default Login;