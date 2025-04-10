import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AirportsList = () => {
  const [airports, setAirports] = useState([]);
  const [loading, setLoading] = useState(true);

  // Pobieranie lotów z API
  useEffect(() => {
    axios.get('http://localhost:8080/airports/findAllAirports')
      .then((response) => {
        console.log('Response from backend:', response); // Dodajemy logowanie
        setAirports(response.data); // Ustawiamy dane lotów
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching airports:", error);
        setLoading(false);
      });
  }, []);
  

  // Jeśli dane się ładują, pokazujemy komunikat
  if (loading) {
    return <div>Loading airports...</div>;
  }

  return (
    <div>
      <h2>Airtports List</h2>
      {airports.length === 0 ? (
        <p>No airports found.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Airport Code</th>
              <th>City</th>
            </tr>
          </thead>
          <tbody>
            {airports.map(airport => (
              <tr key={airport.idAirport}>
                <td>{airport.idAirport}</td>
                <td>{airport.airportCode}</td>
                <td>{airport.city}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};
export default AirportsList;