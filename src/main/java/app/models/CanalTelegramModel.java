package app.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CanalTelegramModel {
    public CanalTelegramModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String nome;

    @Column(unique = true)
    public String username;

    @CreationTimestamp
    @Column(name = "dt_criacao", updatable = false)
    public LocalDateTime dtCriacao;
}
