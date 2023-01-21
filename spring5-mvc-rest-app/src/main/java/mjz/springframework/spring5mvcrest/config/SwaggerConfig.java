package mjz.springframework.spring5mvcrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2  //this annotation brings swagger support to the project
@Configuration
public class SwaggerConfig /*extends WebMvcConfigurationSupport*/ { // if we did not see the swagger-ui in web we have to extend the config class to support MVC Config

    //http://localhost:8080/v2/api-docs
    //http://localhost:8080/swagger-ui.html

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
                .pathMapping("/")
                .apiInfo(metaData()); // adds the api information to swagger UI
    }

    //if we are not in a spring boot project we should add our resources manually like below
    // but springboot automatically detects the following resources and configures them
    // and if swagger ui did not come up, adding these resources should fix the issue
   /*
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    */

    // added this metadata to the swagger UI
    private ApiInfo metaData(){

        Contact contact = new Contact("Majid Xoqi", "https://fiverr.com/itdev_solutions",
                "majid.jow@gmail.com");

        return new ApiInfo(
                "My RestFul API Example",
                "Spring Framework 5: RestFul API Examples",
                "1.0",
                "Terms of Service: My terms",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
