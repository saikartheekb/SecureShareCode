package platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Codes {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime date;
    private String code;

    @Override
    public String toString() {
        return code;
    }
}
