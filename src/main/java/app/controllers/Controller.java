package app.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.models.CanalTelegramModel;
import app.models.PreferenciaModel;
import app.models.UsuarioModel;
import app.repositories.CanalTelegramRepository;
import app.repositories.PreferenciaRepository;
import app.repositories.UserRepository;

@RequestMapping("cuspidor-bot/api")
@RestController
public class Controller {

        final UserRepository userRepository;
        final CanalTelegramRepository canalTelegramRepository;
        final PreferenciaRepository preferenciaRepository;

        public Controller(UserRepository userRepository,
                        CanalTelegramRepository canalTelegramRepository, PreferenciaRepository preferenciaRepository) {
                this.userRepository = userRepository;
                this.canalTelegramRepository = canalTelegramRepository;
                this.preferenciaRepository = preferenciaRepository;
        }

        public record UserPayload(String name,
                        @JsonProperty("id_telegram") String idTelegram) {
        }

        @PostMapping("user/create")
        public ResponseEntity<String> addUser(
                        @RequestBody UserPayload payload) {

                userRepository.save(new UsuarioModel(payload));
                return ResponseEntity.status(201).body("user created");
        }

        @PostMapping("user/become-premium/{id}")
        public ResponseEntity<String> tooglePremium(
                        @PathVariable("id") String idTelegram) {

                Optional<UsuarioModel> opUser = userRepository.findByIdTelegram(idTelegram);
                if (opUser.isEmpty())
                        return ResponseEntity.status(404).body("user doest't exists");

                var user = opUser.get();
                user.isPremium = true;

                userRepository.save(user);
                return ResponseEntity.status(201).body("user now is premium");
        }

        @GetMapping("user/is-premium/{idTelegram}")
        public ResponseEntity<String> userIsPremium(
                        @PathVariable("idTelegram") String idTelegram) {

                Optional<UsuarioModel> opUser = userRepository.findByIdTelegram(idTelegram);
                if (opUser.isEmpty())
                        return ResponseEntity.status(404).body("user doest't exists");

                var user = opUser.get();

                userRepository.save(user);
                return ResponseEntity.status(201).body("premium status: " + user.isPremium);
        }

        public record TelegramChannelPayload(
                        @JsonProperty("user_id") long userId,
                        String name,
                        String username) {
        }

        @PostMapping("telegram-channel")
        public ResponseEntity<String> addTelegramChannel(
                        @RequestBody TelegramChannelPayload payload) {

                canalTelegramRepository.save(new CanalTelegramModel(payload));
                return ResponseEntity.status(201).body("channel added");
        }

        @PutMapping("telegram-channel/{channelId}")
        public ResponseEntity<String> editTelegramChannel(
                        @PathVariable("channelId") long id,
                        @RequestBody TelegramChannelPayload payload) {
                Optional<CanalTelegramModel> opCanal = canalTelegramRepository.findById(id);

                if (opCanal.isPresent()) {
                        var canal = opCanal.get();
                        canal.updateData(payload);

                        canalTelegramRepository.save(canal);
                        return ResponseEntity.status(202).body("channel updated");
                }
                return ResponseEntity.status(404).body("channel doest't exists");

        }

        public enum SocialMediaType {
                YOUTUBE("youtube"),
                TELEGRAM("telegram"),
                INSTAGRAM("instagram");

                public final String v;

                private SocialMediaType(String v) {
                        this.v = v;
                }

                public boolean isTelegram() {
                        return this.equals(TELEGRAM);
                }

                public boolean isInstagram() {
                        return this.equals(INSTAGRAM);
                }

                public boolean isYoutube() {
                        return this.equals(YOUTUBE);
                }
        }

        @PostMapping("toogle-post")
        public ResponseEntity<String> toogleGeneratePost(
                        @RequestParam("target") SocialMediaType target,
                        @RequestParam("preferencia_id") long prefId) {

                Optional<PreferenciaModel> opPref = preferenciaRepository.findById(prefId);
                if (opPref.isEmpty())
                        return ResponseEntity.status(404).body("pref doest't exists");

                var pref = opPref.get();
                pref.tooglePost(target);

                preferenciaRepository.save(pref);
                return ResponseEntity.status(202).body(
                                "%s updated to %s".formatted(target.v, pref.newPostValueIs(target)));

        }

        @PostMapping("toogle-video")
        public ResponseEntity<String> toogleGenerateVideo(
                        @RequestParam("target") SocialMediaType target,
                        @RequestParam("preferencia_id") long prefId) {

                Optional<PreferenciaModel> opPref = preferenciaRepository.findById(prefId);
                if (opPref.isEmpty())
                        return ResponseEntity.status(404).body("pref doest't exists");

                var pref = opPref.get();
                pref.toogleVideo(target);

                preferenciaRepository.save(pref);
                return ResponseEntity.status(202).body(
                                "%s updated to %s".formatted(target.v, pref.newVideoValueIs(target)));

        }

}
