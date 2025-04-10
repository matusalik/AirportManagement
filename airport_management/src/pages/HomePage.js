import React, {useState} from 'react';
import './HomePage.css'
function HomePage(){
    const [loginValue, setLoginValue] = useState("");
    const [passwordValue, setPasswordValue] = useState("");
    const handleLoginChange = (e) => {
        setLoginValue(e.target.value);
    };
    const handlePasswordChange = (e) => {
        setPasswordValue(e.target.value);
    };
    const handleLogin = () =>{

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
                    type="text"
                    placeholder="Password"
                    value={passwordValue}
                    onChange={handlePasswordChange}
                /><br/>
                <button className='homePageButton' onClick={handleLogin}>
                    Log in
                </button>
            </div>
        </div>
    );
}
export default HomePage;