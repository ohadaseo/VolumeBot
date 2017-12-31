
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ohadaseo on 31/12/2017.
 */
public class Program {

    public static void main(String[] args){
        try {
            String response = performWebRequestAndGetResponse("https://api.coinmarketcap.com/v1/ticker/");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response);
            JSONArray res = (JSONArray) obj;

            for(int i=0 ; i<res.size() ; i++) {
                JSONObject curr = (JSONObject) res.get(i);
                System.out.println(curr.get("24h_volume_usd"));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void parseFile(){
        File[] files = new File(Program.class.getClassLoader().getResource("data").getFile()).listFiles();

    }
    public static String performWebRequestAndGetResponse(String request){
        StringBuffer response = null;
        try{
            URL obj = new URL(request);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(	new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return response.toString();
    }

    public String readFromFile(String fileName){
        StringBuilder outputString = new StringBuilder();
        try {
            byte[] buffer = new byte[1000];
            FileInputStream inputStream =	new FileInputStream(fileName);
            int total = 0;
            int nRead = 0;
            while((nRead = inputStream.read(buffer)) != -1) {
                outputString.append(new String(buffer));
                total += nRead;
            }
            inputStream.close();
            System.out.println("Read " + total + " bytes");
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '" 	+ fileName + "'");
        }
        return outputString.toString();
    }
}
