package ExamPreparation.RetakeExam19Dec20.viceCity.models.neighbourhood;

import ExamPreparation.RetakeExam19Dec20.viceCity.models.guns.Gun;
import ExamPreparation.RetakeExam19Dec20.viceCity.models.players.Player;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GangNeighbourhood implements Neighbourhood {

    public GangNeighbourhood() {
    }

    @Override
    public void action(Player mainPlayer, List<Player> civilPlayers) {
        if (!mainPlayer.getGunRepository().getModels().isEmpty()) {
            Collection<Gun> mainPlayersGuns = mainPlayer.getGunRepository().getModels();
            for (Gun mainPlayersGun : mainPlayersGuns) {
                for (int i = 0; i < civilPlayers.size(); i++) {
                    while (mainPlayersGun.canFire()) {
                        civilPlayers.get(i).takeLifePoints(mainPlayersGun.fire());
                        if (!civilPlayers.get(i).isAlive()) {
                            civilPlayers.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }
        }
        for (Player player : civilPlayers) {
            Collection<Gun> guns = player.getGunRepository().getModels();
            if (guns.isEmpty()) {
                continue;
            }
            for (Gun gun : guns) {
                while (gun.canFire()) {
                    mainPlayer.takeLifePoints(gun.fire());
                    if (!mainPlayer.isAlive()) {
                        return;
                    }
                }
            }
        }
    }
}
