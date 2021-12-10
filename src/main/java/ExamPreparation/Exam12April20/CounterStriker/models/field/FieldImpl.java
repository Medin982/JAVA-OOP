package ExamPreparation.Exam12April20.CounterStriker.models.field;

import ExamPreparation.Exam12April20.CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.Exam12April20.CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static ExamPreparation.Exam12April20.CounterStriker.common.OutputMessages.TERRORIST_WINS;


public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {
        List<Player> terrorist = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());

        List<Player> counterTerrorist = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("CounterTerrorist"))
                .collect(Collectors.toList());
        while (!terrorist.isEmpty() && !counterTerrorist.isEmpty()) {

            for (Player player : terrorist) {
                for (int i = 0; i < counterTerrorist.size(); i++) {
                    counterTerrorist.get(i).takeDamage(player.getGun().fire());
                    if (!counterTerrorist.get(i).isAlive()) {
                        counterTerrorist.remove(i);
                        i--;
                    }
                }
            }

            for (Player player : counterTerrorist) {
                for (int i = 0; i < terrorist.size(); i++) {
                    terrorist.get(i).takeDamage(player.getGun().fire());
                    if (!terrorist.get(i).isAlive()) {
                        terrorist.remove(i);
                        i--;
                    }
                }
            }
        }
        if (terrorist.isEmpty()) {
            return COUNTER_TERRORIST_WINS;
        }
        return TERRORIST_WINS;
    }
}
