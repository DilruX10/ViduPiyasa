import express from "express"
import {addAuthConfig, authorize, authorizeStudent, authorizeInstructor, authorizeAdmin} from "../controller/auth.controller.js"

const authRoutes = express.Router();

authRoutes.get("/authorize", authorize);
authRoutes.get("/authorizeSeller", authorizeStudent);
authRoutes.get("/authorizeBuyer", authorizeInstructor);
authRoutes.get("/authorizeAdmin", authorizeAdmin);
authRoutes.post("/registerAuth", addAuthConfig);

export default authRoutes;