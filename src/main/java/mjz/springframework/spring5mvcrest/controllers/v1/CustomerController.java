package mjz.springframework.spring5mvcrest.controllers.v1;

import mjz.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import mjz.springframework.spring5mvcrest.api.v1.model.CustomerListDTO;
import mjz.springframework.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController // uses ResponseBody to return the response in requested type (XML, JSON, etc)
//@RequestMapping("/api/v1/customers")
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers(){
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        // we want the object bound automatically so we use @RequestBody which tells Spring to check and parse
        // the body of the request and try to create a customerDTO out of that
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    /*
    the difference between PATCH and PUT is that if we do not add some of the json attribute in requestBody
    in a PUT Request, all json attributes that are not added to the requestBody (json Body) will be set to null
    for example if we send
    {"firstname": "Ali"} then the object values will be set to {"firstname": "Ali", "lastname": null}
    whereas
    in a PATCH Request if we just add firstname as json attrib in our json body the other attributes of the object will not be set to null
    and keep their previous value
    send {"firstname": "Ali"} as request body -> modified object: {"firstname": "Ali", "lastname": "sss"}
     */
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomerById(id);
    }

    /* // Spring 3 old way
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    */

}
