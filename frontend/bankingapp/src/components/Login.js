// import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import './Login.css'; // You can create this CSS file for styling
// import {toast} from 'react-toastify';
// function Login() {
//   const [email, setEmail] = useState('');
//   const [password, setPassword] = useState('');
//   const [emailError, setEmailError] = useState('');
//   const [passwordError, setPasswordError] = useState('');
//   const [loginError, setLoginError] = useState('');

//   const navigate = useNavigate(); // Access the navigate function


//   const validateEmail = () => {
//     const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//     if (!emailRegex.test(email)) {
//       setEmailError('Please enter a valid email address');
//     } else {
//       setEmailError('');
//     }
//     console.log("validateemail");
//   };

//   const validatePassword = () => {
//     if (password.length < 6) {
//       setPasswordError('Password must be at least 6 characters long');
//     } else {
//       setPasswordError('');
//     }
//     console.log("validatepassword");
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     validateEmail();
//     validatePassword();

//     if (emailError === '' && passwordError === '') {
      
//         // Simulated successful login
//         // Replace with actual backend authentication logic
//         fetch("http://localhost:8000/user/"+email).then((res)=>{
//           return res.json();
//         }).then((resp)=>{
//           console.log(resp)
//           if(Object.keys(resp).length==0){
//             toast.error('Please enter valid user');
//           }else{
//             if(resp.password === password)
//             {
//               toast.success('Success');
//               navigate('/dashboard')
//             }else{
//               toast.error('Please enter valid credentials');
//             }
//           }
//         }).catch((err)=> {
//           toast.error('Login failed due to :'+ err.message);
//         });
      
//   }

//   return (
//     <div className="login-container">
//       <h2>Login</h2>
//       <form onSubmit={handleSubmit}>
//         <div className="input-group">
//           <label>Email</label>
//           <input
//             type="email"
//             value={email}
//             onChange={(e) => setEmail(e.target.value)}
//             onBlur={validateEmail}
//             required
//           />
//           {emailError && <span className="error-message">{emailError}</span>}
//         </div>
//         <div className="input-group">
//           <label>Password</label>
//           <input
//             type="password"
//             value={password}
//             onChange={(e) => setPassword(e.target.value)}
//             onBlur={validatePassword}
//             required
//           />
//           {passwordError && <span className="error-message">{passwordError}</span>}
//         </div>
//         {loginError && <span className="error-message">{loginError}</span>}
//         <button type="submit">Login</button>
//       </form>
//     </div>
//   );
// }
// }
// export default Login;
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css'; // You can create this CSS file for styling
import { toast } from 'react-toastify';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [loginError, setLoginError] = useState('');

  const navigate = useNavigate(); // Access the navigate function

  const validateEmail = () => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setEmailError('Please enter a valid email address');
    } else {
      setEmailError('');
    }
    console.log("validateemail");
  };

  const validatePassword = () => {
    if (password.length < 6) {
      setPasswordError('Password must be at least 6 characters long');
    } else {
      setPasswordError('');
    }
    console.log("validatepassword");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    validateEmail();
    validatePassword();
  
    if (emailError === '' && passwordError === '') {
      // Simulated successful login
      // Replace with actual backend authentication logic
      fetch("http://localhost:8000/user/" + email)
        .then((res) => {
          return res.json();
        })
        .then((resp) => {
          console.log(resp);
          if (Object.keys(resp).length === 0) {
            toast.error('Please enter valid user');
          } else {
            if (resp.password === password) {
              toast.success('Success');
              navigate('/dashboard');
            } else {
              toast.error('Invalid password');
            }
          }
        })
        .catch((err) => {
          toast.error('Login failed due to: ' + err.message);
        });
    }
  };
  
  // const handleSubmit = async (e) => {
  //   e.preventDefault();
  //   validateEmail();
  //   validatePassword();

  //   if (emailError === '' && passwordError === '') {
  //     // Simulated successful login
  //     // Replace with actual backend authentication logic
  //     fetch("http://localhost:8000/user/" + email)
  //       .then((res) => {
  //         return res.json();
  //       })
  //       .then((resp) => {
  //         console.log(resp);
  //         if (Object.keys(resp).length === 0) {
  //           toast.error('Please enter valid user');
  //         } else {
  //           if (resp.password === password) {
  //             toast.success('Success');
  //             navigate('/dashboard');
  //           } else {
  //             toast.error('Please enter valid credentials');
  //           }
  //         }
  //       })
  //       .catch((err) => {
  //         toast.error('Login failed due to :' + err.message);
  //       });
  //   }
  // };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            onBlur={validateEmail}
            required
          />
          {emailError && <span className="error-message">{emailError}</span>}
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
          {passwordError && <span className="error-message">{passwordError}</span>}
        </div>
        {loginError && <span className="error-message">{loginError}</span>}
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
