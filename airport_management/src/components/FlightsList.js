import React, { useEffect, useState } from 'react';
import axios from 'axios';

const FlightsList = () => {
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(true);

  // Pobieranie lotów z API
  useEffect(() => {
    axios.get('http://localhost:8080/flights/findAllFlights')
      .then((response) => {
        console.log('Response from backend:', response); // Dodajemy logowanie
        setFlights(response.data); // Ustawiamy dane lotów
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching flights:", error);
        setLoading(false);
      });
  }, []);
  

  // Jeśli dane się ładują, pokazujemy komunikat
  if (loading) {
    return <div>Loading flights...</div>;
  }

  return (
    <div>
      <h2>Flights List</h2>
      {flights.length === 0 ? (
        <p>No flights found.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Departure Date</th>
              <th>Arrival Date</th>
              <th>Departure Airport</th>
              <th>Arrival Airport</th>
              <th>Passeneger List</th>
            </tr>
          </thead>
          <tbody>
            {flights.map(flight => (
              <tr key={flight.idFlight}>
                <td>{flight.idFlight}</td>
                <td>{flight.departureDate}</td>
                <td>{flight.arrivalDate}</td>
                <td>{flight.departureAirport ? flight.departureAirport.airportCode : "N/A"}</td>
                <td>{flight.arrivalAirport ? flight.arrivalAirport.airportCode : "N/A"}</td>
                <td>{flight.passengerList.join(", ")}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};
export default FlightsList;