-- Создание таблицы пользователей
CREATE TABLE users (
                     user_id BIGINT PRIMARY KEY,
                       username VARCHAR(255),
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       registration_date TIMESTAMP,
                        country VARCHAR(255),
                        league VARCHAR(255),
                        rank VARCHAR(255)
);
