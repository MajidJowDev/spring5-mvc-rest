package mjz.springframework.spring5mvcrest.bootstrap;

import mjz.springframework.spring5mvcrest.domain.Category;
import mjz.springframework.spring5mvcrest.domain.Customer;
import mjz.springframework.spring5mvcrest.domain.Vendor;
import mjz.springframework.spring5mvcrest.repositories.CategoryRepository;
import mjz.springframework.spring5mvcrest.repositories.CustomerRepository;
import mjz.springframework.spring5mvcrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    // Loading data using Spring Events (A context initialization event)
    // and CommandRunner is a Spring Boot specific class and  used to indicate
    // that a bean should run when it is contained within a SpringApplication
    // so this class gets called on startup and any arguments that have been
    // passed into the JVM also get picked up, but here we only to load some classes
    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories(){

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded: "+ categoryRepository.count());

    }

    private void loadCustomers(){
        Customer customerA = new Customer();
        customerA.setId(1L);
        customerA.setFirstname("Majid");
        customerA.setLastname("Xoqi");

        Customer customerB = new Customer();
        customerB.setId(2L);
        customerB.setFirstname("Ali");
        customerB.setLastname("Karimi");

        customerRepository.save(customerA);
        customerRepository.save(customerB);

        System.out.println("Customers Loaded: "+ customerRepository.count());

    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

        System.out.println("Vendors Loaded: "+ vendorRepository.count());

    }

}
