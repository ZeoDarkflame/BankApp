import React, {useState,useEffect} from 'react';
import axios from 'axios';

function TestComponent(){
    const url = "http://localhost:8080/customer/customer/1";

    const [data,setData] = useState([]);

    const fetchInfo = async () => {
        var getres;
        const resp = await axios.get(url).then((res) => getres);
        console.log("debug data:")
        console.log(getres);
        console.log(resp)
        resp.catch((error) => console.log(error));
        setData(getres.data);
        
        return resp;
    };

    useEffect (() => {
        fetchInfo();
    },[]);

    return (
        <div className="TestComponent">
            <h1>Axios Test</h1>
            <center>
                {data.map((dataObj,index) => {
                    return (
                        <div>
                            <p>{dataObj.name}</p>
                        </div>
                    );
                })}
            </center>
        </div>
    );
}

export default TestComponent;