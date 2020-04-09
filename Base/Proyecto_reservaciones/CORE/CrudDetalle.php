<?php


class CrudDetalle
{
    public static function Insertar($array)
    {
        $id = BDD::INSERTAR_DESDE_ARRAY("detalle_reservacion", $array);
        if ($id) {
            return $array = array("id" => $id);
        } else {
            return $array = array();
        }
    }

    public static function Actualizar($array, $id)
    {
        if (BDD::ACTUALIZAR_DESDE_ARRAY("detalle_reservacion", $array, "id_detalle=$id")) {
            return $array = array("id" => $id);
        } else {
            return $array = array();
        }
    }
}