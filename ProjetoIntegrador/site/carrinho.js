// Verifica se o documento ainda está carregando
if (document.readyState == 'loading') {
  document.addEventListener('DOMContentLoaded', ready)
} else {
  ready()
}

// Inicializa o valor total
var totalAmount = "0,00"

// Função a ser executada quando o documento estiver pronto
function ready() {
  // Botão remover produto
  const removeCartProductButtons = document.getElementsByClassName("remove-product-button")
  for (var i = 0; i < removeCartProductButtons.length; i++) {
    removeCartProductButtons[i].addEventListener("click", removeProduct)
  }

  // Mudança valor dos inputs
  const quantityInputs = document.getElementsByClassName("product-qtd-input")
  for (var i = 0; i < quantityInputs.length; i++) {
    quantityInputs[i].addEventListener("change", checkIfInputIsNull)
  }

  // Botão add produto ao carrinho
  const addToCartButtons = document.getElementsByClassName("button-hover-background")
  for (var i = 0; i < addToCartButtons.length; i++) {
    addToCartButtons[i].addEventListener("click", addProductToCart)
  }

  // Botão comprar
  const purchaseButton = document.getElementsByClassName("purchase-button")[0]
  purchaseButton.addEventListener("click", makePurchase)
}

function removeProduct(event) {
  event.target.parentElement.parentElement.remove()
  updateTotal()
}

function checkIfInputIsNull(event) {
  if (event.target.value === "0") {
    event.target.parentElement.parentElement.remove()
  }

  updateTotal()
}

function addProductToCart(event) {
  const button = event.target
  const productInfos = button.parentElement.parentElement
  const productImage = productInfos.getElementsByClassName("product-image")[0].src
  const productName = productInfos.getElementsByClassName("product-title")[0].innerText
  const productPrice = productInfos.getElementsByClassName("product-price")[0].innerText

  const productsCartNames = document.getElementsByClassName("cart-product-title")
  for (var i = 0; i < productsCartNames.length; i++) {
    if (productsCartNames[i].innerText === productName) {
      productsCartNames[i].parentElement.parentElement.getElementsByClassName("product-qtd-input")[0].value++
      updateTotal()
      return
    }
  }

  let newCartProduct = document.createElement("tr")
  newCartProduct.classList.add("cart-product")

  newCartProduct.innerHTML =
    `
      <td class="product-identification">
        <img src="${productImage}" alt="${productName}" class="cart-product-image">
        <strong class="cart-product-title">${productName}</strong>
      </td>
      <td>
        <span class="cart-product-price">${productPrice}</span>
      </td>
      <td>
        <input type="number" value="1" min="0" class="product-qtd-input">
        <button type="button" class="remove-product-button">Remover</button>
      </td>
    `
  
  const tableBody = document.querySelector(".cart-table tbody")
  tableBody.append(newCartProduct)
  updateTotal()

  newCartProduct.getElementsByClassName("remove-product-button")[0].addEventListener("click", removeProduct)
  newCartProduct.getElementsByClassName("product-qtd-input")[0].addEventListener("change", checkIfInputIsNull)
}

function makePurchase() {
  if (totalAmount === "0,00") {
    alert("Your shopping cart is empty!")
  } else {   
    alert(
      `
        Thanks for the purchase!
        value of the order: $${totalAmount}\n
        Check back often :)
      `
    )

    document.querySelector(".cart-table tbody").innerHTML = ""
    updateTotal()
  }
}

// Atualizar o valor total do carrinho
function updateTotal() {
  const cartProducts = document.getElementsByClassName("cart-product")
  totalAmount = 0

  for (var i = 0; i < cartProducts.length; i++) {
    const productPrice = cartProducts[i].getElementsByClassName("cart-product-price")[0].innerText.replace("$", "").replace(",", ".")
    const productQuantity = cartProducts[i].getElementsByClassName("product-qtd-input")[0].value

    totalAmount += productPrice * productQuantity
  }
  
  totalAmount = totalAmount.toFixed(2)
  totalAmount = totalAmount.replace(".", ",")
  document.querySelector(".cart-total-container span").innerText = "$" + totalAmount
}


//API CEP
function cep(i){
  let aux = i.parentNode
  let cep = aux.getElementsByTagName("input")[0].value
  let btn = document.getElementById("fetchButton")

  fetch(`https://viacep.com.br/ws/${cep}/json/`)
  .then(response =>{
    response.json()
    .then(data => {console.log(data)
    btn.removeAttribute("disabled")
  aux.innerHTML += `<section id "apiteste">
  <h4>Your products will be delivered to:
  <h5>${data.logradouro},${data.bairro},${data.uf}<h4>
  </section>`})
  })

}
