import logo from './logo.svg';
import {BrowserRouter as Router, Route,Routes, Link} from 'react-router-dom'
import './App.css';
import LoginForm from './components/LoginValidation/LoginForm';
import Dashboard from './components/Dashboard'
function App() {
  return (
    <div className="App">
      <h1>BankingApp</h1>
      <Router>

        <div className="container">
        <Routes>
          <Route path = "/dashboard" element = {<Dashboard/>}/>
          <Route path = "/login" element = {<LoginForm/>}/>  
        </Routes>
            
       
         
    
        </div>
    
      
      </Router>
    </div>
  );
}

export default App;
