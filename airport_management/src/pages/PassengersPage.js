import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import PassengersList from '../components/PassengerList'
function PassengersPage(){
    const navigate = useNavigate();
    return(
        <div>
            <PassengersList/>
        </div>
    ); 
}
export default PassengersPage;