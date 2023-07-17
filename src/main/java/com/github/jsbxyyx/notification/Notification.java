package com.github.jsbxyyx.notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author jsbxyyx
 * @since 1.0
 */
public class Notification {

    public static String sendText(String url, String text) {
        String param = "{\"msgtype\":\"text\",\"text\":{\"content\":\"" + Escape.escapeJson(text) + "\", \"mentioned_mobile_list\":[]}}";
        return post(url, param);
    }

    public static String post(String urlString, String param) {
        final StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("user-agent", "Mozilla/5.0");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            out.print(param);
            out.flush();
            out.close();
            int responseCode = conn.getResponseCode();
            if (responseCode > 299) {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

}
