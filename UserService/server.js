import "dotenv/config";
import express from "express";
import cors from "cors";
import { connect } from "./util/database_con/db.js";
import userRoutes from "./routes/user.routes.js"

const app = express()
connect();

app.use(cors());
app.use(express.json());

app.get("/", (req, res) => res.send("User service running"));
app.use("/api/user", userRoutes);


const PORT = process.env.PORT || 5001;
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));