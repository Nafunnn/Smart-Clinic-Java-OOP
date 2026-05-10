CREATE DATABASE smart_clinic;
USE smart_clinic;

CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    ROLE VARCHAR(20)
);
CREATE TABLE dokter (
    id_dokter INT AUTO_INCREMENT PRIMARY KEY,
    nama_dokter VARCHAR(100) NOT NULL,
    spesialis VARCHAR(100),
    no_hp VARCHAR(20)
);

CREATE TABLE pasien (
    id_pasien INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    umur INT,
    gender VARCHAR(20),
    alamat TEXT,
    no_hp VARCHAR(20),
    tekanan_darah DOUBLE,
    gula_darah DOUBLE
);
CREATE TABLE pendaftaran (
    id_daftar INT AUTO_INCREMENT PRIMARY KEY,
    tanggal DATE,
    keluhan TEXT,

    id_pasien INT,
    id_dokter INT,

    FOREIGN KEY (id_pasien)
    REFERENCES pasien(id_pasien),

    FOREIGN KEY (id_dokter)
    REFERENCES dokter(id_dokter)
);
CREATE TABLE rekam_medis (
    id_rekam INT AUTO_INCREMENT PRIMARY KEY,
    diagnosa TEXT,
    resep TEXT,
    catatan TEXT,

    id_pasien INT,
    id_dokter INT,

    FOREIGN KEY (id_pasien)
    REFERENCES pasien(id_pasien),

    FOREIGN KEY (id_dokter)
    REFERENCES dokter(id_dokter)
);
CREATE TABLE prediksi (
    id_prediksi INT AUTO_INCREMENT PRIMARY KEY,

    hasil_prediksi VARCHAR(100),
    probabilitas DOUBLE,
    tanggal_prediksi DATE,

    id_pasien INT,

    FOREIGN KEY (id_pasien)
    REFERENCES pasien(id_pasien)
);
INSERT INTO dokter(nama_dokter, spesialis, no_hp)
VALUES
('Dr. Andi', 'Penyakit Dalam', '081111111'),
('Dr. Budi', 'Umum', '082222222');
INSERT INTO pasien
(nama, umur, gender, alamat, no_hp,
tekanan_darah, gula_darah)

VALUES
('Siti', 25, 'Perempuan',
'Semarang', '081234567',
120, 90),

('Ahmad', 40, 'Laki-laki',
'Kendal', '089999999',
150, 250);
1);

INSERT INTO pendaftaran
(tanggal, keluhan, id_pasien, id_dokter)

VALUES
('2026-05-08',
'Sakit kepala',
1,
1);

INSERT INTO rekam_medis
(diagnosa, resep, catatan,
id_pasien, id_dokter)

VALUES
(
'Hipertensi',
'Paracetamol',
'Kontrol 1 minggu',
2,
1
);

INSERT INTO prediksi
(hasil_prediksi,
probabilitas,
tanggal_prediksi,
id_pasien)

VALUES
(
'Risiko Diabetes Tinggi',
0.89,
'2026-05-08',
2
);

SELECT
p.nama,
d.nama_dokter,
pd.keluhan

FROM pendaftaran pd

JOIN pasien p
ON pd.id_pasien = p.id_pasien

JOIN dokter d
ON pd.id_dokter = d.id_dokter;

SELECT
p.nama,
r.diagnosa,
r.resep

FROM rekam_medis r

JOIN pasien p
ON r.id_pasien = p.id_pasien;

SELECT
p.nama,
pr.hasil_prediksi,
pr.probabilitas

FROM prediksi pr

JOIN pasien p
ON pr.id_pasien = p.id_pasien;
