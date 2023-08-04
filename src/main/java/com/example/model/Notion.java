package com.example.model;

import lombok.Data;


/**
 * The {@code Notion} class represents a notion object.
 * It stores the attributes of a notion, including a reboot, peteId, title, and message.
 * The class uses Lombok's {@code @Data} annotation to automatically generate getter and setter methods.
 *
 * <p>
 * Example usage:
 * <pre>
 *     Notion notion = new Notion();
 *     notion.setReboot("reboot");
 *     notion.setPereId("pereId");
 *     notion.setTitle("title");
 *     notion.setMsg("message");
 * </pre>
 * </p>
 *
 * @see Data
 */

@Data
public class Notion {
    private String reboot;
    private String pereId;
    private String title;
    private String msg;
}
