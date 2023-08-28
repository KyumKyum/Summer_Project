import axios from "axios";


export const sendTestQuery = async () => {
    const res = await axios.post("http://localhost:8080/test");

    console.log(res.data)
}