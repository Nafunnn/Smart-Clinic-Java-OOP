-- DEPRECATED: Do not use this file for new installations.
-- This schema is outdated and incompatible with the Java application:
--   - Uses orphan `petugas` table instead of `users` + `roles`
--   - Patient columns named `tekanan`/`gula` instead of `tekanan_darah`/`gula_darah`
--   - Legacy `rekam_medis` linked to pasien/dokter, not pemeriksaan
-- Use smartClinic.sql or docs/migrations/001_add_missing_tables.sql instead.

CREATE DATABASE IF NOT EXISTS smart_clinic;
USE smart_clinic;

-- =========================
-- TABEL USERS
-- =========================
CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    PASSWORD VARCHAR(100),
    level_user VARCHAR(30)
);
-- =========================
-- TABEL DOKTER
-- =========================
CREATE TABLE dokter (
    id_dokter INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    spesialis VARCHAR(100),
    no_hp VARCHAR(20)
);
-- =========================
-- TABEL PASIEN
-- =========================
CREATE TABLE pasien (
    id_pasien INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    umur INT,
    gender VARCHAR(20),
    alamat TEXT,
    no_hp VARCHAR(20),
    tekanan DOUBLE,
    gula DOUBLE
);

-- =========================
-- TABEL PENDAFTARAN
-- =========================
CREATE TABLE pendaftaran (
    id_daftar INT AUTO_INCREMENT PRIMARY KEY,
    tanggal DATE,
    keluhan TEXT,

    id_pasien INT,
    id_dokter INT,

    STATUS VARCHAR(30) DEFAULT 'Menunggu',

    FOREIGN KEY (id_pasien)
        REFERENCES pasien(id_pasien)
        ON DELETE CASCADE,

    FOREIGN KEY (id_dokter)
        REFERENCES dokter(id_dokter)
        ON DELETE CASCADE
);


-- =========================
-- TABEL PETUGAS
-- =========================
CREATE TABLE petugas (
    id_petugas INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    username VARCHAR(50),
    PASSWORD VARCHAR(100)
);

-- =========================
-- TABEL OBAT
-- =========================
CREATE TABLE obat (
    id_obat INT AUTO_INCREMENT PRIMARY KEY,
    nama_obat VARCHAR(100),
    stok INT,
    harga DOUBLE,
    aturan_pakai VARCHAR(100)
);



-- =========================
-- TABEL PEMERIKSAAN
-- =========================
CREATE TABLE pemeriksaan (
    id_periksa INT AUTO_INCREMENT PRIMARY KEY,

    id_daftar INT,

    tanggal_periksa DATE,

    diagnosa TEXT,
    tekanan_darah DOUBLE,
    gula_darah DOUBLE,
    suhu DOUBLE,
    berat_badan DOUBLE,

    catatan TEXT,

    hasil_prediksi VARCHAR(100),
    tingkat_resiko VARCHAR(50),

    FOREIGN KEY (id_daftar)
        REFERENCES pendaftaran(id_daftar)
        ON DELETE CASCADE
);

-- =========================
-- TABEL REKAM MEDIS
-- =========================
CREATE TABLE rekam_medis (
    id_rekam INT AUTO_INCREMENT PRIMARY KEY,

    id_periksa INT,

    tanggal DATE,

    ringkasan TEXT,

    FOREIGN KEY (id_periksa)
        REFERENCES pemeriksaan(id_periksa)
        ON DELETE CASCADE
);

-- =========================
-- TABEL RESEP OBAT
-- =========================
CREATE TABLE resep_obat (
    id_resep INT AUTO_INCREMENT PRIMARY KEY,

    id_periksa INT,
    id_obat INT,

    jumlah INT,
    dosis VARCHAR(100),
    keterangan TEXT,

    FOREIGN KEY (id_periksa)
        REFERENCES pemeriksaan(id_periksa)
        ON DELETE CASCADE,

    FOREIGN KEY (id_obat)
        REFERENCES obat(id_obat)
        ON DELETE CASCADE
);




INSERT INTO dokter(nama, spesialis, no_hp)
VALUES
('dr. Andi', 'Penyakit Dalam', '081111111'),
('dr. Budi', 'Umum', '082222222');

INSERT INTO pasien(nama, umur, gender, alamat, no_hp, tekanan, gula)
VALUES
('Siti', 25, 'Perempuan', 'Semarang', '081234567', 120, 90),
('Ahmad', 40, 'Laki-laki', 'Kendal', '089999999', 150, 250);

INSERT INTO obat(nama_obat, stok, harga, aturan_pakai)
VALUES
('Paracetamol', 100, 5000, '3x1'),
('Antasida', 50, 7000, '2x1');
