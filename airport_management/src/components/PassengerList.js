import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PassengerList = () => {
  const [passengers, setPassengers] = useState([]);
  const [loading, setLoading] = useState(true);

  // Pobieranie lotów z API
  useEffect(() => {
    axios.get('http://localhost:8080/passengers/findAllPassengers')
      .then((response) => {
        console.log('Response from backend:', response); // Dodajemy logowanie
        setPassengers(response.data); // Ustawiamy dane lotów
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching passengers:", error);
        setLoading(false);
      });
  }, []);
  

  // Jeśli dane się ładują, pokazujemy komunikat
  if (loading) {
    return <div>Loading passengers...</div>;
  }

  return (
    <div>
      <h2>PassengerList List</h2>
      {passengers.length === 0 ? (
        <p>No passengers found.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Passport ID</th>
              <th>Name</th>
              <th>Surname</th>
              <th>Birth Date</th>
              <th>Flights</th>
            </tr>
          </thead>
          <tbody>
            {passengers.map(passenger => (
              <tr key={passenger.idPassenger}>
                <td>{passenger.idPassenger}</td>
                <td>{passenger.passportId}</td>
                <td>{passenger.name}</td>
                <td>{passenger.surname}</td>
                <td>{passenger.birthDate}</td>
                <td>{passenger.flightIds.join(", ")}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};
export default PassengerList;