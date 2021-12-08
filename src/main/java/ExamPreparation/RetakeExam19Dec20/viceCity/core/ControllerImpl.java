package ExamPreparation.RetakeExam19Dec20.viceCity.core;

import ExamPreparation.RetakeExam19Dec20.viceCity.core.interfaces.Controller;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.guns.Gun;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.guns.Pistol;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.guns.Rifle;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.neighbourhood.GangNeighbourhood;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.neighbourhood.Neighbourhood;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.players.CivilPlayer;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.players.MainPlayer;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.players.Player;
import ExamPreparation.RetakeExam19Dec20.viceCity.repositories.interfaces.CivilPlayerRepository;
import ExamPreparation.RetakeExam19Dec20.viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;

import static ExamPreparation.RetakeExam19Dec20.viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

    private ArrayDeque<Gun> queue;
    private Player mainPlayer;
    private Repository<Player> civilRepository;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.queue = new ArrayDeque<>();
        this.civilRepository = new CivilPlayerRepository();
    }

    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);
        this.civilRepository.add(civilPlayer);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return GUN_TYPE_INVALID;
        }
        this.queue.offer(gun);
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gunToAdd;
        if (this.queue.isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }
        if (name.equals("Vercetti")) {
            gunToAdd = this.queue.poll();
            this.mainPlayer.getGunRepository().add(gunToAdd);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gunToAdd.getName(), this.mainPlayer.getName());
        }
        Player player = this.civilRepository.find(name);
        if (player == null) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }
        gunToAdd = this.queue.poll();
        player.getGunRepository().add(gunToAdd);
        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gunToAdd.getName(), player.getName());
    }

    @Override
    public String fight() {
        Neighbourhood neighbourhood = new GangNeighbourhood();
        int beforeFight = this.civilRepository.getModels().size();
        neighbourhood.action(this.mainPlayer, this.civilRepository.getModels());
        int afterFight = this.civilRepository.getModels().size();
        if (this.mainPlayer.getLifePoints() == 100 && beforeFight == afterFight) {
                return FIGHT_HOT_HAPPENED;
        }

        StringBuilder sb = new StringBuilder();
            sb.append(FIGHT_HAPPENED)
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()))
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,
                            beforeFight - afterFight))
                    .append(System.lineSeparator())
                    .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, afterFight));

        return sb.toString().trim();
    }
}
