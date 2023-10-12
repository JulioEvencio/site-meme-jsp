DROP DATABASE db_site_meme_jsp;

CREATE DATABASE db_site_meme_jsp;

\c db_site_meme_jsp;

CREATE TABLE IF NOT EXISTS tb_roles (
	uuid UUID PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tb_images (
	uuid UUID PRIMARY KEY,
	imageBase64 TEXT NOT NULL,
    type VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_users (
    uuid UUID PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    enabled BOOLEAN NOT NULL,
    photo_uuid UUID REFERENCES tb_images(uuid)
);

CREATE TABLE IF NOT EXISTS tb_users_roles (
    user_uuid UUID NOT NULL REFERENCES tb_users(uuid),
    role_uuid UUID NOT NULL REFERENCES tb_roles(uuid),
    PRIMARY KEY (user_uuid, role_uuid)
);

CREATE TABLE IF NOT EXISTS tb_tags (
	uuid UUID PRIMARY KEY,
	name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tb_memes (
    uuid UUID PRIMARY KEY,
    image_uuid UUID NOT NULL REFERENCES tb_images(uuid),
    user_uuid UUID NOT NULL REFERENCES tb_users(uuid),
    tag_uuid UUID NOT NULL REFERENCES tb_tags(uuid)
);

CREATE TABLE IF NOT EXISTS tb_comments (
    uuid UUID PRIMARY KEY,
    comment VARCHAR(255),
    user_uuid UUID NOT NULL REFERENCES tb_users(uuid),
    meme_uuid UUID NOT NULL REFERENCES tb_memes(uuid)
);

INSERT INTO tb_roles (uuid, name) VALUES (gen_random_uuid(), 'ROLE_ADMIN'), (gen_random_uuid(), 'ROLE_USER');
INSERT INTO tb_tags (uuid, name) VALUES (gen_random_uuid(), 'Internet'), (gen_random_uuid(), 'Shitpost'), (gen_random_uuid(), 'Games');
