import User from '../models/user.model.js'
import bcrypt from "bcrypt"
import authApi from "../apis/auth.api.js"
import jwt from "jsonwebtoken"
const register = async (req, res) => {
    const { first_name, last_name, email, mobileNumber, address, password, role } = req.body;
    try {
        //See if user Exist
        let user = await User.findOne({ email });

        if (user) {
            return res
                .status(400)
                .json({ errors: [{ msg: "User already exist" }] });
        }

        var date = new Date()
        const createdAt = date;
        const updatedAt = date;


        //create a user instance
        user = new User({
            first_name, last_name, email, mobileNumber, address, password, role, createdAt, updatedAt
        });
        const salt = await bcrypt.genSalt(10);
        //hashing password
        user.password = await bcrypt.hash(password, salt);

        //save user to the database
        await user.save()
        const authToken = await user.generateAuthToken()
        //send req to auth services
        const authObj = {
            first_name: first_name,
            email: email,
            password: password,
            authToken: authToken,
            role: role
        }
        authApi.auth().registerAuth(authObj)
        const resObj = {
            authToken: authToken
        }
        res.json(resObj);

    } catch (err) {
        //Something wrong with the server
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
}
const login = async (req, res) => {
    const { email, password } = req.body;

    try {
        //See if user Exist
        let user = await User.findOne({ email });
        if (!user) {
            return res.status(400).json({ errors: [{ msg: "Invalid Credentials" }] });
        }

        //match the user email and password
        const isMatch = await bcrypt.compare(password, user.password);

        if (!isMatch) {
            return res.status(400).json({ errors: [{ msg: "Invalid Credentials" }] });
        }

        const authToken = await user.generateAuthToken()
        //send req to auth service
        const authObj = {
            email: email,
            password: password,
            authToken: authToken,
        }
        authApi.auth().registerAuth(authObj)
        const resObj = {
            authToken: authToken
        }
        res.json(resObj);

    } catch (err) {
        //Something wrong with the server
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
};

const updateUser = async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        if (user != null) {
            User.findByIdAndUpdate(req.params.id).then(async (userProfile) => {
                if (req.body.first_name) {
                    userProfile.first_name = req.body.first_name;
                }
                if (req.body.last_name) {
                    userProfile.last_name = req.body.last_name;
                }
                if (req.body.email) {
                    userProfile.email = req.body.email;
                }
                if (req.body.mobileNumber) {
                    userProfile.mobileNumber = req.body.mobileNumber;
                }
                if (req.body.address) {
                    userProfile.address = req.body.address;
                }
                if (req.body.role) {
                    userProfile.role = req.body.role;
                }
                if (req.body.password) {
                    const salt = await bcrypt.genSalt(10);
                    userProfile.password = await bcrypt.hash(req.body.password, salt);
                }


                var date = new Date()
                userProfile.updatedAt = date
                userProfile
                    .save()
                    .then((response) => res.json(response))
                    .catch((err) => res.status(400).json("Error: " + err));
            });
        }

    } catch (err) {
        //Something wrong with the server
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
}
const deleteUser = async (req, res) => {
    try {
        const authToken = req.header("Authorization");
        const authresponse = await authApi.auth().authorizeAdmin(authToken)
        if (authresponse.data.isAuthorized) {
            await User.findByIdAndDelete(req.params.id)
                .then(() => {
                    res.json("User Deleted");
                })
                .catch((err) => res.status(400).json("Error: " + err));
        }
        else {
            res.status(401).send("Unauthorized");
        }

    } catch (err) {
        console.log(err.message);
        res.status(500).send("Server Error");
    }

}
const getUserDetailsbyID = async (req, res) => {
    try {
        const authToken = req.header("Authorization");
        const authresponse = await authApi.auth().authorizeAdmin(authToken)
        if (authresponse.data.isAuthorized) {
            const user = await User.findById(req.params.id).select("-password")
            res.json(user);
        }
        else {
            res.status(401).send("Unauthorized");
        }

    } catch (err) {
        console.log(err.message);
        res.status(500).send("Server Error");
    }
}
const getUserDetailsbytoken = async (req, res) => {
    try {
        const secret = process.env.JWT_SECRET;
        const authToken = req.header("Authorization").split(' ')[1];
        const decodedToken = jwt.verify(authToken, secret);
        const user = await User.findById(decodedToken._id).select("-password")
        res.json(user);
    } catch (err) {
        console.log(err.message);
        res.status(500).send("Server Error");
    }
}

const getUserList = async (req, res) => {
    try {
        const authToken = req.header("Authorization");
        const authresponse = await authApi.auth().authorizeAdmin(authToken);
        if (authresponse.data.isAuthorized) {
            const userList = await User.find().select("-password")
            res.json(userList);
        }
        else {
            res.status(401).send("Unauthorized");
        }
    } catch (err) {
        console.log(err.message);
        return res.status(500).send("Server Error");
    }
}

export { register, login, updateUser, deleteUser, getUserDetailsbyID, getUserDetailsbytoken, getUserList }