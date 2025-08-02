package app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.models.UsuarioModel;

public interface UserRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByIdTelegram(String id);
}
