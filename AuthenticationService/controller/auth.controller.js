import Auth from '../models/auth.model.js'
import jwt from "jsonwebtoken"

const Role = {
    Student: 'student',
    Instructor: 'Instructor',
    Admin: 'admin'
};

const addAuthConfig = async (req, res) => {
    const { first_name,email, password , authToken ,role} = req.body;
    try {
        //See if user Exist
        let auth = await Auth.findOne({ email });
        if (auth) {
            auth.authToken = authToken;
            auth.save()
        }else{
            auth = new Auth({
                first_name,email, password , authToken ,role
            });
            await auth.save()

        }
    } catch (err) {
        //Something wrong with the server
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
}

const authorize = async (req, res) => {
    try {
		const secret = process.env.JWT_SECRET;

		if (secret) {
			const authToken = req.header("Authorization").split(' ')[1];
			const decodedToken = jwt.verify(authToken, secret);
            return res.status(200).send("Success");

		} else {
            return res.status(500).send("Secret not found");
        }
	} catch (err) {
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
}

const authorizeStudent = async (req, res) => {
    try {
		const secret = process.env.JWT_SECRET;
        const privillages = [Role.Student,Role.Admin]
    
		if (secret) {
			const authToken = req.header("Authorization").split(' ')[1];
			const decodedToken = jwt.verify(authToken, secret);
            if(privillages.includes(decodedToken.role)){
                return res.status(200).json({isAuthorized:true});
            }else{
                return res.status(401).json({isAuthorized:false});
            }

		} else {
            return res.status(500).send("Secret not found");
        }
	} catch (err) {
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
}

const authorizeInstructor = async (req, res) => {
    try {
		const secret = process.env.JWT_SECRET;
        const privillages = [Role.Instructor,Role.Admin]
		if (secret) {
			const authToken = req.header("Authorization").split(' ')[1];
			const decodedToken = jwt.verify(authToken, secret);
            if(privillages.includes(decodedToken.role)){
                return res.status(200).json({isAuthorized:true});
            }else{
                return res.status(401).json({isAuthorized:false});
            }


		} else {
            return res.status(500).send("Secret not found");
        }
	} catch (err) {
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
}

const authorizeAdmin = async (req, res) => {
    try {
		const secret = process.env.JWT_SECRET;
        const privillages = [Role.Admin]
		if (secret) {
			const authToken = req.header("Authorization").split(' ')[1];
            console.log(authToken)
			const decodedToken = jwt.verify(authToken, secret);
            if(privillages.includes(decodedToken.role)){
                return res.status(200).json({isAuthorized:true});
            }else{
                return res.status(200).json({isAuthorized:false});
            }


		} else {
            return res.status(500).send("Secret not found");
        }
	} catch (err) {
        console.error(err.message);
        return res.status(500).send("Server Error");
    }
}


export {addAuthConfig, authorize, authorizeStudent, authorizeInstructor, authorizeAdmin}