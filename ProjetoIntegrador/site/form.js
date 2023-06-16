document.getElementById("contactForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que a página seja recarregada ao enviar o formulário

    // Obtem os valores dos campos do formulário
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var country = document.getElementById("country").value;
    var message = document.getElementById("message").value;

    // Simula o envio do formulário exibindo os valores na página
    var output = "Nome: " + name + "<br>";
    output += "Email: " + email + "<br>";
    output += "Country: " + country + "<br>";
    output += "Mensagem: " + message;

    document.getElementById("contactForm").innerHTML = output;
});
