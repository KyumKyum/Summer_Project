import axios from "axios";


export const sendGenerateQuery = async (
    userId,
    name,
    keyVal
) => {
    const genReq = {
        userId,
        name,
        size: 240,
        keyVal
    }

    const res = await axios.post("http://localhost:8080/generate", genReq);

    if(res.data.ok){
        return res.data?.data.avatarUrl;
    }else{
        alert("Generation Failed!");
        return null
    }
}