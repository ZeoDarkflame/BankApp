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



function App() {
  return (
<<<<<<< HEAD
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
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/admin" element={<AdminLogin />} />
        <Route path="/admindashboard" element={<AdminDashboard />} />
        </Routes>
      </div>
    </Router>
=======
    <div className="App">
      <h1>BankingApp</h1>
      <Router>

        <div className="container">
          <nav>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/login">Login</Link>
              </li>
              <li>
                <Link to="/signup">Sign Up</Link>
              </li>
              <li>
                <Link to="/login">Admin</Link>
              </li>
            </ul>
          </nav>

          <Routes>
            <Route path="/login" element={<LoginForm/>}/>
            <Route path="/signup" element={<Register/>}/>
          </Routes>
    
          {/* <Route path = "/dashboard" element = {<Dashboard/>}/>
          <Route path = "/login" element = {<LoginForm/>}/>   */}

            
       
         
    
        </div>
          
      
      </Router>
    </div>
>>>>>>> 521133d2592f03290098e8c7d16c199ad082fc28
  );
}

export default App;
