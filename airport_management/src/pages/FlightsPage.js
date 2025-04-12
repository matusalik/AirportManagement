import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import FlightsList from '../components/FlightsList';
function FlightsPage(){
    const navigate = useNavigate();
    return(
        <div>
            <FlightsList/>
        </div>
    );
}
export default FlightsPage;