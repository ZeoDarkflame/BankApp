import React from "react";
import "./styles.css";

export default function App() {
  return (
    <div className="App">
        <h2>User Registration</h2>
      <form>
        <div className="form-control">
          <label>Title</label>
          <input type="text" name="Title" required/>
        </div>

        <div className="form-control">
          <label>First Name</label>
          <input type="text" name="First Name" required/>
        </div>

        <div className="form-control">
          <label>Last Name</label>
          <input type="text" name="Last Name" required/>
        </div>

        <div className="form-control">
          <label>Email</label>
          <input type="text" name="email" />
        </div>

        <div className="form-control">
          <label>Phone Number</label>
          <input type="tel" name="Phone Number" required/>
        </div>

        <div className="form-control">
          <label>Aadhar Card Number</label>
          <input type="number" name="Aadhar Card Number" required/>
        </div>

        <div className="form-control">
          <label>Date of Birth</label>
          <input type="date" name="Date of Birth" min="1923-01-01" max="2005-01-01" required/>
        </div>

        <div className="form-control">
          <label></label>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
}