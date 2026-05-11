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
INSERT INTO users(username, password_hash, role) VALUES
                                                     ('Lina','abc1234','USER'),
                                                     ('Hank','hhh4476','COACH'),
                                                     ('Aggie','agg777','DOCTOR'),
                                                     ('Tony','tnn542','USER');
INSERT INTO users(username, password_hash, role) VALUES
                                                     ('Linaling','aabbcc06','ADMIN');

-- 2. 項目表（範例：你可以改成 roses / rooms / bills / quests 等）
-- *定義監控目的枚舉 ( 5 種需求)
CREATE TYPE goal_type AS ENUM (
    'WEIGHT_LOSS',      -- 減重
    'RECOVERY',         -- 恢復
    'ANXIETY_MGMT',     -- 焦慮管理
    'WITHDRAWAL',       -- 戒斷 (如戒菸)
    'LIFE_LOGGING'      -- 單純生活紀錄
    );
-- 3. 操作紀錄表（建立目標管理表 (One-to-Many 邏輯)）
CREATE TABLE IF NOT EXISTS goals (
                                     id SERIAL PRIMARY KEY,
                                     user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                                     title goal_type NOT NULL,
                                     is_active BOOLEAN DEFAULT TRUE, -- 用於「隨時切換目的」的核心欄位
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TYPE user_role ADD VALUE 'ADMIN' BEFORE 'USER';

INSERT INTO goals (user_id, title, is_active) VALUES
                                                  (6, 'WEIGHT_LOSS', TRUE),   -- 目前啟動中的目標
                                                  (7, 'LIFE_LOGGING', TRUE),
                                                  (8,'ANXIETY_MGMT', TRUE),
                                                  (9,'LIFE_LOGGING',FALSE),-- 過去或待機中的目標
                                                  (9,'WITHDRAWAL',TRUE);


-- =============================================
-- 種子資料（示範用，同學請替換成自己的）
-- =============================================
INSERT INTO users (username, password_hash, role) VALUES
                                                      ('ling0045', 'lina1234', 'DOCTOR'),
                                                      ('demo',  'fe2592b4', 'USER');

INSERT INTO goals (user_id, title, is_active) VALUES
                                                  (1, 'RECOVERY', TRUE),      -- 目前啟動中的目標
                                                  (1, 'LIFE_LOGGING', FALSE); -- 過去或待機中的目標

CREATE TABLE IF NOT EXISTS behavior_logs (
                                             id          SERIAL PRIMARY KEY,
                                             goal_id     INT NOT NULL REFERENCES goals(id) ON DELETE CASCADE,
                                             action      VARCHAR(30) NOT NULL, -- 類別：如 TRAINING, MEDICAL, NUTRITION
                                             note        TEXT,                -- 詳細內容：如 重訓重量、心情筆記
                                             created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 4. 養成角色邏輯與資訊
CREATE TYPE talent_category AS ENUM ('CONTROL','MEDITATION','RECOVERY', 'FOCUS');
CREATE TABLE IF NOT EXISTS characters
(
    id            SERIAL PRIMARY KEY,
    user_id       INT                NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    goal_id       INT                NOT NULL REFERENCES goals (id) ON DELETE CASCADE,
    char_name     VARCHAR(30) UNIQUE NOT NULL,
    char_level    INT           DEFAULT 1, --預設值1
    char_exp      INT           DEFAULT 0, --預設值0
    bonus_decimal DECIMAL(3, 2) DEFAULT 1.00,
    talent_type   VARCHAR(20),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--5.character_state 邏輯建模
CREATE TABLE IF NOT EXISTS character_state
(
    id              SERIAL PRIMARY KEY,
    char_id         INT NOT NULL REFERENCES characters (id) ON DELETE CASCADE,
    muscle_mass     DECIMAL(5, 2) DEFAULT 0,
    waist_hip_ratio DECIMAL(3, 2) DEFAULT 0,
    fatigue_index   INT           DEFAULT 0,
    mood_index      INT           DEFAULT 0,
    goal_completion INT           DEFAULT 0,
    update_at       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);

--Test
INSERT INTO characters(
                       user_id, goal_id, char_name, talent_type,bonus_decimal
) VALUES
      (6,16,'黃小鴨','WEIGHT_LOSS',1.15);

INSERT INTO behavior_logs(goal_id,action,note)VALUES
                                                  (16,'TRAINING','重訓:槓鈴深蹲 50KG'),
                                                  (16,'CONTROL','飲食未攝取糖類'),
                                                  (16,'MEDICAL','生理:有排便');
INSERT INTO behavior_logs (goal_id, action, note) VALUES
(17, 'MEDITATION', '冥想：身心恢復加成'),
(20, 'MEDITATION', '冥想：戒斷穩定加成');





