package mjz.springframework.spring5mvcrest.controllers.v1;

import mjz.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import mjz.springframework.spring5mvcrest.api.v1.model.CategoryListDTO;
import mjz.springframework.spring5mvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@Controller  // spring 3
@RestController  // in Spring 4 we can use this annotation instead of @Controller and instead of using
//@RequestMapping("/api/v1/categories/")
//@RequestMapping("${some.url.value}") // we can also use this method to define Base Urls in application properties
// so that they can be loaded when spring context is loading
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // in Spring 4 we can remove ResponseEntity and use @ResponseStatus
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

    /*
    // this is the old way in Spring 3
    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){

        return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }



    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<CategoryDTO>(
                categoryService.getCategoryByName(name), HttpStatus.OK );
    }
    */

}
