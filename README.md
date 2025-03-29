**Saya Muhammad Akhtar Rizki Ramadha dengan NIM 2304742 mengerjakan soal Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.**

# Data Mahasiswa GUI

Aplikasi ini adalah manajemen data mahasiswa berbasis Java dengan GUI menggunakan Swing. Program ini memungkinkan pengguna untuk menambah, mengubah, menghapus, dan melihat data mahasiswa yang tersimpan dalam database MySQL.

## Struktur Kelas

**1. Mahasiswa**

Representasi data mahasiswa dengan atribut NIM, Nama, Jenis Kelamin, dan Program Studi.
    
**2. Menu**

Tampilan utama GUI, berisi form input, tabel mahasiswa, serta tombol Add, Update, Delete.

**3. Database**

Koneksi ke database MySQL serta eksekusi query SELECT, INSERT, UPDATE, DELETE.

## Fitur Program

- Menambahkan data mahasiswa baru ke database.
- Menampilkan daftar mahasiswa dalam tabel.
- Memperbarui informasi mahasiswa berdasarkan NIM.
- Menghapus data mahasiswa dari database.
- Validasi input untuk mencegah kesalahan data.

## Alur Program

- Aplikasi dimulai, menampilkan tampilan utama (Menu).
- Pengguna dapat:

    - Menambahkan data mahasiswa dengan mengisi form dan menekan tombol "Add".
    - Memilih data di tabel untuk diperbarui atau dihapus.
    - Memperbarui data mahasiswa dengan mengubah form lalu menekan "Update".
    - Menghapus data dengan menekan tombol "Delete" setelah memilih baris di tabel.
- Data yang diubah akan terupdate di database MySQL secara langsung.
