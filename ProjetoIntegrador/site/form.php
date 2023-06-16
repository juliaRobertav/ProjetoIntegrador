<?php
if (isset($_POST['Email'])) {

    // Configuração do email de destino e assunto
    $email_to = "infosoftplaceSanrio@gmail.com";
    $email_subject = "New form submissions";

    // Função para exibir erros
    function problem($error)
    {
        echo "We are very sorry, but there were error(s) found with the form you submitted. ";
        echo "These errors appear below.<br><br>";
        echo $error . "<br><br>";
        echo "Please go back and fix these errors.<br><br>";
        die();
    }

    // Verifica se os campos necessários foram preenchidos
    if (
        !isset($_POST['Name']) ||
        !isset($_POST['Email']) ||
        !isset($_POST['Message'])
    ) {
        problem('We are sorry, but there appears to be a problem with the form you submitted.');
    }

    // Obtém os valores dos campos do formulário
    $name = $_POST['Name'];
    $email = $_POST['Email'];
    $message = $_POST['Message'];

    $error_message = "";
    $email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';

    // Valida o formato do email
    if (!preg_match($email_exp, $email)) {
        $error_message .= 'The Email address you entered does not appear to be valid.<br>';
    }

    $string_exp = "/^[A-Za-z .'-]+$/";

    // Valida o formato do nome
    if (!preg_match($string_exp, $name)) {
        $error_message .= 'The Name you entered does not appear to be valid.<br>';
    }

    // Valida o tamanho da mensagem
    if (strlen($message) < 2) {
        $error_message .= 'The Message you entered do not appear to be valid.<br>';
    }

    // Se houver erros, exibe a mensagem de erro
    if (strlen($error_message) > 0) {
        problem($error_message);
    }

    // Prepara a mensagem do email
    $email_message = "Form details below.\n\n";

    // Função para remover possíveis conteúdos maliciosos da string
    function clean_string($string)
    {
        $bad = array("content-type", "bcc:", "to:", "cc:", "href");
        return str_replace($bad, "", $string);
    }

    // Adiciona os valores dos campos à mensagem do email
    $email_message .= "Name: " . clean_string($name) . "\n";
    $email_message .= "Email: " . clean_string($email) . "\n";
    $email_message .= "Message: " . clean_string($message) . "\n";

    // Configura os cabeçalhos do email
    $headers = 'From: ' . $email . "\r\n" .
        'Reply-To: ' . $email . "\r\n" .
        'X-Mailer: PHP/' . phpversion();

    // Envia o email
    @mail($email_to, $email_subject, $email_message, $headers);
?>

    <!-- Exibe uma mensagem de agradecimento após o envio do formulário -->
    Thank you for contacting us. We will be in touch with you very soon.

<?php
}
?>
