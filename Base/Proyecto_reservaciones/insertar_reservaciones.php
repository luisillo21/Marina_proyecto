<?php
include "CORE/CrudReservacion.php";
include "BASE/BDD.php";
$json = isset($_POST['json'])?$_POST['json']:false;

if($json){
    $var = json_decode($json,true);
    foreach ($var as $e) {
        $array = array("observacion" => $e['observacion']
        , "nombre" => $e['nombre']
        , "cant_asientos"=> $e['cant_asientos']
        , "hora" => $e['hora']
        , "reservado" => $e['reservado']
        );
        $idbase = $e['idbase'];
        if($idbase == 0)
            $id = CrudReservacion::Insertar($array);
        else
            $id = CrudReservacion::Actualizar($array,$idbase);
        echo json_encode($id,JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
    }
}else{
    echo json_encode(json_encode($json),JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);;
}