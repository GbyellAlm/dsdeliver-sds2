// ARQUIVO Q CONTEM ALGORITMOS Q N QUEREMOS BOTAR NOS ARQUIVOS NORMAIS PRA ENXUGAR UM POUCO O TAMANHO DO CÓDIGO

import { Product } from "./types"

export function checkIsSelected(selectedProducts: Product[], product: Product) {
    return selectedProducts.some(
        (item) => item.id === product.id
      );
}

// Formata os preços pro estilo da nossa moeda direitinho. Sem isso, os preços iam ser tipo 38.9 ou só 38 qdo for 38,00
export function formatPrice(price: number) {
  const formatter = new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
  });

  return formatter.format(price);
}