import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.lang.Math;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReadExample {
	public static void main(String[] args) throws Exception {
		Object obj = new JSONParser().parse(new FileReader("tickets.json"));

		JSONObject jo = (JSONObject) obj;
		JSONArray ja = (JSONArray) jo.get("tickets");

		List<Double> time = new ArrayList<Double>();

		Iterator itr2 = ja.iterator();
		Iterator<Map.Entry> itr1;

		DateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
		
		while (itr2.hasNext()) {
			itr1 = ((Map) itr2.next()).entrySet().iterator();
			String departureTimeTemp = "", arrivalTimeTemp = "", departureDateTemp = "", arrivalDateTemp = "";
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				if (pair.getKey().equals("departure_date")) {
					departureDateTemp = (String) pair.getValue();
				} else if (pair.getKey().equals("departure_time")) {
					departureTimeTemp = (String) pair.getValue();
				} else if (pair.getKey().equals("arrival_date")) {
					arrivalDateTemp = (String) pair.getValue();
				} else if (pair.getKey().equals("arrival_time")) {
					arrivalTimeTemp = (String) pair.getValue();
				}
			}
			departureTimeTemp = departureDateTemp + " " + departureTimeTemp;
			arrivalTimeTemp = arrivalDateTemp + " " + arrivalTimeTemp;
			time.add((double) (format.parse(arrivalTimeTemp).getTime() - format.parse(departureTimeTemp).getTime())/1000);
		}


		/*for (double num : time) {
			System.out.printf("Time(in hour): %f\n", num/3600);
		}*/

		System.out.printf("Mean (in seconds): %f\n", mean(time));
		System.out.printf("90th percentile(in seconds): %f\n", percentile(time, 90));
	}

	public static double mean(List<Double> m) {
		double sum = 0;
		for (double num : m) {
			sum += num;
		}
		return sum / m.size();
	}

	public static double percentile(List<Double> time, double percentile) {
		Collections.sort(time);
		int index = (int) Math.ceil(percentile / 100.0 * time.size());
		return time.get(index-1);
	}
}
