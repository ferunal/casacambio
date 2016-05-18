-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2016 a las 00:32:14
-- Versión del servidor: 10.1.8-MariaDB
-- Versión de PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cambiomonedas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monedas`
--

CREATE TABLE `monedas` (
  `simbolo` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `valor` double NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `monedas`
--

INSERT INTO `monedas` (`simbolo`, `nombre`, `valor`, `fecha`) VALUES
('€', '(EUR) Euro', 0.8844, '2016-05-15'),
('£', '(GBP) Libra esterlina', 0.6964, '2016-05-15'),
('¥ ', '(JPY) Yen japones', 108.6, '2016-05-15'),
('Fr', '(CHF) Franco suizo', 0.9756, '2016-05-15'),
('$', '(AUD) Dolar australiano', 1.376, '2016-05-15'),
(' $', '(CAD) Dolar Canadiense', 1.294, '2016-05-15'),
('$', '(HKD) Dolar de Hong Kong', 7.764, '2016-05-15'),
(' ?', '(RUB) Rublo ruso', 65.38, '2016-05-15'),
('¥', '(CNY) Yuan chino', 6.533, '2016-05-15');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
