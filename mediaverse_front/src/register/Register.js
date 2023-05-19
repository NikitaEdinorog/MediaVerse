import {useLocalState} from "../util/useLocalStorage";
import {useState} from "react";

const Register = () => {


    const [firstName, setFirstName] = useState("");
    const [secondName, setSecondName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [matchingPassword, setMathingPassword] = useState("");
    const [message, setMessage] = useState("")


    const [jwt, setJwt] = useLocalState("", "jwt");

    function register() {

        if (password !== matchingPassword) {
            setMessage("Passwords not equals");
            return;
        }

        const requestbody = {
            "username": username,
            "password": password,
            "firstName": firstName,
            "secondName": secondName,
            "email": email
        }

        fetch("auth/register", {
            headers: {
                "Content-Type": "application/json",
            },
            method: "post",
            body: JSON.stringify(requestbody)

        }).then((response) => {
            if (response.status === 200) {
                return Promise.all([response.json(), response.headers]);
            } else {
                return Promise.reject("Invalid registration attempt");
            }
        })
            .then(([body, headers]) => {
                setJwt(headers.get("authorization"));
                console.log(headers.get("authorization"))
            })
            .catch((msg) => {
                alert(msg);
            });

    }


    return (
        <div>
            <form>
                <fieldset>
                    <legend>Sign Up</legend>
                    <div>
                        <label htmlFor="firstName"></label>
                        <div>
                            <input id="firstName" name="firstName" type="text" placeholder="First Name"
                                   onChange={(e) => setFirstName(e.target.value)}
                                   required=""/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="secondName"></label>
                        <div>
                            <input id="secondName" name="secondName" type="text" placeholder="Second Name"
                                   onChange={(e) => setSecondName(e.target.value)}
                                   required=""/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="username"></label>
                        <div>
                            <input id="username" name="username" type="text" placeholder="Username"
                                   onChange={(e) => setUsername(e.target.value)}
                                   required=""/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="email"></label>
                        <div>
                            <input id="email" name="email" type={"email"} placeholder="email"
                                   onChange={(e) => setEmail(e.target.value)}
                                   required=""/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="password"></label>
                        <div>
                            <input id="password" name="password" type="password" placeholder="Password"
                                   onChange={(e) => setPassword(e.target.value)}/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="mathingPassword"></label>
                        <div>
                            <input id="mathingPassword" name="mathingPassword" type="password"
                                   placeholder="Mathing Password"
                                   onChange={(e) => setMathingPassword(e.target.value)}/>
                        </div>
                    </div>
                    <div>
                        <label htmlFor="loginBtn"></label>
                        <div>
                            <button id="loginBtn" name="loginBtn" type={"submit"}
                                    onClick={() => {register();}}>Sign Up
                            </button>
                        </div>
                    </div>
                </fieldset>
            </form>
            <p> {message}
            </p>
        </div>
    );
}


export default Register;