import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';
import Login from './components/Login.js';
import './components/Login.css';
import Signup from './components/Signup.js';
import './components/Signup.css';
import Dashboard from './components/Dashboard.js';
import './components/Dashboard.css';
import AdminDashboard from './components/Admindashboard';
import AdminLogin from './components/Adminlogin';
import './components/Admindashboard.css';
import './components/Adminlogin.css';
import TestComponent from './components/TestComponent';


function App() {
  return (
    <Router>
      <div className="App">
        <nav className="navbar">
          <ul className="nav-list">
            
            <li className="nav-item"><Link to="/login">Login</Link></li>
            <li className="nav-item"><Link to="/signup">Signup</Link></li>
            <li className="nav-item"><Link to="/admin"> Admin</Link></li>
          </ul>
          <h1>Welcome to Wells Fargo Net Banking</h1>
        </nav>
        <Routes>
        
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/dashboCard" element={<Dashboard />} />
        <Route path="/admin" element={<AdminLogin />} />
        <Route path="/admindashboard" element={<AdminDashboard />} />
        <Route path="/testcomponent" element={<TestComponent/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
