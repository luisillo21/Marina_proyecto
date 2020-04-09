<?php
header('Content-type: application/json');
require 'BASE/BDD.php';
include "CORE/Rol.php";

$id = isset($_POST['rol'])?$_POST['rol']:null;

if($id){
    $id = "where rol_name='$id'";
}else{
    $id= "";
}
$data = BDD::QUERY("select * from rol $id");

foreach ($data as $v)
{
    $usuario = new Rol($v['idrol'],$v['rol_nombre']
        ,$v['estado']);
    $array[] = $usuario;
}

if($array){
    $datas = array("rol"=>$array);
    echo json_encode($datas, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
}else{
    echo json_encode("error", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
}