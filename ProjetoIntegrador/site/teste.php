<?php

header("Access-Control-Allow-Origin: *");
header('Cache-Control: no-cache, must-revalidate'); 
header("Content-Type: text/plain; charset=UTF-8");
header("HTTP/1.1 200 OK");

$dados = json_decode(file_get_contents("php://input"));

$produto = array(

    "codigo" => 1,
    "produto" => "Arroz",
    "valor" => 12.50

);

if($dados->codigo == 1){

        echo json_encode($produto);

}else{

    echo "Aconteceu este erro ".http_response_code(400);

}

?>