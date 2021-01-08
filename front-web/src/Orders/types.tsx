// SIMBOLIZAÇÃO DOS TIPOS DE DADOS Q ESTAMOS USANDO NO FRONTEND. É COMO SE FOSSE UMA CLASSE Products no backend

export type Product = {
    id: number;
    name: string;
    price: number;
    description: string;
    imageUri: string;
}

export type OrderLocationData = {
    latitude: number;
    longitude: number;
    address: string;
}

// Representa o payload q vou enviar pro backend
type ProductId = {
    id: number;
}

// & OrderLocationdata, esse & eh pq a estrutura de pedidos do backend eh os atributos do orderlocationdata + uma lista de pedidos
export type OrderPayload = {
    products: ProductId[];
} & OrderLocationData;