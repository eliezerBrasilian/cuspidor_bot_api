package app.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PreferenciaModel {

    public PreferenciaModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @OneToOne // primeiro one é essa entidade PrerenciaModel -> 1 pref pra 1 usuario
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    public UsuarioModel usuario;

    @Column(name = "gerar_video_instagram")
    public boolean gerarVideoInstagram;

    @Column(name = "gerar_video_telegram")
    public boolean gerarVideoTelegram;

    @Column(name = "gerar_video_youtube")
    public boolean gerarVideoYoutube;

    @Column(name = "gerar_post_instagram")
    public boolean gerarPostInstagram;

    @Column(name = "gerar_post_telegram", columnDefinition = "BOOLEAN DEFAULT TRUE")
    public boolean gerarPostTelegram;

    @Column(name = "gerar_post_youtube")
    public boolean gerarPostYoutube;

    @CreationTimestamp
    @Column(name = "dt_criacao", updatable = false)
    public LocalDateTime dtCriacao;
}
