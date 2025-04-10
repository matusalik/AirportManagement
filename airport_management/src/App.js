import React from 'react';
import './App.css';
import FlightsList from './components/FlightsList';
import PassenegerList from './components/PassengerList';
import AirportsList from './components/AirportsList';
import AirplanesList from './components/AirplanesList';
import HomePage from './pages/HomePage';

function App() {
  return (
    <div className="App">
      <HomePage/>
      {/*<FlightsList />
      <PassenegerList/>
      <AirportsList/>
      <AirplanesList/>*/}
    </div>
  );
}

export default App;