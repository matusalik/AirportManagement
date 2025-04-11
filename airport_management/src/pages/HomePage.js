import React, {useState} from 'react';
import axios from 'axios';
import './HomePage.css'
function HomePage(){
    const [loginValue, setLoginValue] = useState("");
    const [passwordValue, setPasswordValue] = useState("");
    const [errorLabel, setErrorLabel] = useState("");
    const handleLoginChange = (e) => {
        setLoginValue(e.target.value);
    };
    const handlePasswordChange = (e) => {
        setPasswordValue(e.target.value);
    };
    const handleLogin = () =>{
        axios.get('http://localhost:8080/users/findAllUsers')
        .then((response) => {
            const foundUser = response.data.find(
                (u) => u.userLogin === loginValue.trim() && u.userPassword === passwordValue.trim()
            );

            if (foundUser) {
                console.log("SUCCESS");
                setErrorLabel("");
            } 
            else {
                setErrorLabel("Invalid login or password!");
            }
        })
        .catch((error) => {
            console.error("Error fetching users:", error);
        });
    }
    return(
        <div className='outerHome'>
            <h1>Welcome to airport manager!</h1>
            <div className='innerHome'>
                <h2>Login</h2>
                <input className='homePageInput'
                    type="text"
                    placeholder="Login"
                    value={loginValue}
                    onChange={handleLoginChange}
                /><br/>        
                <input className='homePageInput'
                    type="password"
                    placeholder="Password"
                    value={passwordValue}
                    onChange={handlePasswordChange}
                /><br/>
                <button className='homePageButton' onClick={handleLogin}>
                    Log in
                </button><br/>
                <label className='errorLabel'>
                    {errorLabel}
                </label>
            </div>
        </div>
    );
}
export default HomePage;