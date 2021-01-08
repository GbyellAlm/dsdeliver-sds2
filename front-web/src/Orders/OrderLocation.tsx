import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import AsyncSelect from "react-select/async";
import { useState } from 'react'
import { fetchLocalMapBox } from "../api";
import { OrderLocationdata } from './types'

const initialPosition = {
  lat: -26.9161329,
  lng: -48.6665175,
};

// Tipo de dados q representa o local q estou
// esse "?" dps do label significa q a propriedade label n eh obrigatoria
// O value eh o valor do select e o label eh o q vai ser mostrado na tela, do select
type Place = {
    label?: string;
    value?: string;
    position: {
        lat: number;
        lng: number;
    }
}

type Props = {
    onChangeLocation: (location: OrderLocationdata) => void;
}

function OrderLocation({onChangeLocation}: Props) {
  // Endereco q foi selecionado pelo usu
  const [address, setAddress] = useState<Place>({
      position: initialPosition
  });
  

    // Codigos do select (select é a caixa de texto do mapa pra selecionar um endereco pra entrega do pedido)
  const loadOptions = async (
    inputValue: string,
    callback: (places: Place[]) => void
  ) => {
    const response = await fetchLocalMapBox(inputValue);

    const places = response.data.features.map((item: any) => {
      return {
        label: item.place_name,
        value: item.place_name,
        position: {
          lat: item.center[1],
          lng: item.center[0],
        }
      };
    });

    callback(places);
  };

  const handleChangeSelect = (place: Place) => {
    setAddress(place);
    
    onChangeLocation({
      latitude: place.position.lat,
      longitude: place.position.lng,
      address: place.label!
    });
    
  };

  return (
    <div className="order-location-container">
      <div className="order-location-content">
        <h3 className="order-location-title">
          Selecione onde o pedido deve ser entregue:
        </h3>
        <div className="filter-container">
          <AsyncSelect
            placeholder="Digite um endereço para entregar o pedido"
            className="filter"
            loadOptions={loadOptions}
            onChange={value => handleChangeSelect(value as Place)}
          />
        </div>
        <MapContainer center={address.position} zoom={13} scrollWheelZoom key={address.position.lat}>
          <TileLayer
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker position={address.position}>
            <Popup>
              {address.label}
            </Popup>
          </Marker>
        </MapContainer>
      </div>
    </div>
  );
}

export default OrderLocation;