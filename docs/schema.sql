CREATE DATABASE IF NOT EXISTS hospital CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE hospital;

DROP TABLE IF EXISTS admin;
CREATE TABLE admin
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    username  VARCHAR(50) NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    real_name VARCHAR(50)
);

DROP TABLE IF EXISTS doctor;
CREATE TABLE doctor
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50)  NOT NULL,
    department  VARCHAR(50),
    title       VARCHAR(50),
    phone       VARCHAR(30),
    description VARCHAR(255)
);

DROP TABLE IF EXISTS patient;
CREATE TABLE patient
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(50)  NOT NULL,
    gender    VARCHAR(10),
    age       INT,
    phone     VARCHAR(30),
    id_number VARCHAR(50),
    address   VARCHAR(255)
);

DROP TABLE IF EXISTS registration;
CREATE TABLE registration
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id       BIGINT      NOT NULL,
    doctor_id        BIGINT      NOT NULL,
    appointment_time DATETIME    NOT NULL,
    status           VARCHAR(20) NOT NULL DEFAULT '待就诊',
    notes            VARCHAR(255),
    created_at       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_registration_patient FOREIGN KEY (patient_id) REFERENCES patient (id),
    CONSTRAINT fk_registration_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id)
);

INSERT INTO admin (username, password, real_name)
VALUES ('admin', '123456', '系统管理员')
ON DUPLICATE KEY UPDATE username = VALUES(username);

