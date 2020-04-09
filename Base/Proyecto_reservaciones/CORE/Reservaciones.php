<?php


class Reservaciones
{
    public $id_reservacion;
    public $nombre;
    public $observacion;
    public $cant_asientos;
    public $hora;
    public $reservado;
    public $estado;

    /**
     * Reservaciones constructor.
     * @param $id_reservacion
     * @param $nombre
     * @param $observacion
     * @param $cant_asientos
     * @param $hora
     * @param $reservado
     * @param $estado
     */
    public function __construct($id_reservacion, $nombre, $observacion, $cant_asientos, $hora, $reservado, $estado)
    {
        $this->id_reservacion = $id_reservacion;
        $this->nombre = $nombre;
        $this->observacion = $observacion;
        $this->cant_asientos = $cant_asientos;
        $this->hora = $hora;
        $this->reservado = $reservado;
        $this->estado = $estado;
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

    /**
     * @return mixed
     */
    public function getNombre()
    {
        return $this->nombre;
    }

    /**
     * @param mixed $nombre
     */
    public function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }

    /**
     * @return mixed
     */
    public function getObservacion()
    {
        return $this->observacion;
    }

    /**
     * @param mixed $observacion
     */
    public function setObservacion($observacion)
    {
        $this->observacion = $observacion;
    }

    /**
     * @return mixed
     */
    public function getCantAsientos()
    {
        return $this->cant_asientos;
    }

    /**
     * @param mixed $cant_asientos
     */
    public function setCantAsientos($cant_asientos)
    {
        $this->cant_asientos = $cant_asientos;
    }

    /**
     * @return mixed
     */
    public function getHora()
    {
        return $this->hora;
    }

    /**
     * @param mixed $hora
     */
    public function setHora($hora)
    {
        $this->hora = $hora;
    }

    /**
     * @return mixed
     */
    public function getReservado()
    {
        return $this->reservado;
    }

    /**
     * @param mixed $reservado
     */
    public function setReservado($reservado)
    {
        $this->reservado = $reservado;
    }

    /**
     * @return mixed
     */
    public function getEstado()
    {
        return $this->estado;
    }

    /**
     * @param mixed $estado
     */
    public function setEstado($estado)
    {
        $this->estado = $estado;
    }



}