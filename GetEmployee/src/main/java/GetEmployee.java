import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;
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
                        Short.parseShort(String.valueOf(obj.getInt("id"))),
                        Short.parseShort(String.valueOf(obj.getInt("albumId"))),
                        obj.getString("title"),
                        obj.getString("url"),
                        obj.getString("thumbnailUrl")
                ));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        try {
            int employeeId = Integer.parseInt(args[0]);
            System.out.println("Getting Data...");
            getData(employeeId);
            List<String[]> data = new ArrayList<>();
            System.out.println("Writting Data...");
            String[] header = {"EmployeeId", "ThreadId", "Id", "AlbumId", "Title", "Url", "ThumbnailUrl"};
            data.add(header);
            for(Employee employee: employees) {
                String[] tmp = {
                        String.valueOf(employee.getEmployeeId()),
                        String.valueOf(employee.getThreadId()),
                        String.valueOf(employee.getId()),
                        String.valueOf(employee.getAlbumId()),
                        employee.getTitle(),
                        employee.getUrl(),
                        employee.getThumbnailUrl()
                };
                data.add(tmp);
            }
            writeCSV(data);
            System.out.println("Finished.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Employee ID needed: " + String.valueOf(e));
        }
    }

    private static void writeCSV(List<String[]> data) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("employee.csv"));
            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
