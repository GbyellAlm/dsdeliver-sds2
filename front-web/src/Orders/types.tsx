// SIMBOLIZAÇÃO DOS TIPOS DE DADOS Q ESTAMOS USANDO NO FRONTEND. É COMO SE FOSSE UMA CLASSE Products no backend

export type Product = {
    id: number;
    name: string;
    price: number;
    description: string;
    imageUri: string;
}

export type OrderLocationdata = {
    latitude: number;
    longitude: number;
    address: string;
}