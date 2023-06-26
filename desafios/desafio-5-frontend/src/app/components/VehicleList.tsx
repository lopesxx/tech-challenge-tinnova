"use client";
import { useContext, useEffect, useState } from "react";
import axios from 'axios';
import EditPopup from "./EditPopup";
import { VehicleContext } from "../AppContext";
import Button from "./Button";

const VehicleList = () => {
    const { vehicles, updateVeiculos } = useContext(VehicleContext) as VehicleType;

    const [filter, setFilter] = useState('');
    const [filteredVehicles, setFilteredVehicles] = useState(vehicles);

    useEffect(() => {
        setFilteredVehicles(
          vehicles.filter((vehicle: Vehicle) =>
            Object.values(vehicle).some((value) =>
            value !== null && value.toString().toLowerCase().includes(filter.toLowerCase())
            )
          )
        );
      }, [vehicles, filter]);

    useEffect(() => {

        const fetchVehicles = async () => {
          try {
            const response = await fetch('http://localhost:8080/veiculos');
            const data = await response.json();
            updateVeiculos(data);
          } catch (error) {
            console.error('Erro ao buscar veículos:', error);
          }
        };
    
        fetchVehicles();
      }, []);

      const [showPopup, setShowPopup] = useState(false);
      const [vehicleToEdit, setVehicleToEdit] = useState<Vehicle>();
      const openPopup = (vehicle: Vehicle) => {
        setVehicleToEdit(vehicle);
        setShowPopup(true);
      };
    
      const savePopup = (vehicleUpdated: Vehicle) => {
        axios.put(`http://localhost:8080/veiculos/${vehicleUpdated.id}`, vehicleUpdated)
        .then(() => updateVeiculos(vehicles.map(item => item.id === vehicleUpdated.id ? vehicleUpdated : item)))
        .catch((error: string) => console.error(error));

        setVehicleToEdit(undefined);
        setShowPopup(false);
      };

      const closePopup = () => setShowPopup(false);


    const handleDelete = (id: number) => {
        axios.delete(`http://localhost:8080/veiculos/${id}`)
        .then(() => updateVeiculos(vehicles.filter((vehicle:Vehicle) => vehicle.id !== id)))
        .catch((error: string) => console.error(error));
    };

    return (
     <div>
       <div className="mb-4 mt-5">
            <label htmlFor="descricao" className="block mb-1">
              Buscar veículo:
            </label>
            <input
              type="text"
              value={filter}
              defaultValue=""
              onChange={(e) => setFilter(e.target.value)}
              placeholder="Ex: Ford"
              className="w-full border rounded py-2 px-3"
            />
          </div>

     <ul role="list" className="mt-10 divide-y divide-gray-100">
        {filteredVehicles.map((vehicle: Vehicle) => (
          <li key={vehicle.marca} className="flex justify-between gap-x-6 py-5">
            <div className="flex gap-x-4">
              <div className="min-w-0 flex-auto">
                <p className="text-sm font-semibold leading-6 text-gray-900">Marca: {vehicle.marca}</p>
                {vehicle.vendido 
                    ? <p className="text-sm leading-6 text-red-600">Vendido</p> 
                    : <p className="text-sm leading-6 text-green-600">Disponível</p>
                 }
                <p className="mt-1 truncate text-xs leading-5 text-gray-500">Ano: {vehicle.ano}</p>
                <p className="mt-1 truncate text-xs leading-5 text-gray-500">Cor: {vehicle.cor}</p>
                <p className="mt-1 truncate text-xs leading-5 text-gray-500">Descrição: {vehicle.descricao}</p>
             
              </div>
            </div>
            <div className="hidden sm:flex sm:flex-col sm:items-end">       
            <div className="mt-2 flex-row">
                <Button color="bg-yellow-600" title="Editar" onClick={() => openPopup(vehicle)}/>
                <Button color="bg-red-900" title="Excluir" onClick={() => handleDelete(vehicle.id)}/>
            </div>
           </div>
          </li>
        ))}
      </ul>
      {showPopup && <EditPopup vehicle={vehicleToEdit} onSave={savePopup} onClose={closePopup}/>}
     </div>
    )
  }
  

export default VehicleList;