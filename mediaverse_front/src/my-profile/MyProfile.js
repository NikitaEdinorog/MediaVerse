import {useEffect, useState} from "react";
import {useLocalState} from "../util/useLocalStorage";
import Header from "../components/Header";

const MyProfile = () => {

    const [jwt, setJwt] = useLocalState("", "jwt");
    const [ProfileDTO, setProfileDTO] = useState(null);

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
            .then((ProfileDTO) => {
                setProfileDTO(ProfileDTO);
            });
    }, []);


    return (
        <div>

            {ProfileDTO ? (
                <div>
                    <Header username={ProfileDTO.user.username} userLogo={ProfileDTO.userImagePath}/>
                    <h3>Profile</h3>
                    <div>
                        <h3>ID: {ProfileDTO.user.id}</h3>
                        <h3>Username: {ProfileDTO.user.username}</h3>
                        <h3>First Name: {ProfileDTO.user.firstName}</h3>
                        <h3>Second Name: {ProfileDTO.user.secondName}</h3>
                        <h3>Email: {ProfileDTO.user.email}</h3>
                    </div>
                    <div>
                        <h3>Posts: </h3>
                        {ProfileDTO.posts.map(post => (
                                <div>
                                    <h3>ID: {post.id}</h3>
                                    <h3>Text: {post.description}</h3>
                                    <h3>Created: {post.timestamp}</h3>
                                    <h3>Tags: </h3>
                                    {post.tags.map(tag => (
                                        <div>
                                            <h2>{tag.name}</h2>
                                        </div>
                                    ))}
                                </div>
                            )
                        )}
                    </div>
                </div>
            ) : (
                <div></div>
            )
            }

        </div>
    );
}

export default MyProfile;