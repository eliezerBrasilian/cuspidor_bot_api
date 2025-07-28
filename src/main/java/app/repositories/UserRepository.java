package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.models.UsuarioModel;

public interface UserRepository extends JpaRepository<UsuarioModel, Long> {

}
