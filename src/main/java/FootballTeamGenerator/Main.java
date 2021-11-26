package FootballTeamGenerator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputLine = scan.nextLine();
        Map<String, Team> teams = new LinkedHashMap<>();
        while (!inputLine.equals("END")) {
            String[] arr = inputLine.split(";");
            try {
                switch (arr[0]) {
                    case "Team":
                        String teamName = arr[1];
                        Team team = new Team(teamName);
                        teams.putIfAbsent(teamName, team);
                        break;
                    case "Add":
                        teamName = arr[1];
                        String playerName = arr[2];
                        int endurance = Integer.parseInt(arr[3]);
                        int sprint = Integer.parseInt(arr[4]);
                        int dribble = Integer.parseInt(arr[5]);
                        int passing = Integer.parseInt(arr[6]);
                        int shooting = Integer.parseInt(arr[7]);
                        if (!teams.containsKey(teamName)) {
                            System.out.println("Team " + teamName + " does not exist.");
                        } else {
                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                            teams.get(teamName).addPlayer(player);
                        }
                        break;
                    case "Remove":
                        teamName = arr[1];
                        playerName = arr[2];
                        teams.get(teamName).removePlayer(playerName);
                        break;
                    case "Rating":
                        teamName = arr[1];
                        if (!teams.containsKey(teamName)) {
                            System.out.println("Team" + teamName + " does not exist.");
                        } else {
                            System.out.printf("%s - %d%n", teamName, Math.round(teams.get(teamName).getRating()));
                        }
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            inputLine = scan.nextLine();
        }
    }
}
