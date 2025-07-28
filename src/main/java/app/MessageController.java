package app;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081" })

@RequestMapping("api/votar")
@RestController
public class MessageController {

        public record PayloadCore(
                        String batchId,
                        String sourceNodeId,
                        List<Message> dataPoints) {
        }

        public record Body(
                        String objectIdentifier) {
        }

        // melhor-filme-2025
        public record Message(
                        String type,
                        String objectIdentifier,
                        int valor,
                        LocalDateTime datetime) {
        }

        @PostMapping("/candidato")
        public ResponseEntity<String> votar(
                        @RequestBody Body body) {

                var message = new Message(
                                "melhor-filme-2025",
                                body.objectIdentifier(),
                                1,
                                LocalDateTime.now());

                var payloadCore = new PayloadCore(
                                UUID.randomUUID().toString(),
                                "node-coletor-fantasma",
                                List.of(message));

                return ResponseEntity.ok("Mensagem enviada com sucesso");
        }
}
