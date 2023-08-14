import React, { useState, useEffect } from 'react';
import './Admindashboard.css';
function AdminDashboard() {
  const [userDetails, setUserDetails] = useState([]);

  useEffect(() => {
    // Fetch user details from your backend API
    // For example: fetchUserDetails().then(data => setUserDetails(data));
    const simulatedUserDetails = [
      { id: 1, username: 'user1', email: 'user1@example.com', balance: 1000 },
      { id: 2, username: 'user2', email: 'user2@example.com', balance: 2500 },
      // ... fetch more user details
    ];
    setUserDetails(simulatedUserDetails);
  }, []);

  return (
    <div className="admin-dashboard-container">
      <h2>Admin Dashboard</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Balance</th>
          </tr>
        </thead>
        <tbody>
          {userDetails.map(user => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td>${user.balance}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminDashboard;
