package by.itacademy.domain;

import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Data
public class Cinema {

    private Long id;

    private String name;

    private int ticketsCount;

    private String phoneNumber;

    private String paymentMethod;

    private Timestamp created;

    private Timestamp changed;

    private Long locationId;

    private Long movieId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
