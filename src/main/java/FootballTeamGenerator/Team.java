package FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public double getRating() {
        return this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0.0);
    }

    public void removePlayer(String name) {
        Player current = this.players.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        if (current == null) {
            throw new IllegalArgumentException("Player " + name + " is not in " + this.name + " team.");
        }
        this.players.remove(current);
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
