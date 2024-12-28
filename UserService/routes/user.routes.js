import express from "express"
import {login,register,updateUser,deleteUser,getUserDetailsbyID,getUserList,getUserDetailsbytoken} from "../controller/user.controller.js"

const userRoutes = express.Router();

userRoutes.post("/login", login);
userRoutes.post("/register", register);
userRoutes.put("/update/:id", updateUser);
userRoutes.delete("/delete/:id", deleteUser);
userRoutes.get("/user_token/getdetails", getUserDetailsbytoken);
userRoutes.get("/:id", getUserDetailsbyID);
userRoutes.get("/", getUserList);

export default userRoutes;