package mjz.springframework.spring5mvcrest.controllers.v1;

import mjz.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import mjz.springframework.spring5mvcrest.controllers.RestResponseEntityExceptionHandler;
import mjz.springframework.spring5mvcrest.services.ResourceNotFoundException;
import mjz.springframework.spring5mvcrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest extends AbstractRestControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllVendorsTest() throws Exception {

        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName("first Vendor");
        vendor1.setVendorUrl(VendorController.BASE_URL + "/1");

        VendorDTO vendor2 = new VendorDTO();
        vendor2.setName("second Vendor");
        vendor2.setVendorUrl(VendorController.BASE_URL + "/2");

        when(vendorService.getAllVendors()).thenReturn(Arrays.asList(vendor1, vendor2));

        mockMvc.perform(MockMvcRequestBuilders.get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    void getVendorByIdTest() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName("first Vendor");
        vendor1.setVendorUrl(VendorController.BASE_URL + "/1");

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor1);

        mockMvc.perform(MockMvcRequestBuilders.get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(("first Vendor"))));

    }

    @Test
    void createNewVendorTest() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("vendorX");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL+"/1");

        when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnDTO);

        //when-then
        mockMvc.perform(MockMvcRequestBuilders.post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("vendorX")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+"/1")));

    }

    @Test
    void updateVendorTest() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("vendorX");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL+"/1");

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        //when-then
        mockMvc.perform(MockMvcRequestBuilders.put(VendorController.BASE_URL +"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("vendorX")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));

    }

    @Test
    void patchVendorTest() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();


        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName("ccc");
        returnDTO.setVendorUrl(VendorController.BASE_URL+"/1");

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        //when-then
        mockMvc.perform(MockMvcRequestBuilders.put(VendorController.BASE_URL +"/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("ccc")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    void deleteVendorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());

    }

    @Test
    public void testNotFoundException() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}