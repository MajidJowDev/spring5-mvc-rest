package mjz.springframework.spring5mvcrest.controllers.v1;


import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestControllerTest {

    // added to convert an object to json string using Jackson
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj); // this line converts the Object to Json String
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }
}
