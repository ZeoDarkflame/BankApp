import logo from './logo.svg';
import React from "react";
import Navbar from "./components/navbar";
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter as Router, Route,Routes, Link,Switch} from 'react-router-dom'
import './App.css';
import LoginForm from './components/LoginValidation/LoginForm';
import Dashboard from './components/Dashboard'
function App() {
  return (
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
          </Routes>
    
          {/* <Route path = "/dashboard" element = {<Dashboard/>}/>
          <Route path = "/login" element = {<LoginForm/>}/>   */}

            
       
         
    
        </div>
    
      
      </Router>
    </div>
  );
}

export default App;
