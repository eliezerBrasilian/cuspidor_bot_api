package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.models.CanalTelegramModel;

public interface CanalTelegramRepository extends JpaRepository<CanalTelegramModel, Long> {

}
