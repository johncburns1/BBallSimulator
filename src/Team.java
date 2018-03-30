import java.util.*;

public class Team {
	
	public String team_name;
	public double orb;
	public double drb;
	public double fga;
	public double fg;
	public double fta;
	public double ft;
	public double tov;
	public double points;
	public double score;
	
	/* Array of players */
	public HashMap<String, Player> players;
	
	/**
	 * Constructor for team class
	 */
	public Team(String team_name) {
		this.team_name = team_name;
		this.score = 0;
	}
	
	public void printPlayerStats() {
		Set<String> keySet = this.players.keySet();
		Iterator<String> keySetIterator = keySet.iterator();

		while (keySetIterator.hasNext()) {
			System.out.println("------------------------------------------------");
			String key = keySetIterator.next();
			Player player = players.get(key);
			player.printStats();
		}
	}
}
