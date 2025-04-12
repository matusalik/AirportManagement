import React, {useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import AirplanesList from '../components/AirplanesList';
function AirplanesPage(){
    const navigate = useNavigate();
    return(
        <div>
            <AirplanesList/>
        </div>
    );
}
export default AirplanesPage;