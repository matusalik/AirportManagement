import React from 'react';
import './App.css';
import{
  BrowserRouter as Router,
  Routes,
  Route,
} from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import HomePage from './pages/HomePage';
import FlightsPage from './pages/FlightsPage';
import AirportsPage from './pages/AirportsPage';
import AirplanesPage from './pages/AirplanesPage';
import PassengersPage from './pages/PassengersPage';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<LoginPage/>}/>
          <Route path="/home" element={<HomePage/>}/>
          <Route path="/flights" element={<FlightsPage/>}/>
          <Route path="/airports" element={<AirportsPage/>}/>
          <Route path="/airplanes" element={<AirplanesPage/>}/>
          <Route path="/passengers" element={<PassengersPage/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;