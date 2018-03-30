/**
 * Loads player statistics from online source
 * @author johnc
 * @version 1.0
 * @since 2017
 */

//import lines
import java.util.*;
import java.io.*;
import java.net.*;
import org.json.*;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;


public class Stats {

	/* api routes */
	private static HashMap<String, String> teamMap = new HashMap<String, String>();
	private static String apiKey = "/?api_key=9vk4rghc3juktfmdxhnpdmc3";
	private static String teamsURL = "http://api.sportradar.us/nba-t3/teams/";
	private static String season_statsURL = "http://api.sportradar.us/nba-t3/seasontd/2016/REG/teams/";
	private static String playerURL = "http://api.sportradar.us/nba-t3/players/";
	private static String latestSeason = "03241c9e-731b-40d0-a36f-bcc9932d055b";
	private static String statsURL = "/statistics.json" + apiKey;
	private static String profileURL = "/profile.json" + apiKey;
	
	/*global team fields */
	private static Team team1;
	private static Team team2;
	private static HashMap<String, Float> team1_stats;
	private static HashMap<String, Float> team2_stats;

	
	/* team keys */
	private static String wizards = "583ec8d4-fb46-11e1-82cb-f4ce4684ea4c";
	private static String hawks = "583ecb8f-fb46-11e1-82cb-f4ce4684ea4c";
	private static String hornets = "583ec97e-fb46-11e1-82cb-f4ce4684ea4c";
	private static String heat = "583ecea6-fb46-11e1-82cb-f4ce4684ea4c";
	private static String magic = "583ed157-fb46-11e1-82cb-f4ce4684ea4c";
	private static String cavaliers = "583ec773-fb46-11e1-82cb-f4ce4684ea4c";
	private static String pacers = "583ec7cd-fb46-11e1-82cb-f4ce4684ea4c";
	private static String pistons = "583ec928-fb46-11e1-82cb-f4ce4684ea4c";
	private static String bulls = "583ec5fd-fb46-11e1-82cb-f4ce4684ea4c";
	private static String bucks = "583ecefd-fb46-11e1-82cb-f4ce4684ea4c";
	private static String celtics = "583eccfa-fb46-11e1-82cb-f4ce4684ea4c";
	private static String raptors = "583ecda6-fb46-11e1-82cb-f4ce4684ea4c";
	private static String knicks = "583ec70e-fb46-11e1-82cb-f4ce4684ea4c";
	private static String sixers = "583ec87d-fb46-11e1-82cb-f4ce4684ea4c";
	private static String nets = "583ec9d6-fb46-11e1-82cb-f4ce4684ea4c";
	private static String warriors = "583ec825-fb46-11e1-82cb-f4ce4684ea4c";
	private static String clippers = "583ecdfb-fb46-11e1-82cb-f4ce4684ea4c";
	private static String kings = "583ed0ac-fb46-11e1-82cb-f4ce4684ea4c";
	private static String lakers = "583ecae2-fb46-11e1-82cb-f4ce4684ea4c";
	private static String suns = "583ecfa8-fb46-11e1-82cb-f4ce4684ea4c";
	private static String spurs = "583ecd4f-fb46-11e1-82cb-f4ce4684ea4c";
	private static String rockets = "583ecb3a-fb46-11e1-82cb-f4ce4684ea4c";
	private static String grizzlies = "583eca88-fb46-11e1-82cb-f4ce4684ea4c";
	private static String mavericks = "583ecf50-fb46-11e1-82cb-f4ce4684ea4c";
	private static String pelicans = "583ecc9a-fb46-11e1-82cb-f4ce4684ea4c";
	private static String jazz = "583ece50-fb46-11e1-82cb-f4ce4684ea4c";
	private static String thunder = "583ecfff-fb46-11e1-82cb-f4ce4684ea4c";
	private static String nuggets = "583ed102-fb46-11e1-82cb-f4ce4684ea4c";
	private static String trailblazers = "583ed056-fb46-11e1-82cb-f4ce4684ea4c";
	private static String timberwolves = "583eca2f-fb46-11e1-82cb-f4ce4684ea4c";

	/* maps teams to their corresponding string */
	static void fillMap() {
		teamMap.put("wizards", wizards);
		teamMap.put("hawks", hawks);
		teamMap.put("hornets", hornets);
		teamMap.put("heat", heat);
		teamMap.put("magic", magic);
		teamMap.put("cavaliers", cavaliers);
		teamMap.put("pacers", pacers);
		teamMap.put("pistons", pistons);
		teamMap.put("bulls", bulls);
		teamMap.put("bucks", bucks);
		teamMap.put("celtics", celtics);
		teamMap.put("raptors", raptors);
		teamMap.put("knicks", knicks);
		teamMap.put("sixers", sixers);
		teamMap.put("nets", nets);
		teamMap.put("warriors", warriors);
		teamMap.put("clippers", clippers);
		teamMap.put("kings", kings);
		teamMap.put("lakers", lakers);
		teamMap.put("suns", suns);
		teamMap.put("spurs", spurs);
		teamMap.put("rockets", rockets);
		teamMap.put("grizzlies", grizzlies);
		teamMap.put("mavericks", mavericks);
		teamMap.put("pelicans", pelicans);
		teamMap.put("jazz", jazz);
		teamMap.put("thunder", thunder);
		teamMap.put("nuggets", nuggets);
		teamMap.put("trailblazers", trailblazers);
		teamMap.put("timerwolves", timberwolves);
	}

	/**
	 * Parses a string into a url and then into json format
	 * 
	 * @param String url
	 * @return String stats (a formatted string containing all relevant player statistics)
	 */
	private static String parseURL(String webURL) throws IOException{

		//new URL
		URL url = null;
		JSONParser parser = new JSONParser();
		String json = "";

		try {
			url = new URL(webURL);
		}

		catch (MalformedURLException e) {
			//e.printStackTrace();
			return "\nError: Invalid URL\n";
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			json += inputLine;
		in.close();

		return json;
	}

	/**
	 * Prints out each player and their statistics
	 * 
	 * @param String json (json formatted string)
	 * @param int 	 team (indicates population of a team array or not);
	 */
	private static void getTeamStats(String url, String teamID, Team team) {

		String json = "";

		try {
			json = parseURL(url);
			System.out.println("URL: " + json);
		}

		catch(IOException e) {
			e.printStackTrace();
		}

		JSONArray jsonArray = new JSONArray(json);
		JSONObject total_obj;
		JSONObject own_obj;


		String teamName = (String) jsonArray.getJSONObject(1).toString();
		System.out.println("***" + teamName + "***");

		//JSONArray own_record = (JSONArray)jsonA.getJSONArray("own_record");
		//System.out.println("Own Record " + own_record);
			
		// loop array
		Iterator<Object> iterator = jsonArray.iterator();
		JSONObject obj;
		int i = 2;
		while (iterator.hasNext()) {
			obj = (JSONObject) iterator.next();

			if(obj.has("own_obj") == true) {
				System.out.println(obj.names().toString());
			}	
			JSONArray total = (JSONArray) obj.get("total");
			Iterator<Object> iterator2 = total.iterator();
			while(iterator2.hasNext()) {
				total_obj = (JSONObject) iterator2.next();
				System.out.println(total_obj.names());
						
			}
			
			i++;
		}	
	}

	/**
	 * Prints the statistics of a player
	 * 
	 * @param String playerID
	 */
	private static void printPlayerStats(String playerID, String teamID) {

		String url = playerURL + playerID + statsURL;
		String json = "";
		String seasonIDString;
		String teamIDString;
		JSONObject teamObj;
		JSONObject seasonsObj;

		try {
			json = parseURL(url);
		}

		catch(IOException e) {
			e.printStackTrace();
		}

		try {

			JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(json);
			
			System.out.println("JSON OBJ: " + jsonObject);

			// loop array
			JSONArray seasons = (JSONArray) jsonObject.get("seasons");
			Iterator<Object> iterator = seasons.iterator();

			while (iterator.hasNext()) {
				seasonsObj = (JSONObject) iterator.next();
				seasonIDString = seasonsObj.get("id").toString();

				if(seasonIDString.equals(latestSeason)) {

					System.out.println("\nSeason: " + seasonsObj.get("year") + " (" + seasonsObj.get("type") + ")");
					JSONArray teams = (JSONArray) seasonsObj.get("teams");
					Iterator<Object> iterator2 = teams.iterator();

					while (iterator2.hasNext()) {
						teamObj = (JSONObject) iterator2.next();
						teamIDString = teamObj.get("id").toString();

						if(teamIDString.equals(teamID)) {

							JSONObject averages = (JSONObject) teamObj.get("average");
							JSONObject totals = (JSONObject) teamObj.get("total");
							System.out.println("\naverage:\n" + toStringHelper(averages));         
							System.out.println("total:\n" + toStringHelper(totals));
						}
					}
				}            	
			}
		} 

		catch(ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Helper toString method
	 * 
	 * @param JSONObject set
	 * @return String formated like json
	 */
	private static String toStringHelper(JSONObject set) {

		String str = set.toString();
		char[] charString = str.toCharArray();
		String acc = "";

		for(int i = 0; i < charString.length; i++) {

			if(i == 0 || i == charString.length - 1) {
				acc += "";
			}
			else if(charString[i] == '"') {
				continue;
			}
			else if(charString[i] == ',') {
				acc += ",\n";
			}
			else {
				acc += charString[i];
			}
		}

		acc += "\n------------------------------\n";
		return acc;
	}
}
