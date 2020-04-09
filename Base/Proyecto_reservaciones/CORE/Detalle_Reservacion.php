<?php


class Detalle_Reservacion
{
    public $id_detalle;
    public $id_usuario;
    public $id_reservacion;

    /**
     * Detalle_Reservacion constructor.
     * @param $id_detalle
     * @param $id_usuario
     * @param $id_reservacion
     */
    public function __construct($id_detalle, $id_usuario, $id_reservacion)
    {
        $this->id_detalle = $id_detalle;
        $this->id_usuario = $id_usuario;
        $this->id_reservacion = $id_reservacion;
    }

    /**
     * @return mixed
     */
    public function getIdDetalle()
    {
        return $this->id_detalle;
    }

    /**
     * @param mixed $id_detalle
     */
    public function setIdDetalle($id_detalle)
    {
        $this->id_detalle = $id_detalle;
    }

    /**
     * @return mixed
     */
    public function getIdUsuario()
    {
        return $this->id_usuario;
    }

    /**
     * @param mixed $id_usuario
     */
    public function setIdUsuario($id_usuario)
    {
        $this->id_usuario = $id_usuario;
    }

    /**
     * @return mixed
     */
    public function getIdReservacion()
    {
        return $this->id_reservacion;
    }

    /**
     * @param mixed $id_reservacion
     */
    public function setIdReservacion($id_reservacion)
    {
        $this->id_reservacion = $id_reservacion;
    }


}