import "dotenv/config";
import express from "express";
import cors from "cors";
import { connect } from "./util/database_con/db.js";
import authRoutes from "./routes/auth.routes.js"

const app = express()
connect();

app.use(cors());
app.use(express.json());

app.get("/", (req, res) => res.send("Auth service running"));
app.use("/api/auth", authRoutes);


const PORT = process.env.PORT || 5002;
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));