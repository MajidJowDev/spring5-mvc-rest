package mjz.springframework.spring5mvcrest.services;

import mjz.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import mjz.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import mjz.springframework.spring5mvcrest.domain.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
