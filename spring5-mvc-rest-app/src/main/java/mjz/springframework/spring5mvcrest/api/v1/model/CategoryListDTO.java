package mjz.springframework.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {

    // since we are mimicking the api.predic8.de API since the list of categories are defined as an actual object
    // we need to add this class to make the response object look like that site
    List<CategoryDTO> categories;
}
