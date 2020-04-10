<?php
header('Content-type: application/json');
require 'BASE/BDD.php';
include "CORE/Usuario.php";

$id = isset($_GET['usuario'])?$_GET['usuario']:null;

if($id){
    $id = "where usuario='$id'";
}else{
    $id= "";
}
$data = BDD::QUERY("select * from usuario $id");

foreach ($data as $v)
{
    $usuario = new Usuario($v['cedula'],$v['nombre']
        ,$v['apellido'],$v['usuario'],$v['clave'],$v['rol'],$v['estado'],$v['idusuario']);
    $array[] = $usuario;
}

if($array){
    $datas = array("usuario"=>$array);
    echo json_encode($datas, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
}else{
    echo json_encode("error", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
}