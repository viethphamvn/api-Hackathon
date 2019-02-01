package direction;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.*;
import org.jsoup.Jsoup;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class direction {
    
    private final String  endpoint = "https://maps.googleapis.com/maps/api/directions/json?";
    private final String API_key = "AIzaSyC4i8VAgGQWY2jgzSQSsoXgshRX0Mtnzz8"; 
    private String result_html = "";
    private String result_raw = "";
    
    public direction(String ori, String des, String mode) throws IOException{
        //Standardize location addresses
        String[] ori_arr = ori.split(" ");
        String[] des_arr = des.split(" ");

        String ori_fin = "";
        String des_fin = "";
        
        ori_fin += ori_arr[0];
        des_fin += des_arr[0];
        
        for (String w : ori_arr){
            ori_fin += "+";
            ori_fin += w;
        }
        
        for (String w : des_arr){
            des_fin += "+";
            des_fin += w;
        }
        
        //Request and Get result
        String json = "";
        
        HttpURLConnection connection = null;
        URL url = new URL(endpoint + "origin=" + ori_fin + "&destination=" + des_fin + "&mode=" + mode + "&key=" + API_key);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        try (Scanner sc = new Scanner(url.openStream())) {
            while (sc.hasNext()){
                json += sc.nextLine();
            }
        }
        
        //Filter result
        JSONObject obj = new JSONObject(json);
        JSONObject route = obj.getJSONArray("routes").getJSONObject(0);
        JSONObject leg = route.getJSONArray("legs").getJSONObject(0);
        JSONArray step = leg.getJSONArray("steps");
        
        //Extract result
        for (int i = 0; i < step.length(); i++){
            result_html = result_html + step.getJSONObject(i).getString("html_instructions") + "\n";
            result_raw = result_raw + Jsoup.parse(step.getJSONObject(i).getString("html_instructions")).text() + "\n";
        }
    }
    
    public String getHtml(){
        return this.result_html;
    }
    
    public String getRaw(){
        return this.result_raw;
    }
}
