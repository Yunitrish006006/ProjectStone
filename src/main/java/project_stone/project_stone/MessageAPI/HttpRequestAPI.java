package project_stone.project_stone.MessageAPI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequestAPI {

    public String httpRequest(String method,String targetUrl,String requestBodyJson) throws IOException {
        HttpURLConnection connection;
        StringBuffer sb = new StringBuffer();
        URL url = new URL(targetUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod(method);// POST,GET,POST,DELETE,INPUT
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        //connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        //connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        //connection.setRequestProperty("charset", "UTF-8");
        connection.connect();
        OutputStream out = connection.getOutputStream();
        out.write(requestBodyJson.getBytes());
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String lines;
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
            sb.append( lines);
        }
        System.out.println(sb);
        reader.close();
        connection.disconnect();
        return "OK,200";
    }
}