package ExamPreparation.RetakeExam19Dec20.viceCity.models.neighbourhood;

import ExamPreparation.RetakeExam19Dec20.viceCity.models.players.Player;

import java.util.List;

public interface Neighbourhood {
    void action(Player mainPlayer, List<Player> civilPlayers);
}
