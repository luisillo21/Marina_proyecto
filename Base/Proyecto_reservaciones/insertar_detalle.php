<?php
include "CORE/CrudDetalle.php";
include "BASE/BDD.php";

$json = isset($_POST['json'])?$_POST['json']:false;

if($json){
    $var = json_decode($json,true);
    foreach ($var as $e) {
        $array = array("id_detalle" => $e['id_detalle']
        , "id_usuario" => $e['id_usuario']
        , "id_reservacion"=> $e['id_reservacion']
        );
        $idbase = $e['idbase'];
        if($idbase == 0)
            $id = CrudDetalle::Insertar($array);
        else
            $id = CrudDetalle::Actualizar($array,$idbase);
        echo json_encode($id,JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
    }
}else{
    echo json_encode(json_encode($json),JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);;
}