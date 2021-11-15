import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FlightTime {

	public static void main(String args[]) {
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("tickets.json"));
			JSONArray tickets = (JSONArray) jsonObject.get("tickets");
			Iterator<Map.Entry> iterator1;
			Iterator iterator2 = tickets.iterator();
			while (iterator2.hasNext()) {
				iterator1 = ((Map) iterator2.next()).entrySet().iterator();
				while (iterator1.hasNext()) {
					Map.Entry pair = iterator1.next();
					System.out.println(pair.getKey() + " : " + pair.getValue());
				}
			}
			/*JSONArray tickets = obj.getJSONArray("tickets");
			for (int i = 0; i < tickets.length(); ++i) {
				JSONObject ticket = tickets.getJSONObject(i);
				System.out.println(ticket.getString("origin"));
				System.out.println(ticket.getString("destination"));
				System.out.println(ticket.getString("departure_time"));
				System.out.println(ticket.getString("arrival_time"));
			}*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
