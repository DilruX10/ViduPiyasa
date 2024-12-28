import mongoose from "mongoose";
import jwt from "jsonwebtoken";
const Schema = mongoose.Schema;

const UserSchema = new Schema({
    first_name: {
        type: String,
    },
    last_name: {
        type: String,
    },
    email: {
        type: String,
        unique: true,
    },
    mobileNumber: {
        type: String,
    },
    address: {
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
    },
    createdAt: {
        type: Date,
    },
    updatedAt: {
        type: Date,
    }
    
});
UserSchema.methods.generateAuthToken = async function () {
	const user = this;
	const secret = process.env.JWT_SECRET;
	const authToken = jwt.sign({ _id: user._id , role: user.role , email:user.email}, secret);
	user.authToken = authToken;
	await user.save();
	return authToken;
};

const User = mongoose.model("User", UserSchema);
export default User;