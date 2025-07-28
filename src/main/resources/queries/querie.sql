create table usuario(
	id serial PRIMARY KEY,
	id_telegram INTEGER UNIQUE,
	id_canal INTEGER REFERENCES canal_telegram(id),
	nome varchar(50),
	is_premium BOOLEAN DEFAULT FALSE,
	dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table preferencia(
	id serial PRIMARY KEY,
	id_usuario INTEGER REFERENCES usuario(id),
	gerar_video_instagram BOOLEAN DEFAULT FALSE,
	gerar_video_telegram BOOLEAN DEFAULT FALSE,
	gerar_video_youtube BOOLEAN DEFAULT FALSE,
	gerar_post_instagram BOOLEAN DEFAULT FALSE,
	gerar_post_telegram BOOLEAN DEFAULT TRUE,
	gerar_post_youtube BOOLEAN DEFAULT FALSE,
	dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table canal_telegram(
	id serial PRIMARY KEY,
	username VARCHAR(30) UNIQUE,
	nome VARCHAR(30) UNIQUE,
	dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--drop table preferencia;
--drop table usuario;