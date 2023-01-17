package mjz.springframework.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    @ApiModelProperty(value = "Name of the Venodr", required = true)
    private String name;

    @ApiModelProperty(readOnly = true)
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
