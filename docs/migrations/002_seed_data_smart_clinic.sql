-- MySQL dump 10.13  Distrib 9.6.0, for macos14.8 (x86_64)
--
-- Host: localhost    Database: smart_clinic
-- ------------------------------------------------------
-- Server version	9.6.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '25c71f3c-58ad-11f1-a545-1a8c719287f0:1-80';

--
-- Dumping data for table `pasien`
--

LOCK TABLES `pasien` WRITE;
/*!40000 ALTER TABLE `pasien` DISABLE KEYS */;
INSERT INTO `pasien` VALUES (1,'Siti',25,'Perempuan','Semarang','081234567',120,90),(2,'Ahmad',40,'Laki-laki','Kendal','089999999',150,250),(5,'Budi Santoso',35,'Laki-laki','Bandung','081234567801',125,95),(6,'Dewi Lestari',29,'Perempuan','Jakarta','081234567802',118,88),(7,'Rizky Pratama',42,'Laki-laki','Surabaya','081234567803',145,210),(8,'Nabila Putri',24,'Perempuan','Yogyakarta','081234567804',110,85),(9,'Andi Saputra',50,'Laki-laki','Semarang','081234567805',155,240),(10,'Fitri Handayani',31,'Perempuan','Solo','081234567806',120,92),(11,'Eko Nugroho',38,'Laki-laki','Malang','081234567807',135,110),(12,'Maya Sari',27,'Perempuan','Bogor','081234567808',115,87),(13,'Agus Setiawan',46,'Laki-laki','Bekasi','081234567809',148,180),(14,'Intan Permata',33,'Perempuan','Depok','081234567810',122,94),(15,'Hendra Wijaya',55,'Laki-laki','Cirebon','081234567811',160,275),(16,'Lina Marlina',37,'Perempuan','Tasikmalaya','081234567812',118,89),(17,'Fajar Nugroho',44,'Laki-laki','Purwokerto','081234567813',142,165),(18,'Nurul Hasanah',26,'Perempuan','Pekalongan','081234567814',112,82),(19,'Yoga Prasetyo',32,'Laki-laki','Salatiga','081234567815',128,102),(20,'Sarah Olivia',29,'Perempuan','Magelang','081234567816',120,93),(21,'Dedi Gunawan',61,'Laki-laki','Tegal','081234567817',170,290),(22,'Tika Maharani',23,'Perempuan','Jepara','081234567818',110,79),(23,'Bayu Firmansyah',40,'Laki-laki','Purwakarta','081234567819',138,145),(24,'Anisa Putri',35,'Perempuan','Sukabumi','081234567820',122,97);
/*!40000 ALTER TABLE `pasien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dokter`
--

LOCK TABLES `dokter` WRITE;
/*!40000 ALTER TABLE `dokter` DISABLE KEYS */;
INSERT INTO `dokter` VALUES (1,'Dr. Andi','Penyakit Dalam','081111111'),(2,'Dr. Budi','Umum','082222222'),(3,'Dr. Tirta','Penyakit Dalam','087812341234'),(4,'Dr. Siti Aisyah','Anak','081234567821'),(5,'Dr. Hendra Wijaya','Jantung','081234567822'),(6,'Dr. Maya Lestari','Kandungan','081234567823'),(7,'Dr. Rizky Pratama','Saraf','081234567824'),(8,'Dr. Nabila Putri','Kulit dan Kelamin','081234567825'),(9,'Dr. Fajar Nugroho','Bedah Umum','081234567826'),(10,'Dr. Dewi Anggraini','Mata','081234567827');
/*!40000 ALTER TABLE `dokter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Administrator','admin','123',1,NULL),(3,'Petugas Jaga','petugas','123',2,NULL),(4,'Dulker','Admin','123',1,NULL),(5,'Siti Rahmawati','srahma','123',2,NULL),(6,'Ahmad Fauzan','afauzan','123',2,NULL),(7,'Dewi Lestari','dlestari','123',2,NULL),(8,'Rizky Saputra','rsaputra','123',2,NULL),(9,'Nabila Putri','nputri','123',2,NULL),(10,'Yoga Prasetyo','yprasetyo','123',2,NULL),(11,'Maya Anggraini','manggraini','123',2,NULL),(12,'Dr. Andi','drandi','123',3,1),(13,'Dr. Budi','drbudi','123',3,2),(14,'Dr. Tirta','drtirta','123',3,3),(15,'Dr. Siti Aisyah','drsiti','123',3,4),(16,'Dr. Hendra Wijaya','drhendra','123',3,5),(17,'Dr. Maya Lestari','drmaya','123',3,6),(18,'Dr. Rizky Pratama','drrizky','123',3,7),(19,'Dr. Nabila Putri','drnabila','123',3,8),(20,'Dr. Fajar Nugroho','drfajar','123',3,9),(21,'Dr. Dewi Anggraini','drdewi','123',3,10);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(2,'Petugas'),(3,'Dokter');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `obat`
--

LOCK TABLES `obat` WRITE;
/*!40000 ALTER TABLE `obat` DISABLE KEYS */;
INSERT INTO `obat` VALUES (1,'Paracetamol',100,5000,'3x1',NULL),(2,'Antasida',50,7000,'2x1',NULL),(3,'Amoxicillin',50,12000,NULL,NULL),(4,'Vitamin C',80,3000,NULL,NULL),(5,'Ibuprofen 400mg',120,8000,'3x1',NULL),(6,'Cetirizine 10mg',90,6000,'1x1 malam',NULL),(7,'Omeprazole 20mg',75,15000,'1x1 sebelum makan',NULL),(8,'Metformin 500mg',100,10000,'2x1',NULL),(9,'Amlodipine 5mg',80,12000,'1x1',NULL),(10,'Captopril 25mg',70,9000,'2x1',NULL),(11,'Salbutamol 2mg',60,7000,'3x1',NULL),(12,'Dexamethasone 0.5mg',50,5000,'3x1',NULL),(13,'CTM 4mg',150,3000,'3x1',NULL),(14,'OBH Combi',65,12000,'3x1',NULL),(15,'Methylprednisolone 4mg',45,14000,'3x1',NULL),(16,'Asam Mefenamat 500mg',90,8500,'3x1 sesudah makan',NULL),(17,'Loperamide 2mg',55,5000,'2x1',NULL),(18,'Oralit',200,2500,'Sesuai kebutuhan',NULL),(19,'Zinc 20mg',110,4000,'1x1',NULL),(20,'Ambroxol 30mg',85,7500,'3x1',NULL);
/*!40000 ALTER TABLE `obat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pendaftaran`
--

LOCK TABLES `pendaftaran` WRITE;
/*!40000 ALTER TABLE `pendaftaran` DISABLE KEYS */;
INSERT INTO `pendaftaran` VALUES (1,'2026-05-08','Sakit kepala',1,1),(2,'2026-06-15','Mual',2,2),(3,'2026-06-01','Demam dan batuk',5,2),(4,'2026-06-02','Nyeri lambung',6,1),(5,'2026-06-02','Pusing dan lemas',7,1),(6,'2026-06-03','Kontrol diabetes',8,1),(7,'2026-06-03','Sesak napas ringan',9,5),(8,'2026-06-04','Batuk pilek',10,2),(9,'2026-06-05','Alergi kulit',11,8),(10,'2026-06-05','Sakit tenggorokan',12,2),(11,'2026-06-06','Tekanan darah tinggi',13,5),(12,'2026-06-06','Pemeriksaan kesehatan rutin',14,2),(13,'2026-06-07','Nyeri sendi',15,1),(14,'2026-06-08','Migrain',16,7),(15,'2026-06-09','Diare',17,2),(16,'2026-06-10','Demam',18,2),(17,'2026-06-11','Kontrol hipertensi',19,5),(18,'2026-06-12','Batuk berkepanjangan',20,2),(19,'2026-06-13','Gatal-gatal',21,8),(20,'2026-06-13','Nyeri dada ringan',22,5),(21,'2026-06-14','Pemeriksaan umum',23,2),(22,'2026-06-14','Kontrol gula darah',24,1);
/*!40000 ALTER TABLE `pendaftaran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pemeriksaan`
--

LOCK TABLES `pemeriksaan` WRITE;
/*!40000 ALTER TABLE `pemeriksaan` DISABLE KEYS */;
INSERT INTO `pemeriksaan` VALUES (1,1,'2026-06-06','demam',78,90,38,50,'','demam','Sedang'),(2,1,'2026-06-15','Demam',120,70,32,58,'perbanyak istirahat','','Rendah'),(3,2,'2026-06-15','Alegri makanan',120,70,32,60,'Mual setelah makan seafood','','Rendah'),(4,3,'2026-06-01','ISPA Ringan',125,95,37.8,68,'Istirahat dan perbanyak minum air','Normal','Rendah'),(5,4,'2026-06-02','Gastritis',118,88,36.7,55,'Hindari makanan pedas dan asam','Normal','Rendah'),(6,5,'2026-06-02','Hipertensi',145,210,36.8,72,'Kontrol tekanan darah dan gula darah','Diabetes','Tinggi'),(7,6,'2026-06-03','Diabetes Mellitus Tipe 2',110,185,36.5,64,'Kontrol gula darah secara rutin','Diabetes','Tinggi'),(8,7,'2026-06-03','Asma Ringan',155,140,37,78,'Gunakan inhaler bila diperlukan','Prediabetes','Sedang'),(9,8,'2026-06-04','Influenza',120,92,38.2,60,'Istirahat 3 hari dan banyak minum air putih','Normal','Rendah'),(10,9,'2026-06-05','Dermatitis Alergi',135,110,36.6,70,'Hindari makanan dan zat pemicu alergi','Prediabetes','Sedang'),(11,10,'2026-06-05','Faringitis',115,87,37.9,58,'Perbanyak minum air hangat','Normal','Rendah'),(12,11,'2026-06-06','Hipertensi Stadium 1',148,180,36.7,82,'Kurangi konsumsi garam dan rutin olahraga','Prediabetes','Tinggi'),(13,12,'2026-06-06','Pemeriksaan Kesehatan Rutin',122,94,36.5,59,'Kondisi umum baik dan stabil','Normal','Rendah'),(14,13,'2026-06-07','Osteoarthritis Ringan',160,275,36.9,76,'Disarankan fisioterapi ringan dan kontrol rutin','Diabetes','Tinggi'),(15,14,'2026-06-08','Migrain',118,89,36.8,54,'Kurangi stres dan istirahat cukup','Normal','Rendah'),(16,15,'2026-06-09','Gastroenteritis Akut',142,165,37.5,67,'Perbanyak cairan dan konsumsi oralit','Prediabetes','Sedang'),(17,16,'2026-06-10','Demam Virus',112,82,38.5,52,'Konsumsi paracetamol dan istirahat','Normal','Rendah'),(18,17,'2026-06-11','Hipertensi',128,102,36.6,71,'Kontrol tekanan darah setiap minggu','Prediabetes','Sedang');
/*!40000 ALTER TABLE `pemeriksaan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rekam_medis`
--

LOCK TABLES `rekam_medis` WRITE;
/*!40000 ALTER TABLE `rekam_medis` DISABLE KEYS */;
INSERT INTO `rekam_medis` VALUES (1,1,'2026-06-06','demam\n'),(2,2,'2026-06-15','Demam\nperbanyak istirahat'),(3,3,'2026-06-15','Alegri makanan\nMual setelah makan seafood'),(4,4,'2026-06-01','ISPA Ringan\nPasien mengeluh demam dan batuk selama 3 hari.\nDisarankan istirahat yang cukup dan memperbanyak konsumsi cairan.'),(5,5,'2026-06-02','Gastritis\nKeluhan nyeri pada ulu hati terutama setelah makan.\nDisarankan menghindari makanan pedas, asam, dan kopi.'),(6,6,'2026-06-02','Hipertensi\nPasien mengalami pusing dan lemas.\nTekanan darah dan gula darah tinggi, perlu kontrol rutin.'),(7,7,'2026-06-03','Diabetes Mellitus Tipe 2\nPasien kontrol diabetes.\nDisarankan menjaga pola makan dan melakukan pemeriksaan gula darah berkala.'),(8,8,'2026-06-03','Asma Ringan\nPasien mengalami sesak napas ringan.\nDiberikan edukasi penggunaan inhaler dan menghindari pemicu asma.'),(9,9,'2026-06-05','Dermatitis Alergi\nPasien mengalami gatal dan ruam pada kulit.\nDisarankan menghindari alergen dan menggunakan obat sesuai resep.'),(10,10,'2026-06-05','Faringitis\nPasien mengeluh sakit tenggorokan dan kesulitan menelan.\nDianjurkan banyak minum air hangat dan istirahat cukup.'),(11,11,'2026-06-06','Hipertensi Stadium 1\nTekanan darah pasien di atas normal.\nDisarankan mengurangi konsumsi garam dan melakukan kontrol rutin.'),(12,12,'2026-06-06','Pemeriksaan Kesehatan Rutin\nKondisi umum pasien baik dan stabil.\nTidak ditemukan keluhan yang memerlukan tindakan lanjutan.'),(13,13,'2026-06-07','Osteoarthritis Ringan\nPasien mengeluh nyeri pada persendian.\nDisarankan olahraga ringan dan kontrol berkala.'),(14,14,'2026-06-08','Migrain\nPasien mengalami sakit kepala berulang pada satu sisi kepala.\nDisarankan menghindari stres dan menjaga pola tidur.'),(15,15,'2026-06-09','Gastroenteritis Akut\nPasien mengalami diare dan nyeri perut.\nDianDianDianDianDianDianDairDianDianDianDianDianDianDairDia(1DianD02DianDianDianDian VDianDianDianDianDlaDianDianDianDianDianDianDairDianDianDianDihat yang cukup dan minum obat sesuai resep.');
/*!40000 ALTER TABLE `rekam_medis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `resep_obat`
--

LOCK TABLES `resep_obat` WRITE;
/*!40000 ALTER TABLE `resep_obat` DISABLE KEYS */;
INSERT INTO `resep_obat` VALUES (1,1,1,4,'3','-'),(2,2,3,3,'2x1 hari','setelah makan'),(3,2,4,4,'1x1 hari','vitamin harian'),(4,3,2,3,'1x1hari','setelah makan'),(5,3,4,1,'1x1hari','setelah makan');
/*!40000 ALTER TABLE `resep_obat` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-15 13:37:29
