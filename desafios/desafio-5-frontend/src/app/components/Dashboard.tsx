"use client";
import { useContext, useState } from "react";
import EditPopup from "./EditPopup";
import VehicleList from "./VehicleList";
import axios from "axios";
import { VehicleContext } from "../AppContext";
import Button from "./Button";

const Dashboard = () => {
    const { vehicles, addVeiculo, updateVeiculos } = useContext(VehicleContext) as VehicleType;

    const [showPopup, setShowPopup] = useState(false);
    const openPopup = () => setShowPopup(true);
    const closePopup = () => setShowPopup(false);

    const savePopup = (vehicleToSave: Vehicle) => {
        axios.post(`http://localhost:8080/veiculos`, vehicleToSave)
        .then(resp => addVeiculo(resp.data))
        .catch((error: string) => console.error(error));
        setShowPopup(false);
    };

    return (            
        <div className='flex flex-col justify-center ml-56 mr-56 pt-10'>
            <p hidden>vehicleToSave</p>
            <div className='flex justify-between'>
                <h1 className='font-bold text-2xl'>Veiculos</h1>
                <Button color="bg-green-600" title="Cadastrar veículo" onClick={openPopup}/>
            </div>

            <VehicleList />
            {showPopup && <EditPopup onSave={savePopup} onClose={closePopup}/>}
        </div>
    );
}

export default Dashboard;