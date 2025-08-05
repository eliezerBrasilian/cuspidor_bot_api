package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.models.CanalTelegramModel;

public interface CanalTelegramRepository extends JpaRepository<CanalTelegramModel, Long> {

        @Query("""
                        SELECT c from CanalTelegramModel c
                        JOIN FETCH
                        c.criador criador
                        where criador.idTelegram = :userIdTelegram
                        """)
        public List<CanalTelegramModel> findByUserIdTelegram(
                        @Param("userIdTelegram") String userIdTelegram);
}
