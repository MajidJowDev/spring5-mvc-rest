package mjz.springframework.spring5mvcrest.api.v1.mapper;

import mjz.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import mjz.springframework.spring5mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorMapperTest {
    public static final String NAME = "someName";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() throws Exception {
        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //then
        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void vendorDTOtoVendor() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        //when
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}
