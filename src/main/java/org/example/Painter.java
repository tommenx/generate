package org.example;

import java.sql.Statement;
import java.util.*;

public class Painter {
    private List<StringBuilder> lines;
    private List<Segment> segments;
    private List<Integer> lineCount;
    private int headerLen;
    private int bodyLen;
    private int start = 0;

    private Painter(Builder builder) {
        this.segments = builder.segments;
        this.headerLen = builder.headerLen;
        this.bodyLen = builder.bodyLen;
        init();
    }


    private void init() {
        if (segments == null || segments.size() == 0) {
            return;
        }
        lineCount = new ArrayList<>();
        int size = 0;
        for (Segment segment : segments) {
            int segmentSize = 0;
            int attributesSize = 0;
            segmentSize = segment.getName().length() / ((headerLen + 2) / 2) + 1;
            for (Map.Entry<String, String> entry : segment.getAttributes().entrySet()) {
                attributesSize += (entry.getKey().length() / ((bodyLen + 2) / 2) + 1);
                attributesSize++;
            }
            segmentSize = Math.max(segmentSize, attributesSize);
            lineCount.add(segmentSize);
            size += segmentSize;
        }
        lines = new ArrayList<>(size);
    }

    public String paint() {
        String res = "";
//        head
        setSegmentBuilder();
        res = toString();
        return res;
    }

    public void setBreakBuilder() {
        StringBuilder builder = new StringBuilder();
        int total = bodyLen + headerLen;
        builder.append('+');
        for (int i = 0; i < total; i++) {
            builder.append('=');
        }
        builder.append('+');
        if (this.start == 0) {
            lines.add(builder);
            this.start++;
        } else {
            lines.set(start - 1, builder);
        }

    }

    public void setSegmentBuilder() {
        for(int i = 0; i < segments.size(); i++) {
            setBreakBuilder();
            setHeaderBuilder(segments.get(i).getName(),lineCount.get(i));
            setBodyBuilder(segments.get(i).getAttributes(),lineCount.get(i));
        }
        setBreakBuilder();

    }

    public void setHeaderBuilder(String name, int count) {
        int splitCount = (headerLen + 2) / 2;
        int i = this.start;
        StringBuilder buf = new StringBuilder();
        while (i < count + this.start) {
            buf.append('|');
            buf.append(' ');
            int c = 2;
            if (name.length() > 0) {
                int endIndex = Math.min(name.length(), splitCount);
                buf.append(name.substring(0, endIndex));
                c += endIndex;
                name = name.substring(endIndex);
            }
            while (c < headerLen - 1) {
                buf.append(' ');
                c++;
            }
            lines.add(buf);
            buf = new StringBuilder();
            i++;
        }

    }

    public void setBodyBuilder(Map<String, String> attributes, int count) {
        int splitCount = (bodyLen + 2) / 2;
        int i = this.start;
        boolean yes = true;
        Iterator<Map.Entry<String, String>> iterator = attributes.entrySet().iterator();
        Map.Entry<String, String> entry = iterator.next();
        String key = entry.getKey();
        String val = entry.getValue();
        while (i < (count + this.start)) {
            StringBuilder buf = lines.get(i);
            if (yes) {
                buf.append('|');
                buf.append(' ');
                int spanCount = bodyLen - 1 - Math.min(key.length(), splitCount) - val.length() - 4;
                if (key.length() > 0) {
                    int endIndex = Math.min(key.length(), splitCount);
                    buf.append(key.substring(0, endIndex));
                    key = key.substring(endIndex);
                }
                int sc = 0;
                while (sc < spanCount) {
                    buf.append(' ');
                    sc++;
                }
                buf.append(val);
                buf.append("    ");
                buf.append('|');
                if (key.length() == 0) {
                    if (iterator.hasNext()) {
                        entry = iterator.next();
                        key = entry.getKey();
                        val = entry.getValue();
                    }
                    yes = false;
                }
            } else {
                yes = true;
                buf.append('+');
                for (int c = 1; c <= bodyLen; c++) {
                    buf.append('-');
                }
                buf.append('+');
            }
            i++;
        }
        this.start += count;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (StringBuilder builder : lines) {
            res.append(builder.toString());
            res.append('\n');
        }
        return res.toString();
    }


    static class Builder {
        private List<Segment> segments;
        private int headerLen = 10;
        private int bodyLen = 30;

        public Builder() {
        }

        public Builder header(int headerLen) {
            this.headerLen = headerLen;
            return this;
        }

        public Builder body(int bodyLen) {
            this.bodyLen = bodyLen;
            return this;
        }

        public Builder segments(List<Segment> segments) {
            this.segments = segments;
            return this;
        }

        public Painter build() {
            return new Painter(this);
        }


    }
}
