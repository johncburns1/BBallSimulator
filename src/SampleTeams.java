import java.util.*;

public class SampleTeams {
	
	/* the team stat maps */
	public HashMap<String, Double> warriors;
	public HashMap<String, Double> cavaliers;
	public HashMap<String, Double> wizards;
	public HashMap<String, Double> celtics;

	/* player maps */
	public HashMap<String, Player> warriors_roster;
	public HashMap<String, Player> cavaliers_roster;
	public HashMap<String, Player> celtics_roster;
	public HashMap<String, Player> wizards_roster;
	
	/* Players for the warriors */
	public Player kevinD = new Player("Kevin Durant");
	public Player klayT = new Player("Klay Thompson");
	public Player stephC = new Player("Steph Curry");
	public Player draymondG = new Player("Draymond Green");
	public Player andreI = new Player("Andre Iguodala");
	
	double[] iguodala_stats = {28.4, 7.6, 2.9, 7.0, 2.8, 1.5, 2.1, 0.5, 4.5, 3.6, 1.0, 0.6, 0.3};
	double[] green_stats = {35.4, 14.9, 4.6, 9.3, 5.1, 3.0, 4.5, 0.6, 8.5, 7.3, 1.8, 2.0, 2.6};
	double[] durant_stats = {32.0,23.3,8.5,16.2,4.3,4.5,5.2,1,7.2,3.7,1.7,.7,.8};
	double[] kthompson_stats = {34.4,16.1,6,14.8,5.9,2,2.4,.5,2,1.8,1.9,1.1,.3};
	double[] curry_stats = {34.4,27.1,8.4,17.9,9.8,6.4,7.0,.9,4.4,6,2.9,1.4,.5};
	
	/* players for the cavaliers */
	public Player lebronJ = new Player("Lebron James");
	public Player kevinL = new Player("Kevin Love");
	public Player kyrieI = new Player("Kyrie Irving");
	public Player jrS = new Player("JR Smith");
	public Player tristanT = new Player("Tristian Thompson");
	
	double[] james_stats = {42.4,34.4,11.6,20.9,5.9,8.4,11.5,.8,8.3,7.1,4.4,2.1,1.5};
	double[] love_stats = {31.3,13.8,4.3,9.9,4.6,3.4,4,1.1,8,1.5,2.4,.4,.6};
	double[] irving_stats = {34.3,23.8,8.6,21.6,7.1,4.5,5.0,.5,2.1,5.8,2.1,1.5,.6};
	double[] smith_stats = {25.9,6.4,2.1,4.9,4.3,.3,.5,.1,1.6,.8,.9,.6,.5};
	double[] tthompson_stats = {32.7,7.8,3.1,5.9,0,1.5,2.6,4.9,5.8,1,1,.5,.9};
	
	/* players on the wizards */
	public Player johnW = new Player("John Wall");
	public Player bradleyB = new Player("Bradley Beal");
	public Player marcinG = new Player("Marcin Gortat");
	public Player ottoP = new Player("Otto Porter");
	public Player markieffM = new Player("Markieff Morris");
	
	double[] wall_stats = {38.2,28.1,10,21.2,4.4,6.5,7.5,.4,3.2,10.5,4.5,2.0,1.2};
	double[] beal_stats = {37.9,22.9,8.6,19.2,7.5,3.5,4.5,1.1,2.3,2.5,2.2,1.6,.7};
	double[] gortat_stats = {31.9,8.6,3.9,7.6,0,.8,1.4,4.2,6.8,2,1,.3,1.6};
	double[] porter_stats = {32.1,12.5,4.7,8.5,3.2,2.1,2.5,1,5.6,1.7,.8,1.6,.5};
	double[] morris_stats = {26.5,11.2,4.3,10.6,2.5,1.8,2.2,1.2,4.5,1.6,1.4,.9,1.4};
	
	/* players on the celtics */
	public Player alH = new Player("Al Horford");
	public Player averyB = new Player("Avery Bradley");
	public Player isaiahT = new Player("Isaiah Thomas");
	public Player jaeC = new Player("Jae Crowder");
	public Player marcusS = new Player("Marcus Smart");
	
	double[] horford_stats = {33.5,15.8,6.5,10.1,2.7,1.4,1.6,2,5.7,6.1,1.6,1,1};
	double[] bradley_stats = {34.7,15.4,6.2,13.6,6.5,.5,.9,1.2,3,4.2,2.7,1.9,1.4,.2};
	double[] thomas_stats = {35.1,24.9,7.9,17.4,7.4,6.7,8.3,.4,3.1,5.9,3.5,1,.2};
	double[] crowder_stats = {32.9,13.5,4.6,11.2,5.1,2.5,2.8,1.1,4.9,2.1,.6,1.3,.5};
	double[] smart_stats = {29.8,8.2,2.7,7.5,4.2,1.2,2.4,1.3,3.7,4.7,2.1,1.7,1};
	
	public SampleTeams() {
		
		/* populate the team maps */
		populate_warriors_team_stats();
		populate_cavs_team_stats();
		populate_wizards_team_stats();
		populate_celtics_team_stats();
		
		/* add the player stats */
		add_player_stats();
		
		/* map the players to a roster */
		map_players_to_roster();
	}
	
	private void map_players_to_roster() {
		
		/* define the rosters */
		warriors_roster = new HashMap<String, Player>();
		cavaliers_roster = new HashMap<String, Player>();
		wizards_roster = new HashMap<String, Player>();
		celtics_roster = new HashMap<String, Player>();
		
		/* warriors squad */
		warriors_roster.put("Steph Curry", stephC);
		warriors_roster.put("Kevin Durant", kevinD);
		warriors_roster.put("Klay Thompson", klayT);
		warriors_roster.put("Draymond Green", draymondG);
		warriors_roster.put("Andre Iguodala", andreI);
		
		/* Cavs squad */
		cavaliers_roster.put("Lebron James", lebronJ);
		cavaliers_roster.put("Kevin Love", kevinL);
		cavaliers_roster.put("Kyrie Irving", kyrieI);
		cavaliers_roster.put("Tristan Thompson", tristanT);
		cavaliers_roster.put("JR Smith", jrS);
		
		/* wizards squad */
		wizards_roster.put("John Wall", johnW);
		wizards_roster.put("Bradley Beal", bradleyB);
		wizards_roster.put("Marcin Gortat", marcinG);
		wizards_roster.put("Markieff Morris", markieffM);
		wizards_roster.put("Otto Porter", ottoP);
		
		/* Celtics squad */
		celtics_roster.put("Isaiah Thomas", isaiahT);
		celtics_roster.put("Jae Crowder", jaeC);
		celtics_roster.put("Avery Bradley", averyB);
		celtics_roster.put("Marcus Smart", marcusS);
		celtics_roster.put("Al Horford", alH);
		
	}
	
	private void populate_warriors_team_stats() {
		
		warriors = new HashMap<String, Double>();
		warriors.put("orb", 7.5);
		warriors.put("drb", 38.9);
		warriors.put("fga", 85.5);
		warriors.put("fg", 41.4);
		warriors.put("fta", 25.6);
		warriors.put("ft", 21.1);
		warriors.put("tov", 11.5);
		warriors.put("points", 115.3);
	}
	
	private void populate_cavs_team_stats() {
		
		cavaliers = new HashMap<String, Double>();
		cavaliers.put("orb", 8.3);
		cavaliers.put("drb", 33.1);
		cavaliers.put("fga", 81.1);
		cavaliers.put("fg", 39.8);
		cavaliers.put("fta", 26.8);
		cavaliers.put("ft", 20.6);
		cavaliers.put("tov", 13.3);
		cavaliers.put("points", 114.5);
	}
	
	private void populate_wizards_team_stats() {
		
		wizards = new HashMap<String, Double>();
		wizards.put("orb", 11.3);
		wizards.put("drb", 31.5);
		wizards.put("fga", 88.5);
		wizards.put("fg", 40.7);
		wizards.put("fta", 24.5);
		wizards.put("ft", 19.8);
		wizards.put("tov", 13.8);
		wizards.put("points", 109.8);
	}
	
	private void populate_celtics_team_stats() {
		
		celtics = new HashMap<String, Double>();
		celtics.put("orb", 9.6);
		celtics.put("drb", 29.5);
		celtics.put("fga", 84.1);
		celtics.put("fg", 39.1);
		celtics.put("fta", 21.4);
		celtics.put("ft", 16.2);
		celtics.put("tov", 13.5);
		celtics.put("points", 107.8);
	}
	
	private void populate_player(Player player, double[] stats) {
		player.minutes = stats[0];
		player.points = stats[1];
		player.fg = stats[2];
		player.fga = stats[3];
		player.three_pta = stats[4];
		player.ft = stats[5];
		player.fta = stats[6];
		player.orb = stats[7];
		player.drb = stats[8];
		player.ast = stats[9];
		player.tov = stats[10];
		player.stl = stats[11];
		player.blk = stats[12];
	}
	
	private void add_player_stats() {
		
		/* warriors players */
		populate_player(kevinD, durant_stats);
		populate_player(draymondG, green_stats);
		populate_player(stephC, curry_stats);
		populate_player(andreI, iguodala_stats);
		populate_player(klayT, kthompson_stats);
		
		/* cavaliers players */
		populate_player(lebronJ, james_stats);
		populate_player(kyrieI, irving_stats);
		populate_player(tristanT, tthompson_stats);
		populate_player(jrS, smith_stats);
		populate_player(kevinL, love_stats);
		
		/* wizards players */
		populate_player(bradleyB, beal_stats);
		populate_player(johnW, wall_stats);
		populate_player(marcinG, gortat_stats);
		populate_player(ottoP, porter_stats);
		populate_player(markieffM, morris_stats);
		
		/* celtics players */
		populate_player(isaiahT, thomas_stats);
		populate_player(jaeC, crowder_stats);
		populate_player(averyB, bradley_stats);
		populate_player(alH, horford_stats);
		populate_player(marcusS, smart_stats);
	}
}

	
