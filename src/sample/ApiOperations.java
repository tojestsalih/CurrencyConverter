package sample;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiOperations {
    private static HttpURLConnection connection;//import
    JSONObject object;
    private String apiLink = "https://api.currencyfreaks.com/latest?apikey=";





    public JSONObject connect(){
        this.apiLink += getApiKey();//merge key with url


        try {
            URL url = new URL(apiLink);
            connection = (HttpURLConnection) url.openConnection();

            //request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(2000);
            connection.connect();

            int status = connection.getResponseCode();
//            System.out.println(status);//testing

            if(status != 200){
                throw new RuntimeException("HttpResponseCode: " + status);
            }
            else{
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());



                //write all the json data into a string using a scanner object
                while(scanner.hasNext()){
                    inline+=scanner.nextLine();
                }

                //close the scanner
                scanner.close();
//                System.out.println(inline);//testing


                //Using the JSON simple library parse the string into a json object
                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(inline);

                //get the required object from the above created object
                JSONObject obj = (JSONObject) data_obj.get("rates");

                //get the required data using its key
    //            System.out.println(obj.get("TRY"));//testing

                return obj;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getApiKey(){
        String apiKey="";

        try {
            File file = new File("C://Users//salih//IdeaProjects//CurrencyConverter//src//sample//apiKey.txt");//where to storage my apikey text file
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                apiKey+=data;
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }


        return apiKey;
    }



    public float convert(String counterCurrency){//
        JSONObject obj = connect();
        String countCurrName = obj.get(counterCurrency).toString();
        float amountCounterCurr = Float.parseFloat(countCurrName);
        return amountCounterCurr;




}}
