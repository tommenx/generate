package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Segment {
    private String name;
    private Map<String,String> attributes = new LinkedHashMap<>();

    public Segment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    public void addAttribute(String key,String value) {
        this.attributes.put(key,value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("name: "+this.name + "\n");
        for(Map.Entry<String,String> entry : attributes.entrySet()) {
            builder.append(entry.getKey() + ":" + entry.getValue() + "\n");
        }
        return builder.toString();
    }
}
