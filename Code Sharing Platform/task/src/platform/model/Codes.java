package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "codes")
public class Codes {
    @Id
    @JsonIgnore
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime date;
    @Column
    private String code;
    @Column
    private Integer time;
    @Column
    private Integer views;

    @Column
    private boolean restricted;
    @Column
    private boolean viewRestricted;
    @Column
    private boolean timeRestricted;

}