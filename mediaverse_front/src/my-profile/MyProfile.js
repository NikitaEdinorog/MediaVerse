import {useEffect, useState} from "react";
import {useLocalState} from "../util/useLocalStorage";

const MyProfile = () => {

    const [jwt, setJwt] = useLocalState("", "jwt");
    const [user, setUser] = useState(null);

    useEffect(() => {
        fetch("/my-profile", {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`
            },
            method: "get"
        }).then((response) => {
            if (response.status === 200) return response.json();
        })
            .then((userData) => {
                setUser(userData);
            });
    }, []);



    return (
        <div>
            {user ? (
                <div>
                    <h3>ID: {user.id}</h3>
                    <h3>Username: {user.username}</h3>
                    <h3>First name: {user.firstName}</h3>
                    <h3>Second name: {user.secondName}</h3>
                </div>
            ) : (
                <div></div>
            )
            }

        </div>
    );
}

export default MyProfile;