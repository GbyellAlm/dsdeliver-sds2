// Inicializa a lista de produtos pro ciclo de vida do componente
import axios from "axios";
import { OrderPayload } from "./Orders/types";

const API_URL= process.env.REACT_APP_API_URL;

const mapboxToken = process.env.REACT_APP_ACCESS_TOKEN_MAP_BOX;

// Faz requisição dos produtos no backend
export function fetchProducts() {
    // ` ${API_URL}/products` Forma mais moderna q tem no JS p/ concatenar uma variavel com uma string n
    return axios(` ${API_URL}/products`)
}

// API PRA SE COMUNICAR C/ O MAPBOX
export function fetchLocalMapBox(local: string) {
    return axios(`https://api.mapbox.com/geocoding/v5/mapbox.places/${local}.json?access_token=${mapboxToken}`)
}

// Salvando o pedido feito pelo usu 
export function saveOrder(payload:OrderPayload) {
    return axios.post(` ${API_URL}/orders`, payload);
}