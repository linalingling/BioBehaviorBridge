-- =============================================
-- 期末專題模板 — 資料庫初始化腳本
-- =============================================
-- 使用方式：
--   psql -U postgres -h localhost -d myproject -f sql/schema.sql
-- =============================================

--1.建立項目
CREATE TYPE user_role AS ENUM ('ADMIN','USER','COACH','DOCTOR');
CREATE TYPE goal_type AS ENUM ('WEIGHT_LOSS','RECOVERY','ANXIETY_MGMT', 'WITHDRAWAL', 'LIFE_LOGGING');
CREATE TYPE talent_category AS ENUM ('CONTROL', 'MEDITATION', 'RECOVERY', 'FOCUS');

--2.建立User表
CREATE TABLE users (
                       id SERIAL PRIMARY KEY ,
                       username VARCHAR(30) NOT NULL UNIQUE ,
                       password_hash VARCHAR(100) NOT NULL,
                       role user_role NOT NULL DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--3.建立Charcters表 (主體)
CREATE TABLE characters (
                            id SERIAL PRIMARY KEY ,
                            user_id INT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE ,
                            char_name VARCHAR(30) UNIQUE NOT NULL ,
                            talent_type talent_category,
                            bonus_decimal DECIMAL(3,2) DEFAULT 1.00,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

--4.建立Goals表(一對多綁定角色)
CREATE TABLE goals (
                       id SERIAL PRIMARY KEY ,
                       char_id INT NOT NULL REFERENCES characters(id) ON DELETE CASCADE,
                       title goal_type NOT NULL ,
                       is_active BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--5.建立Bhavior_logs表
CREATE TABLE behavior_logs (
                               id SERIAL PRIMARY KEY ,
                               goal_id INT NOT NULL REFERENCES goals(id) ON DELETE CASCADE ,
                               action VARCHAR(30) NOT NULL ,
                               note TEXT,
                               base_Value DECIMAL(10,2),
                               calculate_points DECIMAL(10,2),
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

