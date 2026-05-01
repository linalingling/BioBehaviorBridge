-- =============================================
-- 期末專題模板 — 資料庫初始化腳本
-- =============================================
-- 使用方式：
--   psql -U postgres -h localhost -d myproject -f sql/schema.sql
-- =============================================

-- 1. 使用者表
create TYPE user_role AS ENUM ('USER', 'COACH' , 'DOCTOR');
CREATE TABLE IF NOT EXISTS users (
    id          SERIAL PRIMARY KEY,
    --使用特定格式( USER00001)
    formatted_id VARCHAR(20) GENERATED ALWAYS AS (
        'USER' || LPAD(id::text,5,0)
        ) STORED,

    username    VARCHAR(30)  NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL,
    role user_role NOT NULL DEFAULT 'USER',
  -- USER / ADMIN
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- 2. 項目表（範例：你可以改成 roses / rooms / bills / quests 等）
CREATE TABLE IF NOT EXISTS items (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    category    VARCHAR(30)  NOT NULL,              -- 對應 Enum
    status      VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',  -- ACTIVE / ARCHIVED / DELETED
    description TEXT,
    priority    INT          NOT NULL DEFAULT 1 CHECK (priority BETWEEN 1 AND 5),
    owner_id    INT          REFERENCES users(id) ON DELETE CASCADE,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- 3. 操作紀錄表（範例：養護日誌 / 帳單紀錄 / 任務日誌）
CREATE TABLE IF NOT EXISTS item_logs (
    id          SERIAL PRIMARY KEY,
    item_id     INT          NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    action      VARCHAR(30)  NOT NULL,   -- CREATE / UPDATE / NOTE / COMPLETE
    note        TEXT,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- =============================================
-- 種子資料（示範用，同學請替換成自己的）
-- =============================================
INSERT INTO users (username, password_hash, role) VALUES
    ('admin', '240be518', 'ADMIN'),
    ('demo',  'fe2592b4', 'USER');

INSERT INTO items (name, category, status, description, priority, owner_id) VALUES
    ('範例項目 A', 'GENERAL', 'ACTIVE', '這是第一個範例項目', 3, 2),
    ('範例項目 B', 'URGENT',  'ACTIVE', '這是緊急項目',       5, 2),
    ('範例項目 C', 'GENERAL', 'ARCHIVED', '這個已經完成了',   1, 2);
