import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import AirportsList from '../components/AirportsList';
function AirportsPage(){
    const navigate = useNavigate();
    return(
        <div>
            <AirportsList/>
        </div>
    );
}
export default AirportsPage;