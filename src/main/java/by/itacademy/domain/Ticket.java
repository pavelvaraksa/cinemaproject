package by.itacademy.domain;

import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Data
public class Ticket {

    private Long id;

    private int placeNumber;

    private double price;

    private Timestamp created;

    private Timestamp changed;

    private Long userId;

    private Long eventId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
