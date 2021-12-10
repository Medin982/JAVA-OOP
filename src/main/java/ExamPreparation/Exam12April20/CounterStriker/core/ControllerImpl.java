package ExamPreparation.Exam12April20.CounterStriker.core;

import ExamPreparation.Exam12April20.CounterStriker.models.field.Field;
import ExamPreparation.Exam12April20.CounterStriker.models.field.FieldImpl;
import ExamPreparation.Exam12April20.CounterStriker.models.guns.Gun;
import ExamPreparation.Exam12April20.CounterStriker.models.guns.Pistol;
import ExamPreparation.Exam12April20.CounterStriker.models.guns.Rifle;
import ExamPreparation.Exam12April20.CounterStriker.models.players.CounterTerrorist;
import ExamPreparation.Exam12April20.CounterStriker.models.players.Player;
import ExamPreparation.Exam12April20.CounterStriker.models.players.Terrorist;
import ExamPreparation.Exam12April20.CounterStriker.repositories.GunRepository;
import ExamPreparation.Exam12April20.CounterStriker.repositories.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.Exam12April20.CounterStriker.common.ExceptionMessages.*;
import static ExamPreparation.Exam12April20.CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_GUN;
import static ExamPreparation.Exam12April20.CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_PLAYER;

public class ControllerImpl implements Controller {

    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }

        this.guns.add(gun);

        return String.format(SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gunsByName = this.guns.findByName(gunName);
        if (gunsByName == null) {
            throw new NullPointerException(GUN_CANNOT_BE_FOUND);
        }

        Player player;
        switch (type) {
            case "Terrorist":
                player = new Terrorist(username, health, armor, gunsByName);
                break;
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gunsByName);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        this.players.add(player);

        return String.format(SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return this.field.start(this.players.getModels());
    }

    @Override
    public String report() {
        StringBuilder report = new StringBuilder();
        List<Player> sortedPlayers = this.players.getModels()
                .stream()
                .sorted((p1, p2) -> {
                    int result;
                    result = p1.getClass().getSimpleName().compareTo(p2.getClass().getSimpleName());

                    if (result == 0) {
                        result = Integer.compare(p2.getHealth(), p1.getHealth());
                    }

                    if (result == 0) {
                        result = p1.getUsername().compareTo(p2.getUsername());
                    }
                    return result;
                })
                .collect(Collectors.toList());

        sortedPlayers.forEach(p -> report.append(p.toString()).append(System.lineSeparator()));

        return report.toString().trim();
    }
}
