package com.company;
import java.lang.Object;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class URLCONN {

    public double getRateForCurrency(String fromCurrency, String toCurrency) {
        try {
            URL url = new URL("https://api.currencyapi.com/v3/latest?apikey=cur_live_txqufS78g18ny0SWxL6zVFUk2UDOlYdgbaXGOZOj");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int responseCode = con.getResponseCode();

            if (responseCode != 200) {
                System.out.println("Error: " + responseCode);
                return -1;
            } else {
                String input = "";
                Scanner sc = new Scanner(url.openStream());

                while(sc.hasNext()){
                    input +=sc.nextLine();
                    System.out.println(input);
                }
                sc.close();

                JSONParser par = new JSONParser();
                JSONObject data = (JSONObject) par.parse(input);

                JSONObject rates = (JSONObject) data.get("data");
                if (rates != null) {
                    JSONObject fromRateData = (JSONObject) rates.get(fromCurrency);
                    JSONObject toRateData = (JSONObject) rates.get(toCurrency);

                    if (fromRateData != null && toRateData != null) {
                        double fromRate = Double.parseDouble(fromRateData.get("value").toString());
                        double toRate = Double.parseDouble(toRateData.get("value").toString());


                        // Convert the amount from "fromCurrency" to "toCurrency"
                        return toRate / fromRate;
                    } else {
                        System.out.println("Invalid currency codes provided.");
                        return -1;
                    }
                } else {
                    System.out.println("Rates not found.");
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
