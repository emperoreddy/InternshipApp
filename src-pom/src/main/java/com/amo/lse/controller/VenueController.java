package com.amo.lse.controller;

import com.amo.lse.dto.InstrumentDTO;
import com.amo.lse.dto.MemberDTO;
import com.amo.lse.dto.VenueDTO;
import com.amo.lse.model.VenueEntity;
import com.amo.lse.repository.VenueRepository;
import com.amo.lse.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    VenueRepository venueRepository;
    VenueService venueService;

    public VenueController(VenueRepository venueRepository, VenueService venueService) {
        this.venueRepository = venueRepository;
        this.venueService = venueService;
    }


    @GetMapping
    public ResponseEntity<List<VenueDTO>> getAllVenues() {
        List<VenueDTO> venuesDTO = venueService.getAllVenues();

        return ResponseEntity.ok(venuesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<VenueDTO> getVenue(@PathVariable Long id) {
        VenueEntity venue = venueService.getVenueById(id);
        VenueDTO venueDTO = venueService.mapToVenueDTO(venue);

        return ResponseEntity.ok(venueDTO);
    }

    @GetMapping("{id}/members")
    public List<MemberDTO> getMembersOfVenue(@PathVariable Long id) {
        return venueService.getMembersOfVenue(id);
    }

    @GetMapping("{id}/instruments")
    public List<InstrumentDTO> getInstrumentsOfVenue(@PathVariable Long id) {
        return venueService.getInstrumentsOfVenue(id);
    }

    @PostMapping
    public ResponseEntity<VenueDTO> createVenue(@RequestBody VenueDTO venueDTO) {
        return venueService.createVenue(venueDTO);
    }

    @PutMapping("{id}/instruments/{instrumentId}")
    public ResponseEntity<VenueDTO> addInstruments(@PathVariable(name = "instrumentId") Long instrumentId, @PathVariable(name = "id") Long venueId) {
        return venueService.addInstrument(instrumentId, venueId);
    }

    @PutMapping("{id}")
    public ResponseEntity<VenueDTO> updateVenue(@RequestBody VenueDTO venueDTO, @PathVariable Long id) {
        return venueService.updateVenue(venueDTO, id);
    }


}
