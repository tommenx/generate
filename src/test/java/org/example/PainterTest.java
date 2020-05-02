package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PainterTest {
    @Test
    public void testSetBreakBuilder() {
        List<Segment> segments = new ArrayList<>();
        Segment segment = new Segment();
        segment.setName("event data");
        segment.addAttribute("binlog_version", "19 : 2");
        segment.addAttribute("server_version", "21 : 50");
        segment.addAttribute("create_timestssssamp", "71 : 4");
        segments.add(segment);
        Painter painter = new Painter.Builder()
                .header(10)
                .body(30)
                .segments(segments)
                .build();
//        painter.setBreakBuilder();
//        painter.setHeaderBuilder(segment.getName(),7);
//        painter.setBodyBuilder(segment.getAttributes(),7);
//        painter.setBreakBuilder();
        painter.setSegmentBuilder();
        System.out.println(painter.toString());
    }
}
