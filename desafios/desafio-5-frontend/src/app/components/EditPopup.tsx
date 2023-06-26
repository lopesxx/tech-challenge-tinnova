"use client";
import React, { useEffect, useState } from 'react';

interface EditPopupProps {
  vehicle?: Vehicle;
  onSave: (updatedItem: Vehicle) => void;
  onClose: () => void;
}

interface Marca {
  nome: string;
}

const EditPopup: React.FC<EditPopupProps> = ({ vehicle, onSave, onClose }) => {
  const [marcas, setMarcas] = useState<Marca[]>([]);
  const id = !!vehicle ? vehicle.id : -1;
  const [marca, setMarca] = useState<string>(!!vehicle ? vehicle.marca : '');
  const [descricao, setDescricao] = useState<string>(!!vehicle ? vehicle.descricao : '');
  const [cor, setCor] = useState<string>(!!vehicle ? vehicle.cor : '');
  const [ano, setAno] = useState<number>(!!vehicle ? vehicle.ano : 0);
  const [vendido, setVendido] = useState<boolean>(!!vehicle ? vehicle.vendido : false);

  const [btnColor, setBtnColor ] = useState('bg-blue-500');
  const handleMarcaChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setMarca(e.target.value);
  };

  const handleDescricaoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDescricao(e.target.value);
  };

  const handleCorChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCor(e.target.value);
  };

  const handleAnoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAno(e.target.valueAsNumber);
  };

  const handleVendidoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setVendido(e.target.checked);
  };
 
  const [warn, setWarn] = useState(false);
  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const updatedItem = { id, marca, descricao, cor, ano, vendido };

    
    if(!!!marca || !!!descricao || !!!cor || !!!ano){
      setBtnColor('bg-red-900')
      setWarn(true);
    } else {
      onSave(updatedItem);
    }

  };

  useEffect(() => {
        const fetchMarcas = async () => {
      try {
          const response = await fetch('http://localhost:8080/veiculos/marcas')
          const data = await response.json();
          setMarcas(data);
      } catch (e){
          console.log(e);
      }
    }

    fetchMarcas();
  }, []);

  return (
    <div className="fixed top-0 left-0 flex items-center justify-center w-screen h-screen bg-gray-800 bg-opacity-75">
      <div className="bg-white rounded p-8">
        <h2 className="text-xl font-bold mb-4">Editar Item</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="marca" className="block mb-1">
              Marca:
            </label>

            <select onChange={handleMarcaChange}>
              <option>
                  Selecionar
                </option>
              {marcas.map((item, index) => (
                <option key={index} value={item.nome}>
                  {item.nome}
                </option>
              ))}
            </select>
          </div>
          <div className="mb-4">
            <label htmlFor="descricao" className="block mb-1">
              Descrição:
            </label>
            <input
              type="text"
              id="descricao"
              value={descricao}
              onChange={handleDescricaoChange}
              className="w-full border rounded py-2 px-3"
            />
          </div>
          <div className="mb-4">
            <label htmlFor="ano" className="block mb-1">
              Ano:
            </label>
            <input
              type="number"
              id="ano"
              value={ano}
              onChange={handleAnoChange}
              className="w-full border rounded py-2 px-3"
            />
          </div>
          <div className="mb-4">
            <label htmlFor="cor" className="block mb-1">
              Cor:
            </label>
            <input
              type="text"
              id="cor"
              value={cor}
              onChange={handleCorChange}
              className="w-full border rounded py-2 px-3"
            />
          </div>
          <div className="mb-4">
            <label htmlFor="vendido" className="block mb-1">
              Vendido:
            </label>
            <input
              type="checkbox"
              id="vendido"
              checked={vendido}
              onChange={handleVendidoChange}
            />
          </div>
          {warn ? <p>Informe todos os campos!</p> : <></>}
          <div className="flex justify-end">
            <button type="submit" className={`${btnColor} text-white px-4 py-2 rounded`}>
              Salvar
            </button>
            <button onClick={() => onClose()} className="ml-2 px-4 py-2">
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditPopup;
