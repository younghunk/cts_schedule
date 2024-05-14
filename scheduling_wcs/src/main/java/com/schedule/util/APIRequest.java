package com.schedule.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.schedule.enums.*;

import lombok.Setter;

@Setter
public class APIRequest {

    private String url;
    private Method method;
    private Response responseType;

    public APIRequest(String url, Method method, Response response) {
        this.url = url;
        this.method = method;
        this.responseType = response;
    }

    private HashMap<String, Object> requestBody;

    public <T> T getRestAPIResponse(Class<T> T) {
        try {
            StringBuilder response = new StringBuilder();
            java.net.URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(method.getMethod());
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", responseType.getResponseType());
            con.setDoOutput(true);

            if (requestBody != null) {
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = new ObjectMapper().writeValueAsString(requestBody).getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            con.setConnectTimeout(10000);
            con.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();
            if (responseType.equals(Response.JSON))
                return new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .readValue(response.toString(), T);
            else
                return new XmlMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .readValue(response.toString(), T);

        } catch (Exception e) {
            return null;
        }
    }
}
