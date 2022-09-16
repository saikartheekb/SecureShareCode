package platform.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeDto {
    private String date;
    private String code;
    private Integer time;
    private Integer views;


    CodeDto(Codes codes) {
        String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        date = codes.getDate().format(formatter);
        code = codes.getCode();
        views = codes.getViews();
        time = codes.getTime();
    }
}