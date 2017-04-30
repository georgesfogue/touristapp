package com.example.georges.tourapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
/**
 * Created by Georges on 30/04/2017.
 */

public class NetworkUtil {
    public static String getResponseFromHttpUrl(String url) throws IOException {

        URL urlObject = new URL(url);

        HttpURLConnection urlConnection =
                (HttpURLConnection) urlObject.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
