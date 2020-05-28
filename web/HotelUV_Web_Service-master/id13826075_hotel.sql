-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-05-2020 a las 05:18:02
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id13826075_hotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Peticiones`
--

CREATE TABLE `Peticiones` (
  `id_peticion` int(11) NOT NULL,
  `habitacion` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `estado` int(10) NOT NULL DEFAULT 0,
  `pluces` int(11) DEFAULT NULL,
  `ptemperatura` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pllaves` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Peticiones`
--

INSERT INTO `Peticiones` (`id_peticion`, `habitacion`, `estado`, `pluces`, `ptemperatura`, `pllaves`) VALUES
(21, '1', 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Servicios`
--

CREATE TABLE `Servicios` (
  `id` int(11) NOT NULL,
  `habitacion` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `luces` int(10) NOT NULL,
  `temperatura` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `llaves` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Servicios`
--

INSERT INTO `Servicios` (`id`, `habitacion`, `luces`, `temperatura`, `llaves`) VALUES
(1, '1', 1, '25', 1),
(2, '2', 1, '25', 0),
(3, '3', 1, '30', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Peticiones`
--
ALTER TABLE `Peticiones`
  ADD PRIMARY KEY (`id_peticion`);

--
-- Indices de la tabla `Servicios`
--
ALTER TABLE `Servicios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Peticiones`
--
ALTER TABLE `Peticiones`
  MODIFY `id_peticion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `Servicios`
--
ALTER TABLE `Servicios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
