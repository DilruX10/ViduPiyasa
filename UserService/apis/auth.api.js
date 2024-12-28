import axios from "axios";
const baseUrl = process.env.AUTH_SERVICE_BACKEND_URL;

const authApi = {
    auth() {
        return {
            registerAuth: (auth) => axios.post(baseUrl + "api/auth/registerAuth", auth),
            authorizeAdmin: (authToken) => axios.get(baseUrl + "api/auth/authorizeAdmin", {
                headers: {
                    Authorization: authToken
                }
            })
        };
    },
};
export default authApi;