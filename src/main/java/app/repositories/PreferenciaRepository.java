package app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.models.PreferenciaModel;

public interface PreferenciaRepository extends JpaRepository<PreferenciaModel, Long> {
    // @Query("""
    // SELECT p FROM PreferenciaModel p
    // JOIN FETCH
    // p.usuario u
    // WHERE p.id=:id AND u.id=:usuarioId
    // """)

    // Optional<PreferenciaModel> findPreferenciaByIdAndUsuarioId(
    // @Param("id") Long id,
    // @Param("usuarioId") Long usuarioId);
}
