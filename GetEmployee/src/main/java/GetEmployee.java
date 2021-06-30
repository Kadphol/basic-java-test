import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class GetEmployee {
    public static List<Employee> employees = new ArrayList<>();

    public static void getData(int employeeId) throws IOException {
        for(int i = 1; i < 250; i++) {
            String apiURL = "http://jsonplaceholder.typicode.com/photos/" + i;
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode != 200) {
                throw new RuntimeException("Http Response code: " + responseCode);
            } else {
                String line = "";

                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext()) {
                    line += sc.nextLine();
                }

                sc.close();

                JSONObject obj = new JSONObject(line);
                employees.add(new Employee(employeeId,
                        1,
                        Short.parseShort(obj.getString("id")),
                        Short.parseShort(obj.getString("albumId")),
                        obj.getString("title"),
                        obj.getString("url"),
                        obj.getString("thumbnailUrl")
                ));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        int employeeId = Integer.parseInt(args[0]);
        getData(employeeId);
    }
}
