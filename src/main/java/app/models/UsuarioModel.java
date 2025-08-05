package app.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import app.controllers.Controller.UserPayload;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class UsuarioModel {
    public UsuarioModel() {
    }

    public UsuarioModel(UserPayload payload) {
        this.idTelegram = payload.idTelegram();
        this.nome = payload.name();
        this.preferencia = new PreferenciaModel();
        this.preferencia.usuario = this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "id_telegram", unique = true)
    public String idTelegram;

    // caminho de volta ->
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval se apagar usuario
                                                                                     // a pref tbm é excluida
    private PreferenciaModel preferencia;

    public String nome;

    @Column(name = "is_premium")
    public boolean isPremium;

    @CreationTimestamp
    @Column(name = "dt_criacao", updatable = false)
    public LocalDateTime dtCriacao;

}
