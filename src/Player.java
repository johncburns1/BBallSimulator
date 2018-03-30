import java.util.*;

public class Player {
	
	public String player_name;
	public double orb;
	public double drb;
	public double fga;
	public double fg;
	public double fta;
	public double ft;
	public double stl;
	public double blk;
	public double ast;
	public double points;
	public double tov;
	public double three_pta;
	public double minutes;
	
	/**
	 * Constructor for team class
	 */
	public Player(String player_name) {
		this.orb = 0;
		this.drb = 0;
		this.fga = 0;
		this.fg = 0;
		this.fta = 0;
		this.ft = 0;
		this.stl = 0;
		this.blk = 0;
		this.ast = 0;
		this.points = 0;
		this.three_pta = 0;
		this.minutes = 0;
		this.player_name = player_name;
	}
	
	public void printStats() {
		System.out.println("Name: " + this.player_name);
		System.out.println("ORB: " + this.orb);
		System.out.println("DRB: " + this.drb);
		System.out.println("FGA: " + this.fga);
		System.out.println("FA: " + this.fg);
		System.out.println("FTA: " + this.fta);
		System.out.println("FT: " + this.ft);
		System.out.println("STL: " + this.stl);
		System.out.println("BLK: " + this.blk);
		System.out.println("AST: " + this.ast);
		System.out.println("POINTS: " + this.points);
		System.out.println("3PTA: " + this.three_pta);
		System.out.println("MINUTES: " + this.minutes);
	}
}
