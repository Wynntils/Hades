package com.wynntils.hades;

import com.vdurmont.semver4j.Semver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Hades {
    // Singleton Class

    private static Hades INSTANCE = null;

    public static Hades getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Hades();
        }
        return INSTANCE;
    }
    private Hades() {
        // Constructor
    }

    private Semver version = null;

    public Semver getVersion() {
        if (version == null) {
            // Read JSON from resources folder and extract version
            String json = getResource("hades.json");

            // Parse JSON using simple java JSON parser
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(json);
                version = new Semver((String) obj.get("version"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return version;
    }


    public String getResource(String resource) {
        StringBuilder json = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resource)),
                            StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null)
                json.append(str);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Caught exception reading resource " + resource, e);
        }
        return json.toString();
    }
}
