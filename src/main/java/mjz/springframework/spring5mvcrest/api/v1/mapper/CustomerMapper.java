package mjz.springframework.spring5mvcrest.api.v1.mapper;

import mjz.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import mjz.springframework.spring5mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class); // initiate this class as a Mapper

    CustomerDTO customerToCustomerDTO(Customer customer);
}
