import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import validation from './Validation'
function LoginForm()
{
    const [values, setValues] = useState({
        name: '',
        password: ''
    })
    const navigate=useNavigate();

    const [error,setError] = useState({})

    function handleChange(e){
        setValues({...values,[e.target.name]:e.target.value})
    }
    function handleSubmit(e) {
        e.preventDefault();
        // setError(validation(values));
    }
    return(
        <div classname='wrap'>
            <form onSubmit={handleSubmit}>
                <div className="container">
                    <label><b>UserName</b></label>
                    <input type="text" placeholder="Enter Username" value={values.name} name="name" onChange={handleChange}/>
                    {error.name && <p style={{color:"red", fontSize:"13px"}}>{error.name}</p>}
                    <label><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" value={values.password} name="password" onChange={handleChange}/>
                    {error.password && <p style={{color:"red", fontSize:"13px"}}>{error.password}</p>}

                    
                    <button type="submit">login</button>
                    <input type="checkbox" checked="checked" /> Remember me

                    <div className="container" style={{backgroundColor:"#f1f1f1"}}>
                    <button type="button" className="cancelbtn">Cancel</button>
                    <span className="psw">Forgot <a href="#">password</a></span>
                    </div>


                </div>
            </form>
        </div>
    )
}

export default LoginForm;