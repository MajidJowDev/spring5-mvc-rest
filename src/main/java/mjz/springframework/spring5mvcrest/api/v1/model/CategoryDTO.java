package mjz.springframework.spring5mvcrest.api.v1.model;


// A DTO Class (Data Transfer Object) is like data structure, which should not contain any business logic
//but should contain Serialization and deserialization mechanisms, they can have all or partial data from data source
// they can hold data from single or multiple sources as well

// we are going to expose CategoryDTO through the REST Controller, so we need to set up a mapping this to the Category Controller and Catagory POJO
public class CategoryDTO {
    private Long id;
    private String name;
}
