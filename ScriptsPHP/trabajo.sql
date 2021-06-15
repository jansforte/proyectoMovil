-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-06-2021 a las 18:36:07
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `trabajo`
--
CREATE DATABASE IF NOT EXISTS `trabajo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `trabajo`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datospersonales`
--

CREATE TABLE `datospersonales` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `ciudad` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `datospersonales`
--

INSERT INTO `datospersonales` (`id`, `Password`, `nombre`, `direccion`, `ciudad`, `tel`) VALUES
('admin', '1', 'admin', 'la', 'ba', '122'),
('jans', '12', 'johan', 'la', 'la', '3217749146');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `Id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `Password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`Id`, `Password`) VALUES
('admin', '1'),
('jans', '12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes_admin`
--

CREATE TABLE `mensajes_admin` (
  `id` int(11) NOT NULL,
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `code_mensaje` varchar(80) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `mensaje` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tipo_mensaje` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `hora_del_mensaje` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mensajes_admin`
--

INSERT INTO `mensajes_admin` (`id`, `user`, `code_mensaje`, `mensaje`, `tipo_mensaje`, `hora_del_mensaje`, `fecha`) VALUES
(1, 'jans', 'jans_205255927600', 'hola', '2', '20:52 , Friday, 15 de May de 2020', '2020-05-15 20:52:56'),
(2, 'jans', 'jans_205342911000', 'hola que se le ofrece?', '1', '20:53 , Friday, 15 de May de 2020', '2020-05-15 20:53:42'),
(3, 'jans', 'jans_223313161900', 'nada', '1', '22:33 , Monday, 14 de June de 2021', '2021-06-14 22:33:13'),
(4, 'jans', 'jans_223456218300', 'ni mergaaaa', '2', '22:34 , Monday, 14 de June de 2021', '2021-06-14 22:34:56'),
(5, 'jans', 'jans_223751885900', 'hola', '2', '22:37 , Monday, 14 de June de 2021', '2021-06-14 22:37:51'),
(6, 'jans', 'jans_224510447900', 'que paso?', '1', '22:45 , Monday, 14 de June de 2021', '2021-06-14 22:45:10'),
(7, 'jans', 'jans_224844753800', 'nada', '2', '22:48 , Monday, 14 de June de 2021', '2021-06-14 22:48:44'),
(8, 'jans', 'jans_224857070600', '????', '2', '22:48 , Monday, 14 de June de 2021', '2021-06-14 22:48:57'),
(9, 'jans', 'jans_23255819400', 'xD', '1', '23:02 , Monday, 14 de June de 2021', '2021-06-14 23:02:55'),
(10, 'jans', 'jans_233345777400', 'x3', '1', '23:33 , Monday, 14 de June de 2021', '2021-06-14 23:33:45'),
(11, 'jans', 'jans_233351822900', 'x3', '1', '23:33 , Monday, 14 de June de 2021', '2021-06-14 23:33:51'),
(12, 'jans', 'jans_9595205100', 'prueba', '1', '09:59 , Tuesday, 15 de June de 2021', '2021-06-15 09:59:05'),
(13, 'jans', 'jans_95945471200', 'prueba2', '1', '09:59 , Tuesday, 15 de June de 2021', '2021-06-15 09:59:45'),
(14, 'jans', 'jans_1008525000', 'prueba3', '1', '10:00 , Tuesday, 15 de June de 2021', '2021-06-15 10:00:08'),
(15, 'jans', 'jans_10041101400', 'funcione', '2', '10:00 , Tuesday, 15 de June de 2021', '2021-06-15 10:00:41'),
(16, 'jans', 'jans_10053276800', 'haber', '2', '10:00 , Tuesday, 15 de June de 2021', '2021-06-15 10:00:53'),
(17, 'jans', 'jans_10211708100', 'hola', '1', '10:02 , Tuesday, 15 de June de 2021', '2021-06-15 10:02:11'),
(18, 'jans', 'jans_10238013300', 'hola', '1', '10:02 , Tuesday, 15 de June de 2021', '2021-06-15 10:02:38'),
(19, 'jans', 'jans_102222669700', 'hola', '1', '10:22 , Tuesday, 15 de June de 2021', '2021-06-15 10:22:22'),
(20, 'jans', 'jans_102249494400', 'como', '1', '10:22 , Tuesday, 15 de June de 2021', '2021-06-15 10:22:49'),
(21, 'jans', 'jans_102534448200', 'como', '1', '10:25 , Tuesday, 15 de June de 2021', '2021-06-15 10:25:34'),
(22, 'jans', 'jans_102628943700', 'como2', '1', '10:26 , Tuesday, 15 de June de 2021', '2021-06-15 10:26:28'),
(23, 'jans', 'jans_102653923300', 'como2', '1', '10:26 , Tuesday, 15 de June de 2021', '2021-06-15 10:26:53'),
(24, 'jans', 'jans_105933621600', 'machetazo', '1', '10:59 , Tuesday, 15 de June de 2021', '2021-06-15 10:59:33'),
(25, 'jans', 'jans_105956615300', 'machetazo2', '1', '10:59 , Tuesday, 15 de June de 2021', '2021-06-15 10:59:56'),
(26, 'jans', 'jans_11141496300', 'machetazo2', '1', '11:01 , Tuesday, 15 de June de 2021', '2021-06-15 11:01:41'),
(27, 'jans', 'jans_11250705600', 'machetazo3', '1', '11:02 , Tuesday, 15 de June de 2021', '2021-06-15 11:02:50'),
(28, 'jans', 'jans_1136226800', 'machetazo4', '1', '11:03 , Tuesday, 15 de June de 2021', '2021-06-15 11:03:06'),
(29, 'jans', 'jans_11440268600', 'machetazo5', '1', '11:04 , Tuesday, 15 de June de 2021', '2021-06-15 11:04:40'),
(30, 'jans', 'jans_11449752900', 'machetazo6', '1', '11:04 , Tuesday, 15 de June de 2021', '2021-06-15 11:04:49'),
(31, 'jans', 'jans_11455707300', 'hola', '2', '11:04 , Tuesday, 15 de June de 2021', '2021-06-15 11:04:55'),
(32, 'jans', 'jans_11118666500', 'machetazo6', '2', '11:11 , Tuesday, 15 de June de 2021', '2021-06-15 11:11:08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes_jans`
--

CREATE TABLE `mensajes_jans` (
  `id` int(11) NOT NULL,
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `code_mensaje` varchar(80) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `mensaje` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tipo_mensaje` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `hora_del_mensaje` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mensajes_jans`
--

INSERT INTO `mensajes_jans` (`id`, `user`, `code_mensaje`, `mensaje`, `tipo_mensaje`, `hora_del_mensaje`, `fecha`) VALUES
(1, 'admin', 'admin_205255927600', 'hola', '1', '20:52 , Friday, 15 de May de 2020', '2020-05-15 20:52:55'),
(2, 'admin', 'admin_205342911000', 'hola que se le ofrece?', '2', '20:53 , Friday, 15 de May de 2020', '2020-05-15 20:53:42'),
(3, 'admin', 'admin_223313161900', 'nada', '2', '22:33 , Monday, 14 de June de 2021', '2021-06-14 22:33:13'),
(4, 'admin', 'admin_223456218300', 'ni mergaaaa', '1', '22:34 , Monday, 14 de June de 2021', '2021-06-14 22:34:56'),
(5, 'admin', 'admin_223751885900', 'hola', '1', '22:37 , Monday, 14 de June de 2021', '2021-06-14 22:37:51'),
(6, 'admin', 'admin_224510447900', 'que paso?', '2', '22:45 , Monday, 14 de June de 2021', '2021-06-14 22:45:10'),
(7, 'admin', 'admin_224844753800', 'nada', '1', '22:48 , Monday, 14 de June de 2021', '2021-06-14 22:48:44'),
(8, 'admin', 'admin_224857070600', '????', '1', '22:48 , Monday, 14 de June de 2021', '2021-06-14 22:48:57'),
(9, 'admin', 'admin_23255819400', 'xD', '2', '23:02 , Monday, 14 de June de 2021', '2021-06-14 23:02:55'),
(10, 'admin', 'admin_233345777400', 'x3', '2', '23:33 , Monday, 14 de June de 2021', '2021-06-14 23:33:45'),
(11, 'admin', 'admin_233351822900', 'x3', '2', '23:33 , Monday, 14 de June de 2021', '2021-06-14 23:33:51'),
(12, 'admin', 'admin_9595205100', 'prueba', '2', '09:59 , Tuesday, 15 de June de 2021', '2021-06-15 09:59:05'),
(13, 'admin', 'admin_95945471200', 'prueba2', '2', '09:59 , Tuesday, 15 de June de 2021', '2021-06-15 09:59:45'),
(14, 'admin', 'admin_1008525000', 'prueba3', '2', '10:00 , Tuesday, 15 de June de 2021', '2021-06-15 10:00:08'),
(15, 'admin', 'admin_10041101400', 'funcione', '1', '10:00 , Tuesday, 15 de June de 2021', '2021-06-15 10:00:41'),
(16, 'admin', 'admin_10053276800', 'haber', '1', '10:00 , Tuesday, 15 de June de 2021', '2021-06-15 10:00:53'),
(17, 'admin', 'admin_10211708100', 'hola', '2', '10:02 , Tuesday, 15 de June de 2021', '2021-06-15 10:02:11'),
(18, 'admin', 'admin_10238013300', 'hola', '2', '10:02 , Tuesday, 15 de June de 2021', '2021-06-15 10:02:38'),
(19, 'admin', 'admin_102222669700', 'hola', '2', '10:22 , Tuesday, 15 de June de 2021', '2021-06-15 10:22:22'),
(20, 'admin', 'admin_102249494400', 'como', '2', '10:22 , Tuesday, 15 de June de 2021', '2021-06-15 10:22:49'),
(21, 'admin', 'admin_102534448200', 'como', '2', '10:25 , Tuesday, 15 de June de 2021', '2021-06-15 10:25:34'),
(22, 'admin', 'admin_102628943700', 'como2', '2', '10:26 , Tuesday, 15 de June de 2021', '2021-06-15 10:26:28'),
(23, 'admin', 'admin_102653923300', 'como2', '2', '10:26 , Tuesday, 15 de June de 2021', '2021-06-15 10:26:53'),
(24, 'admin', 'admin_105933621600', 'machetazo', '2', '10:59 , Tuesday, 15 de June de 2021', '2021-06-15 10:59:33'),
(25, 'admin', 'admin_105956615300', 'machetazo2', '2', '10:59 , Tuesday, 15 de June de 2021', '2021-06-15 10:59:56'),
(26, 'admin', 'admin_11141496300', 'machetazo2', '2', '11:01 , Tuesday, 15 de June de 2021', '2021-06-15 11:01:41'),
(27, 'admin', 'admin_11250705600', 'machetazo3', '2', '11:02 , Tuesday, 15 de June de 2021', '2021-06-15 11:02:50'),
(28, 'admin', 'admin_1136226800', 'machetazo4', '2', '11:03 , Tuesday, 15 de June de 2021', '2021-06-15 11:03:06'),
(29, 'admin', 'admin_11440268600', 'machetazo5', '2', '11:04 , Tuesday, 15 de June de 2021', '2021-06-15 11:04:40'),
(30, 'admin', 'admin_11449752900', 'machetazo6', '2', '11:04 , Tuesday, 15 de June de 2021', '2021-06-15 11:04:49'),
(31, 'admin', 'admin_11455707300', 'hola', '1', '11:04 , Tuesday, 15 de June de 2021', '2021-06-15 11:04:55'),
(32, 'admin', 'admin_11118666500', 'machetazo6', '1', '11:11 , Tuesday, 15 de June de 2021', '2021-06-15 11:11:08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rpuntos`
--

CREATE TABLE `rpuntos` (
  `cod` int(11) NOT NULL,
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `puntos` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rpuntos`
--

INSERT INTO `rpuntos` (`cod`, `id`, `puntos`) VALUES
(1, 'jans', '8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `token`
--

CREATE TABLE `token` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `token` varchar(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `token`
--

INSERT INTO `token` (`id`, `token`) VALUES
('admin', 'f49Se81nSd6kkh31i5Foyb:APA91bG7UassGDMobj3xbysHV9xOqoW8omMDrToIcS3UGd7t0EZk8K9GaNyQsye1IpvsAyvSa0yJo5c-ZvvpcOK8saTD_uwazSuPEGeGg4VTlgsGJEfgQm_DwyTbRe8-gvuelE6remnf'),
('jans', 'f49Se81nSd6kkh31i5Foyb:APA91bG7UassGDMobj3xbysHV9xOqoW8omMDrToIcS3UGd7t0EZk8K9GaNyQsye1IpvsAyvSa0yJo5c-ZvvpcOK8saTD_uwazSuPEGeGg4VTlgsGJEfgQm_DwyTbRe8-gvuelE6remnf');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `mensajes_admin`
--
ALTER TABLE `mensajes_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `mensajes_jans`
--
ALTER TABLE `mensajes_jans`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rpuntos`
--
ALTER TABLE `rpuntos`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mensajes_admin`
--
ALTER TABLE `mensajes_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `mensajes_jans`
--
ALTER TABLE `mensajes_jans`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `rpuntos`
--
ALTER TABLE `rpuntos`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
