public class Equations {
	
	/* normal coefficients */
	private static final double A = 0.123391;
	private static final double B = 0.119597;
	private static final double C = -0.151287;
	private static final double D = 1.255644;
	private static final double E = 0.531838;
	private static final double F = -0.305868;
	private static final double G = 0.921292;
	private static final double H = 0.711217;
	private static final double I = 0.017022;
	private static final double J = 0.297639;
	private static final double K = 0.213485;
	private static final double L = 0.725930;
	
	/* offensive coefficients */
	private static final double A_OFF = 0.064448;
	private static final double B_OFF = 0.211125; 
	private static final double C_OFF = -0.107545;
	private static final double D_OFF = 0.346513;
	private static final double E_OFF = -0.052476;
	private static final double F_OFF = -0.041787;
	private static final double G_OFF = 0.932965;
	private static final double H_OFF = 0.687359;
	private static final double I_OFF = 0.007952;
	private static final double J_OFF = 0.007952;
	private static final double K_OFF = -0.181891;
	private static final double L_OFF = 0.239862;

	/* other constants */
	private static final double LPACE = 96.4;
	private static final double LPPG = 105.6;
	private static final double X = .44;
	private static final double N = 1;
	/**
	 * Calculates relative points per game for a player
	 */
	public double reMPG(Player player) {
		return (player.minutes/240) * N;		
	}
	
	/**
	 * Calculates the offensive rebound percentage for a player
	 * 
	 * @param player
	 * @param team
	 * @param opponent
	 */
	public double oRB(Player player, Team team, Team opponent) {
		return ((player.orb * 48)/(player.minutes * (team.orb + opponent.drb))) * N;
	}
	
	/**
	 * Calculates the percentage of a defensive rebound
	 * 
	 */
	public double dRB(Player player, Team team, Team opponent) {
		return ((player.drb * 48)/(player.minutes * (team.drb + opponent.orb))) * N;
	}
	
	/**
	 * Calculates the total rebound percent for a player
	 * 
	 */
	public double tRB(Player player, Team team, Team opponent) {
		return oRB(player, team, opponent) + dRB(player, team, opponent);
	}
	
	/**
	 * Calculates the percentage of a steal for each player
	 *  
	 * @param player
	 * @return
	 */
	public double sTL(Player player) { 
		return ((100*player.stl * 48)/(player.minutes * LPACE));
	}
	
	/**
	 * Calculates percentage of a block
	 * 
	 */
	public double bLK(Player player, Team opponent) {
		return ((player.blk * 240)/(player.minutes * opponent.fga)) * N;
	}
	
	/**
	 * Calculates the precentage of assists for a player
	 * 
	 */
	public double aST(Player player, Team team) {
		return ((player.ast)/(((player.minutes/48) * team.fg) - player.fg)) * N;
	}
	
	/**
	 * Calculates the chance of a turnover
	 * 
	 */
	public double tOV(Player player) {
		return N * ((player.tov)/ (plays(player)));
	}
	
	public double uSG(Player player, Team team) {
		return N * ((plays(player) * 48) / (player.minutes * plays(team)));
	}
	
	/**
	 * Calculates a players true shot percentage
	 * 
	 */
	public double tS(Player player) {//check the * 100 
		return N * ((player.points)/(2 * (player.fga + (X * player.fta))));
	}
	
	/**
	 * Calculates a team's true shot percentage
	 * 
	 */
	public double tMtS(Team team) {//check the * 100 
		return N * ((team.points)/(2 * (team.fga + (X * team.fta))));
	}
	
	
	/**
	 * Calculates 3 point rate of a player
	 * 
	 */
	public double three_PTA(Player player) {
		return N * (player.three_pta/player.fga);
	}
	
	/**
	 * Gets a players PPP (Points per possession)
	 * 
	 */
	public double pPP(Player player) {
		return (player.points/(plays(player)));
	}
	
	/**
	 * Calculates a players offensive rating (how many points a player scores per 100 possessions
	 * 
	 */
	public double oRT(Player player, double pp) {
		return 100 * ((pp)/(plays(player)));
	}
	
	/**
	 * Calculates a players raw boxscore plus minus
	 * 
	 */
	public double raw_BPM(Player player, Team team, Team opponent) {
		double reMPG = reMPG(player);
		double orb = oRB(player, team, opponent);
		double drb = dRB(player, team, opponent);
		double trb = tRB(player, team, opponent);
		double stl = sTL(player);
		double blk = bLK(player, opponent);
		double ast = aST(player, team);
		double tov = tOV(player);
		double usg = uSG(player, team);
		double ts = tS(player);
		double team_ts = tMtS(team);
		double three_pta = three_PTA(player);
		
		if(three_pta == 0) {
			three_pta = .00001;
		}
		
		double usage_factor = H * usg * (1 - tov) * (2 * (ts - team_ts) + I * ast + J * (three_pta - Math.log(three_pta)) - K);
		double ast_trb_scale = L * Math.sqrt(ast * trb);
		
		double raw_bpm = A * reMPG + B * orb + C * drb + D * stl + E* blk + F* ast - G * usg * tov;
		return raw_bpm + usage_factor + ast_trb_scale;
		
	}
	
	/**
	 * Calculates the offensive oriented BPM for a player
	 * @param player
	 * @param team
	 * @param opponent
	 * @return
	 */
	public double off_BPM(Player player, Team team, Team opponent) {
		double reMPG = reMPG(player);
		double orb = oRB(player, team, opponent);
		double drb = dRB(player, team, opponent);
		double trb = tRB(player, team, opponent);
		double stl = sTL(player);
		double blk = bLK(player, opponent);
		double ast = aST(player, team);
		double tov = tOV(player);
		double usg = uSG(player, team);
		double ts = tS(player);
		double team_ts = tMtS(team);
		double three_pta = three_PTA(player);
		
		if(three_pta == 0) {
			three_pta = .00001;
		}
		
		double usage_factor = H_OFF * usg * (1 - tov) * (2 * (ts - team_ts) + I_OFF * ast + J_OFF * (three_pta - Math.log(three_pta)) - K_OFF);
		double ast_trb_scale = L_OFF * Math.sqrt(ast * trb);
		
		double off_bpm = A_OFF * reMPG + B_OFF * orb + C_OFF * drb + D_OFF * stl + E_OFF * blk + F_OFF * ast + G_OFF * usg * tov;
		return off_bpm + usage_factor + ast_trb_scale;
	}
	
	public double def_BPM(Player player, Team team, Team opponent) {
		double raw_bpm = raw_BPM(player, team, opponent);
		double off_bpm = off_BPM(player, team, opponent);
		return raw_bpm - off_bpm;
	}
	
	
	/**
	 * Calculates the number of play results that a player was involved in
	 * 
	 */
	private double plays(Player player) {
		return player.fga + (X * player.fta) + player.tov;
	}
	
	/**
	 * Calculates the number of play results that a team was involved in
	 * 
	 */
	private double plays(Team team) {
		return team.fga + (X * team.fta) + team.tov;
	}
	
	
}
