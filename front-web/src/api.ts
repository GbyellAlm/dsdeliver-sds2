// INICIALIZA A LISTA DE PRODUTOS PRO CICLO DE VIDA DO COMPONENTE

import axios from "axios";

// Armazenei essa url numa constante pq vamos usar ela em outros momentos tb
const API_URL = 'http://localhost:8080'

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