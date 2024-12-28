import mongoose from "mongoose";
const Schema = mongoose.Schema;

const AuthSchema = new Schema({   
    first_name: {
        type: String,
    },
    email: {
        type: String,
    },
    password: {
        type: String,
    },
    authToken: {
        type: String,
    },
    role: {
        type: String,
    }
});

const Auth = mongoose.model("Auth", AuthSchema);
export default Auth;