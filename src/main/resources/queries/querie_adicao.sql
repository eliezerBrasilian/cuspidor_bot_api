insert into usuario(
id_telegram, nome
) values (
	1234, 'teste'
);

insert into preferencia(
id_usuario, gerar_video_telegram
) values (1, true);

select * from usuario;
select * from preferencia;

insert into canal_telegram(username, nome)
values ('@canalteste','canal de teste');

update usuario
set id_canal = 1 where id = 1;

select * from usuario as u inner join canal_telegram as c
on u.id_canal = c.id;