-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 29, 2025 at 06:31 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `program_studi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `program_studi`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'Ilmu Komputer'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'Ilmu Komputer'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'Ilmu Komputer'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'Ilmu Komputer'),
(5, '2202046', 'Nurainun', 'Perempuan', 'Ilmu Komputer'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'Ilmu Komputer'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'Ilmu Komputer'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'Ilmu Komputer'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'Ilmu Komputer'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'Ilmu Komputer'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'Ilmu Komputer'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'Ilmu Komputer'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'Ilmu Komputer'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'Ilmu Komputer'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'Ilmu Komputer'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'Ilmu Komputer'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'Ilmu Komputer'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'Ilmu Komputer'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'Ilmu Komputer'),
(20, '2204343', 'Meiva Labibah Putri', 'Perempuan', 'Ilmu Komputer'),
(23, '2304742', 'Akhtar Rizki Ramadha', 'Laki-laki', 'Ilmu Komputer'),
(25, '2204744', 'Aurell Nur Jasmine Indrayani', 'Perempuan', 'Pendidikan Ilmu Komputer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
