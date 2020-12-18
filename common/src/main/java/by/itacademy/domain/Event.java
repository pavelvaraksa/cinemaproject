package by.itacademy.domain;

import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Data
public class Event {

    private Long id;

    private String Date;

    private String Time;

    private int ticketsCount;

    private Timestamp created;

    private Timestamp changed;

    private Long movieId;

    private Long cinemaId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
