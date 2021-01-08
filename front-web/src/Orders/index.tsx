import "./styles.css";
import StepsHeader from "./StepsHeader";
import ProductsList from "./ProductsList";
import { useEffect, useState } from "react";
import { Product, OrderLocationData } from "./types";
import { fetchProducts, saveOrder } from "../api";
import OrderLocation from "./OrderLocation";
import OrderSummary from "./OrderSummary";
import Footer from "../Footer";
import { checkIsSelected } from "./helpers";
import { toast } from 'react-toastify';

function Orders() {
  // Estado q armazena a lista de produtos
  // <Product[]> ali é o tipo do dado do estado. Ele tem esse [] pq ele é uma lista tb
  const [products, setProducts] = useState<Product[]>([]);
  console.log(products);

  // Estado q tem tdos os produtos selecionados
  const [selectedProducts, setSelectedProducts] = useState<Product[]>([]);

  // Estado q representa a localizacao escolhida pelo usu
  const [orderLocation, setOrderLocation] = useState<OrderLocationData>();

  const totalPrice = selectedProducts.reduce((sum, item) => {
    return sum + item.price;
  }, 0);

  useEffect(() => {
    // O .data é do axios e é onde ele armazena a lista de produtos
    fetchProducts()
      .then((response) => setProducts(response.data))
      .catch((error) => {
        toast.warning("Erro ao listar produtos!");
      });
  }, []);

  const handleSelectProduct = (product: Product) => {
    const isAlreadySelected = checkIsSelected(selectedProducts, product);

    if (isAlreadySelected) {
      const selected = selectedProducts.filter(
        (item) => item.id !== product.id
      );
      setSelectedProducts(selected);
    } else {
      setSelectedProducts((previous) => [...previous, product]);
    }
  };

  // "Preparando o terreno" pra salvar o pedido no banco, isto é: pegando somente o atributo id do produto e tb pegando a localizacao pra entrega do pedido
  const handleSubmit = () => {
    const productsIds = selectedProducts.map(({ id }) => ({ id }));
    const payload = {
      ...orderLocation!,
      products: productsIds,
    };

    saveOrder(payload)
      .then((response) => {
        toast.error(`Pedido enviado com sucesso! Nº ${response.data.id}`);
        // limpa a lista de produtos selecionados
        setSelectedProducts([]);
      })
      .catch(() => {
        toast.warning("Erro ao enviar pedido");
      });
  };

  return (
    <div className="orders-container">
      <>
        <StepsHeader />
        <ProductsList
          products={products}
          onSelectedProduct={handleSelectProduct}
          selectedProducts={selectedProducts}
        />
        <OrderLocation
          onChangeLocation={(location) => setOrderLocation(location)}
        />
        <OrderSummary
          amount={selectedProducts.length}
          totalPrice={totalPrice}
          onSubmit={handleSubmit}
        />
        <Footer />
      </>
    </div>
  );
}

export default Orders;
