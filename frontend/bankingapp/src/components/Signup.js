import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Signup.css'; // You can create this CSS file for styling
import {toast} from 'react-toastify';
function Signup() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [aadhar, setAadhar] = useState('');
  const [address, setAddress] = useState('');
  const [dob, setDob] = useState('');
  const [phone, setPhone] = useState('');
  
  const [errors, setErrors] = useState({});

  const navigate = useNavigate();
  const validateEmail = () => {
    if (!email) {
      setErrors({ ...errors, email: 'Email is required' });
    } else {
      setErrors({ ...errors, email: '' });
    }
  };

  const validatePassword = () => {
    if (password.length < 6) {
      setErrors({ ...errors, password: 'Password must be at least 6 characters long' });
    } else {
      setErrors({ ...errors, password: '' });
    }
  };

  const validateAadhar = () => {
    if (aadhar.length !== 12) {
      setErrors({ ...errors, aadhar: 'Aadhar number must be 16 digits' });
    } else {
      setErrors({ ...errors, aadhar: '' });
    }
  };

  const validateForm = () => {
    const validationErrors = {};
    validateEmail();
    validatePassword();
    validateAadhar();
    
    // Implement more validation rules here

    return validationErrors;
  };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     const validationErrors = validateForm();
    
//     if (Object.keys(validationErrors).length === 0) {
      
//     } else {
//       setErrors(validationErrors);
//     }
//   };
const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validateForm();
    let regobj = {username,email,password,aadhar,address,dob,phone};
    console.log(regobj);
    if (Object.keys(validationErrors).length === 0) {
      try {
        const response = await fetch('http://localhost:8000', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            username,
            email,
            password,
            aadhar,
            address,
            dob,
            phone,
          }),
        });
  
        if (response.ok) {
          console.log('User registered successfully');
          toast.success("Registered successfully");
          navigate('/login');
          // Optionally, you can redirect the user to another page
        } else {
          console.error('Error registering user');
          toast.error("Error registering user");
        }
      } catch (error) {
        console.error('Error:', error);
      }
    } else {
      setErrors(validationErrors);
    }
  };

  return (
    <div className="signup-container">
      <h2>Sign Up</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label>Username</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            onBlur={validateEmail}
            required
          />
          {errors.email && <span className="error-message">{errors.email}</span>}
        </div>
        <div className="input-group">
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            onBlur={validatePassword}
            required
          />
          {errors.password && <span className="error-message">{errors.password}</span>}
        </div>
        <div className="input-group">
          <label>Confirm Password</label>
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label>Aadhar Number</label>
          <input
            type="text"
            value={aadhar}
            onChange={(e) => setAadhar(e.target.value)}
            onBlur={validateAadhar}
            required
          />
          {errors.aadhar && <span className="error-message">{errors.aadhar}</span>}
        </div>
        <div className="input-group">
          <label>Address</label>
          <input
            type="text"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label>Date of Birth</label>
          <input
            type="date"
            value={dob}
            onChange={(e) => setDob(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label>Phone Number</label>
          <input
            type="tel"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            required
          />
        </div>
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
}

export default Signup;
