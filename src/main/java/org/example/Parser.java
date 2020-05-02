package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private String path = null;

    public Parser(String path) {
        this.path = path;
    }

    public List<Segment> parse() {
        String config = readConfigFromFile(this.path);
        List<Segment> segments = parseConfig(config);
        return segments;
    }

    private List<Segment> parseConfig(String data) {
        List<Segment> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(data);
        JSONArray segmentArray = jsonObject.getJSONArray("graph");
        for(int i = 0; i < segmentArray.length(); i++) {
            Segment segment = new Segment();
            JSONObject segObj = (JSONObject) segmentArray.get(i);
            segment.setName(segObj.getString("name"));
            JSONArray attrArray = segObj.getJSONArray("attributes");
            for(int j = 0; j < attrArray.length(); j++) {
                JSONObject entry = attrArray.getJSONObject(j);
                segment.addAttribute(entry.getString("key"),entry.getString("value"));
            }
            list.add(segment);
        }
        return list;
    }


    private String readConfigFromFile(String path) {
        String config = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            StringBuilder buf = new StringBuilder(1024);
            String line = null;
            while((line = reader.readLine()) != null) {
                buf.append(line);
            }
            config = buf.toString();
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }catch (IOException e) {

            }
        }
        return "";
    }



}
