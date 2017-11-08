package com.godeltech.edushop.rest;

import com.godeltech.edushop.dto.RateDTO;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Dmitry on 07.11.2017.
 */
@Component
public class NetClientGet {
    public RateDTO getRate(){
        String result = null;

        try {

            URL url = new URL("https://api.fixer.io/latest?base=USD&symbols=EUR,GBP");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                result = output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        Gson g = new Gson();
        RateDTO rateDTO = g.fromJson(result, RateDTO.class);

        return rateDTO;
    }
}
