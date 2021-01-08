import './styles.css'
import StepsHeader from './StepsHeader'
import ProductsList from './ProductsList'
import { useEffect, useState } from 'react'
import { Product, OrderLocationdata } from './types'
import { fetchProducts } from '../api'
import OrderLocation from './OrderLocation'

function Orders() {
    // Estado q armazena a lista de produtos
    // <Product[]> ali é o tipo do dado do estado. Ele tem esse [] pq ele é uma lista tb
    const [products, setProducts] = useState<Product[]>([]);
    console.log(products);

    // Estado q representa a localizacao escolhida pelo usu
    const [orderLocation, setOrderLocation] = useState<OrderLocationdata>();

    useEffect(() => {
        // O .data é do axios e é onde ele armazena a lista de produtos
        fetchProducts()
            .then(response => setProducts(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div className="orders-container">
            <StepsHeader />
            <ProductsList products={products}/>
            <OrderLocation onChangeLocation={location => setOrderLocation(location)}/>
        </div>
    )
}

export default Orders;