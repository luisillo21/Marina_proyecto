<?php
header('Content-type: application/json');
require 'BASE/BDD.php';
include "CORE/Reservaciones.php";

$id = isset($_POST['usuario'])?$_POST['usuario']:null;

if($id){
    $id = "where nombre='$id'";
}else{
    $id= "";
}
$data = BDD::QUERY("select * from reservacion $id");

foreach ($data as $v)
{
    $usuario = new Reservaciones($v['id_reservacion'],$v['nombre']
        ,$v['observacion'],$v['cant_asientos'],$v['hora'],$v['reservado'],$v['estado']);
    $array[] = $usuario;
}

if($array){
    $datas = array("reservacion"=>$array);
    echo json_encode($datas, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
}else{
    echo json_encode("error", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
}