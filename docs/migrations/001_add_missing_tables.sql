-- Sync smart_clinic schema with application (run on existing database)
USE smart_clinic;

-- =========================
-- ROLES & USERS (Petugas module)
-- =========================
CREATE TABLE IF NOT EXISTS roles (
    id_role INT AUTO_INCREMENT PRIMARY KEY,
    nama_role VARCHAR(50)
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    username VARCHAR(50),
    password VARCHAR(255),
    id_role INT,
    id_dokter INT NULL,
    FOREIGN KEY (id_role) REFERENCES roles(id_role),
    FOREIGN KEY (id_dokter) REFERENCES dokter(id_dokter)
);

INSERT INTO roles (id_role, nama_role) VALUES
(1, 'Admin'),
(2, 'Petugas'),
(3, 'Dokter')
ON DUPLICATE KEY UPDATE nama_role = VALUES(nama_role);

INSERT INTO users (nama, username, password, id_role) VALUES
('Administrator', 'admin', '123', 1),
('Petugas Klinik', 'petugas', '123', 2);

-- =========================
-- OBAT
-- =========================
CREATE TABLE IF NOT EXISTS obat (
    id_obat INT AUTO_INCREMENT PRIMARY KEY,
    nama_obat VARCHAR(100),
    stok INT,
    harga DOUBLE,
    aturan_pakai VARCHAR(100),
    kode_kfa INT
);

-- =========================
-- PEMERIKSAAN
-- =========================
CREATE TABLE IF NOT EXISTS pemeriksaan (
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
-- REKAM MEDIS (replace legacy schema)
-- =========================
DROP TABLE IF EXISTS rekam_medis;

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
-- RESEP OBAT
-- =========================
CREATE TABLE IF NOT EXISTS resep_obat (
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

-- =========================
-- SEED OBAT (only if empty)
-- =========================
INSERT INTO obat (nama_obat, stok, harga, aturan_pakai)
SELECT * FROM (
    SELECT 'Paracetamol' AS nama_obat, 100 AS stok, 5000 AS harga, '3x1' AS aturan_pakai
    UNION ALL SELECT 'Antasida', 50, 7000, '2x1'
    UNION ALL SELECT 'Amoxicillin', 50, 12000, NULL
    UNION ALL SELECT 'Vitamin C', 80, 3000, NULL
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM obat LIMIT 1);
