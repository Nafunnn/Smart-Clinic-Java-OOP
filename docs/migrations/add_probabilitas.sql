-- Migration: add probabilitas column for ML prediction results
-- Run on existing smart_clinic database

USE smart_clinic;

ALTER TABLE pemeriksaan
    ADD COLUMN probabilitas DOUBLE NULL AFTER tingkat_resiko;
