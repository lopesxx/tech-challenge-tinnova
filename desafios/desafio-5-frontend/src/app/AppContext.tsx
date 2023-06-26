"use client";
import React, { createContext, useState } from "react";

const VehicleContext = createContext<VehicleType|null>(null);

const AppContextProvider: React.FC<any> = ({ children }) => {
  const [vehicles, setVehicles] = useState<Vehicle[]>([]); 

  const addVeiculo = (newData: Vehicle) => setVehicles((prevData) =>[...prevData, newData]);
  const updateVeiculos = (newData: Vehicle[]) => setVehicles(newData);

  const contextValue: VehicleType = {
    vehicles,
    addVeiculo,
    updateVeiculos
  };
    
 

  return (
    <VehicleContext.Provider value={contextValue}>
        {children}
    </VehicleContext.Provider>
  );
}

export {VehicleContext, AppContextProvider};