<?php


class CrudReservacion
{
    public static function Insertar($array)
    {
        $id = BDD::INSERTAR_DESDE_ARRAY("reservacion", $array);
        if ($id) {
            return $array = array("id" => $id);
        } else {
            return $array =  array("id"=>"");
        }
    }

    public static function Actualizar($array, $id)
    {
        if (BDD::ACTUALIZAR_DESDE_ARRAY("reservacion", $array, "id_reservacion=$id")) {
            return $array = array("id" => $id);
        } else {
            return $array =  array("id"=>"");
        }
    }
}