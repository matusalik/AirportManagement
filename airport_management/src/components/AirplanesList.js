import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AirplanesList = () => {
  const [airplanes, setAirplanes] = useState([]);
  const [loading, setLoading] = useState(true);

  // Pobieranie lotów z API
  useEffect(() => {
    axios.get('http://localhost:8080/airplanes/findAllAirplanes')
      .then((response) => {
        console.log('Response from backend:', response); // Dodajemy logowanie
        setAirplanes(response.data); // Ustawiamy dane lotów
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching airplanes:", error);
        setLoading(false);
      });
  }, []);
  

  // Jeśli dane się ładują, pokazujemy komunikat
  if (loading) {
    return <div>Loading airplanes...</div>;
  }

  return (
    <div>
      <h2>AirplanesList List</h2>
      {airplanes.length === 0 ? (
        <p>No airplanes found.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Seat Amount</th>
              <th>Model</th>
            </tr>
          </thead>
          <tbody>
            {airplanes.map(airplane => (
              <tr key={airplane.idAirplane}>
                <td>{airplane.idAirplane}</td>
                <td>{airplane.seatAmount}</td>
                <td>{airplane.model}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};
export default AirplanesList;