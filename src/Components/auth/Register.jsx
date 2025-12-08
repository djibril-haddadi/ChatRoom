import { useState } from 'react';
import { createRoot } from 'react-dom/client';

export default function Register(){
    const [name, setName] = useState("");


    function handleChange(e) {
        setName(e.target.value);
    }

    function handleSubmit(e) {
        e.preventDefault();
        alert(name);
    }
    return(
        <div>
            <h1>Veuillez remplir les informations suivantes</h1>

            <form onSubmit={handleSubmit}>
                <input type={"text"}  value={name} onChange={handleChange}  placeholder={"Nom"} />
                <input  type={"text"}  value={surname} onChange={handleChange} placeholder={"Prénom"} />
            </form>
        </div>
    )
}