package app.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import app.controllers.Controller.TelegramChannelPayload;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CanalTelegramModel {
    public CanalTelegramModel() {
    }

    public CanalTelegramModel(TelegramChannelPayload payload) {
        this.nome = payload.name();
        this.username = payload.username();
    }

    public void updateData(TelegramChannelPayload payload) {
        this.nome = payload.name();
        this.username = payload.username();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String nome;

    @Column(unique = true)
    public String username;

    @CreationTimestamp
    @Column(name = "dt_criacao", updatable = false)
    public LocalDateTime dtCriacao;
}
