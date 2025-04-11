import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './HomePage.css'
function HomePage(){
    const navigate = useNavigate();
    return(
        <div className='outerHome'>
            <h1>What do you want to do?</h1>
            <div className='innerHome'>
                <div className="dashboard">
                    <div className="tile" onClick={() => navigate("/flights")}>
                        ✈️
                        <span>Manage Flights</span>
                    </div>
                    <div className="tile" onClick={() => navigate("/airports")}>
                        🛫
                        <span>Manage Airports</span>
                    </div>
                    <div className="tile" onClick={() => navigate("/airplanes")}>
                        🛩️
                        <span>Manage Airplanes</span>
                    </div>
                    <div className="tile" onClick={() => navigate("/passengers")}>
                        👤
                        <span>Manage Passengers</span>
                    </div>
                    <div className="tile" onClick={() => navigate("/")}>
                        🖐️
                        <span>LOGOUT</span>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default HomePage;