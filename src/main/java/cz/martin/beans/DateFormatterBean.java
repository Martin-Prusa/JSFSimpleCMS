package cz.martin.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named("formatter")
@ApplicationScoped
public class DateFormatterBean {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public String getFormattedDate(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

}
