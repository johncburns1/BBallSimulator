import java.util.*;
import java.math.*;

public class Main {
	
	public static final int NUM_POSSESSIONS = 100;
	
	/* the teams */
	public static Team warriors;
	public static Team cavaliers;
	public static Team celtics;
	public static Team wizards;
	
	/* the sheet of equations */
	private static Equations equation_sheet;
	
	/* sample team data */
	private static SampleTeams samples;
	
	public static void main(String[] args) {
		
		/* get the sample data */
		samples = new SampleTeams();
		equation_sheet = new Equations();
		
		/* new team objects */
		warriors = new Team("Golden State Warriors");
		cavaliers = new Team("Cleveland Cavaliers");
		wizards = new Team("Washington(D.C.) Wizards");
		celtics = new Team("Boston Celtics");		
		
		/* bind sample stats to teams */
		map_stats_to_team(warriors, samples.warriors);
		map_stats_to_team(cavaliers, samples.cavaliers);
		map_stats_to_team(wizards, samples.wizards);
		map_stats_to_team(celtics, samples.celtics);
		
		/* bind rosters to each team */
		warriors.players = samples.warriors_roster;
		cavaliers.players = samples.cavaliers_roster;
		wizards.players = samples.wizards_roster;
		celtics.players = samples.celtics_roster;
		
		Player lbj = cavaliers.players.get("Lebron James");
		Player ki = cavaliers.players.get("Kyrie Irving");
		Player klove = cavaliers.players.get("Kevin Love");
		Player jrsmith = cavaliers.players.get("JR Smith");
		Player tt = cavaliers.players.get("Tristan Thompson");
		
		double total = 0;
		double total2 = 0;
		
		Set<String> keySet = celtics.players.keySet();
		Iterator<String> keySetIterator = keySet.iterator();

		while (keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			Player player = celtics.players.get(key);
			double rbpm = equation_sheet.raw_BPM(player, celtics, wizards);
			double obpm = equation_sheet.off_BPM(player, celtics, wizards);
			double dbpm = equation_sheet.def_BPM(player, celtics, wizards);
			System.out.println("[Player]: " + player.player_name +"--> [Raw_BPM]: " + rbpm +"; [Off_BPM]: " + obpm + "; [Def_BPM]: "+ dbpm);
			total += equation_sheet.raw_BPM(player, celtics, wizards);
		}		
		
		System.out.println("------------------------------------------------");
		
		Set<String> keySet2 = wizards.players.keySet();
		Iterator<String> keySet2Iterator = keySet2.iterator();
		
		while (keySet2Iterator.hasNext()) {
			String key = keySet2Iterator.next();
			Player player = wizards.players.get(key);
			double rbpm = equation_sheet.raw_BPM(player, wizards, celtics);
			double obpm = equation_sheet.off_BPM(player, wizards, celtics);
			double dbpm = equation_sheet.def_BPM(player, wizards, celtics);
			System.out.println("[Player]: " + player.player_name +"--> [Raw_BPM]: " + rbpm +"; [Off_BPM]: " + obpm + "; [Def_BPM]: "+ dbpm);
			total2 += equation_sheet.raw_BPM(player, wizards, celtics);
		}		
		
		System.out.println("------------------------------------------------");
		
		double den = total+total2;
		double warr_win = 100 * total/den;
		double cav_win = 100 * total2/den;
		System.out.println("\nWizards team BPM: " + total + "\n");
		System.out.println("\nCeltics team BPM: " + total2 + "\n");
		System.out.println("\nChance of winning(%) <Wizards: " + warr_win +", Celtics: " + cav_win + ">");
		
		/* enter the simulation process */
		for(int i = 0; i < NUM_POSSESSIONS; i++) {
			
		}
		
	}
	
	public static void map_stats_to_team(Team team, HashMap<String, Double> stats) {
		
		/* bind the stats to team fields */
		team.orb = stats.get("orb");
		team.drb = stats.get("drb");
		team.fga = stats.get("fga");
		team.fg = stats.get("fg");
		team.fta = stats.get("fta");
		team.ft = stats.get("ft");
		team.tov = stats.get("tov");
		team.points = stats.get("points");
	}
}
