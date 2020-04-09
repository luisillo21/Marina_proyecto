<?php


class Rol
{
    public $idrol;
    public $rol_nombre;
    public $estado;

    /**
     * Rol constructor.
     * @param $idrol
     * @param $rol_nombre
     * @param $estado
     */
    public function __construct($idrol, $rol_nombre, $estado)
    {
        $this->idrol = $idrol;
        $this->rol_nombre = $rol_nombre;
        $this->estado = $estado;
    }


    /**
     * @return mixed
     */
    public function getIdrol()
    {
        return $this->idrol;
    }

    /**
     * @param mixed $idrol
     */
    public function setIdrol($idrol)
    {
        $this->idrol = $idrol;
    }

    /**
     * @return mixed
     */
    public function getRolNombre()
    {
        return $this->rol_nombre;
    }

    /**
     * @param mixed $rol_nombre
     */
    public function setRolNombre($rol_nombre)
    {
        $this->rol_nombre = $rol_nombre;
    }



}