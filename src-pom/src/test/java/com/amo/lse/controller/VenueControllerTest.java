package com.amo.lse.controller;

import com.amo.lse.model.VenueEntity;
import com.amo.lse.repository.VenueRepository;
import com.amo.lse.service.VenueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class VenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private VenueService venueService;

    @Mock
    private VenueRepository venueRepository;


//    @Test
//    public void testCreateVenue() {
//        VenueDTO venueDTO = new VenueDTO();
//        venueDTO.setName("Lse");
//        venueDTO.setCountry("UK");
//        venueDTO.setCity("London");
//
//
//        // Create a sample VenueEntity that is expected to be saved
//        VenueEntity venueEntityToSave = new VenueEntity();
//        venueEntityToSave.setName("Lse");
//        venueEntityToSave.setCity("London");
//        venueEntityToSave.setCountry("UK");
//
//        // Create a sample VenueEntity that is expected to be returned after saving
//        VenueEntity savedVenueEntity = new VenueEntity();
//        savedVenueEntity.setId(1L);
//        savedVenueEntity.setName("Lse");
//        savedVenueEntity.setCity("London");
//        savedVenueEntity.setCountry("UK");
//
//        // Mock the behavior of the repository's save method
//        when(venueRepository.save(venueEntityToSave)).thenReturn(savedVenueEntity);
//
//        // Call the service method
//        VenueDTO result = venueService.createVenue(venueDTO);
//
//        // Assert that the result matches the expected savedVenueEntity
//        assertEquals(savedVenueEntity.getId(), result.getId());
//        assertEquals(savedVenueEntity.getName(), result.getName());
//        assertEquals(savedVenueEntity.getCity(), result.getCity());
//        assertEquals(savedVenueEntity.getCountry(), result.getCountry());
//
//        // Verify that the repository's save method was called with the expected entity
//        verify(venueRepository).save(venueEntityToSave);
//    }


    @Test
    public void testGetVenueByIdWhenNoVenue() {
        assertThrows(EntityNotFoundException.class, () -> venueService.getVenueById(1L));
    }

    @Test
    public void testGetVenueByIdWhenExist() {
        VenueEntity venueEntity = new VenueEntity();
        venueEntity.setCity("london");
        venueEntity.setCountry("uk");
        venueEntity.setName("lse");
        venueEntity.setId(1L);

        when(venueRepository.findById(1L)).thenReturn(Optional.of(venueEntity));

        VenueEntity actualEntity = venueService.getVenueById(1L);

        assertEquals(venueEntity, actualEntity);
    }

}