package app.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioModel {
    public UsuarioModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(name = "id_telegram", unique = true)
    public int idTelegram;

    @ManyToOne
    @JoinColumn(name = "id_canal", referencedColumnName = "id")
    public CanalTelegramModel canal;

    public String nome;

    @Column(name = "is_premium")
    public boolean isPremium;

    @CreationTimestamp
    @Column(name = "dt_criacao", updatable = false)
    public LocalDateTime dtCriacao;

}
