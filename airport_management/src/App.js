import React from 'react';
import './App.css';
import FlightsList from './components/FlightsList';
import PassenegerList from './components/PassengerList';
import AirportsList from './components/AirportsList';
import AirplanesList from './components/AirplanesList';

function App() {
  return (
    <div className="App">
      <h1>Flight Management System</h1>
      <FlightsList />
      <PassenegerList/>
      <AirportsList/>
      <AirplanesList/>
    </div>
  );
}

export default App;