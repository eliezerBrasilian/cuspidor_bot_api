package app.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import app.controllers.Controller.SocialMediaType;
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
    public long id;

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

    @Column(name = "gerar_post_telegram")
    public boolean gerarPostTelegram = true;

    @Column(name = "gerar_post_youtube")
    public boolean gerarPostYoutube;

    @CreationTimestamp
    @Column(name = "dt_criacao", updatable = false)
    public LocalDateTime dtCriacao;

    public void tooglePost(SocialMediaType target) {
        if (target.isTelegram())
            gerarPostTelegram = !gerarPostTelegram;

        if (target.isInstagram())
            gerarPostInstagram = !gerarPostInstagram;

        if (target.isYoutube())
            gerarPostYoutube = !gerarPostYoutube;
    }

    public boolean newPostValueIs(SocialMediaType target) {
        if (target.isTelegram())
            return gerarPostTelegram;

        else if (target.isInstagram())
            return gerarPostInstagram;

        return gerarPostYoutube;
    }

    public void toogleVideo(SocialMediaType target) {
        if (target.isTelegram())
            gerarVideoTelegram = !gerarVideoTelegram;

        if (target.isInstagram())
            gerarVideoInstagram = !gerarVideoInstagram;

        if (target.isYoutube())
            gerarVideoYoutube = !gerarVideoYoutube;
    }

    public boolean newVideoValueIs(SocialMediaType target) {
        if (target.isTelegram())
            return gerarVideoTelegram;

        else if (target.isInstagram())
            return gerarVideoInstagram;

        return gerarVideoYoutube;

    }
}
