package mjz.springframework.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    //private Long id;
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url") // the way this prop would be shown in a json object
    private String customerUrl;
}
