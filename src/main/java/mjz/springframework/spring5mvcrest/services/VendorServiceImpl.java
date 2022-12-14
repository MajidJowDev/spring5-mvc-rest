package mjz.springframework.spring5mvcrest.services;

import mjz.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import mjz.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import mjz.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import mjz.springframework.spring5mvcrest.controllers.v1.VendorController;
import mjz.springframework.spring5mvcrest.domain.Customer;
import mjz.springframework.spring5mvcrest.domain.Vendor;
import mjz.springframework.spring5mvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        return saveAndReturnDTO(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }

                    VendorDTO returnDto = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                    returnDto.setVendorUrl(getVendorUrl(id));

                    return returnDto;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String getVendorUrl(Long id){
        return VendorController.BASE_URL+ "/" + id;
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }
}
