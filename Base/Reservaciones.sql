-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-04-2020 a las 20:42:17
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reservaciones`
--
CREATE DATABASE IF NOT EXISTS `reservaciones` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `reservaciones`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_reservacion`
--

CREATE TABLE `detalle_reservacion` (
  `id_detalle` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_reservacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservacion`
--

CREATE TABLE `reservacion` (
  `id_reservacion` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `observacion` varchar(45) DEFAULT NULL,
  `cant_asientos` int(11) DEFAULT NULL,
  `hora` varchar(45) DEFAULT NULL,
  `reservado` varchar(5) DEFAULT 'NO',
  `estado` varchar(1) DEFAULT 'A'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reservacion`
--

INSERT INTO `reservacion` (`id_reservacion`, `nombre`, `observacion`, `cant_asientos`, `hora`, `reservado`, `estado`) VALUES
(1, 'nelio', 'nelio', 3, 'hoy', 'NO', 'A'),
(45, 'nelio', 'nelio', 3, 'hoy', 'NO', 'A'),
(46, 'nueva', 'prueba', 12, '18:30', 'NO', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL,
  `rol_nombre` varchar(45) DEFAULT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'ACTIVO'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idrol`, `rol_nombre`, `estado`) VALUES
(1, 'admin', 'ACTIVO'),
(2, 'cliente', 'ACTIVO'),
(3, 'profesor', 'ACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `cedula` varchar(12) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  `estado` varchar(1) DEFAULT 'A',
  `rol` int(11) DEFAULT NULL,
  `idusuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`cedula`, `nombre`, `apellido`, `usuario`, `clave`, `estado`, `rol`, `idusuario`) VALUES
('0945887754', 'Mario', 'Martinez', 'mario343', '1234', 'A', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `detalle_reservacion`
--
ALTER TABLE `detalle_reservacion`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `fk_reservaciones_idx` (`id_reservacion`),
  ADD KEY `fk_usuario_idx` (`id_usuario`);

--
-- Indices de la tabla `reservacion`
--
ALTER TABLE `reservacion`
  ADD PRIMARY KEY (`id_reservacion`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `fk_rol_idx` (`rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalle_reservacion`
--
ALTER TABLE `detalle_reservacion`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reservacion`
--
ALTER TABLE `reservacion`
  MODIFY `id_reservacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_reservacion`
--
ALTER TABLE `detalle_reservacion`
  ADD CONSTRAINT `fk_reservaciones` FOREIGN KEY (`id_reservacion`) REFERENCES `reservacion` (`id_reservacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_rol` FOREIGN KEY (`rol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
