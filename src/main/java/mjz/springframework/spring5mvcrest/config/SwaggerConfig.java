package mjz.springframework.spring5mvcrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2  //this annotation brings swagger support to the project
@Configuration
public class SwaggerConfig {

    //if we had specific API's that we wanted to expose, we could do so, or if we wanted to limit them
    // this configuration returns back Docket object into Spring context
    // We can use this configurations if we wanted more control over the swagger config, we can use the Docket
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) // set for any APIs or requestHandlers
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }
}
