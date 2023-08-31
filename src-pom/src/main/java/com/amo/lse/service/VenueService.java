package com.amo.lse.service;

import com.amo.lse.dto.InstrumentDTO;
import com.amo.lse.dto.MemberDTO;
import com.amo.lse.dto.VenueDTO;
import com.amo.lse.model.InstrumentEntity;
import com.amo.lse.model.VenueEntity;
import com.amo.lse.repository.InstrumentRepository;
import com.amo.lse.repository.VenueRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService {

    VenueRepository venueRepository;
    MemberService memberService;
    InstrumentRepository instrumentRepository;
    InstrumentService instrumentService;

    public VenueService(VenueRepository venueRepository, MemberService memberService, InstrumentRepository instrumentRepository, InstrumentService instrumentService) {
        this.venueRepository = venueRepository;
        this.memberService = memberService;
        this.instrumentRepository = instrumentRepository;
        this.instrumentService = instrumentService;
    }

    public ResponseEntity<VenueDTO> createVenue(VenueDTO venueDTO) {
        VenueEntity venueEntity = new VenueEntity();

        venueEntity.setId(venueDTO.getId());
        venueEntity.setCity(venueDTO.getCity());
        venueEntity.setCountry(venueDTO.getCountry());
        venueEntity.setName(venueDTO.getName());

        VenueEntity savedVenueEntity = venueRepository.save(venueEntity);

        VenueDTO savedDTO = mapToVenueDTO(savedVenueEntity);

        return ResponseEntity.ok(savedDTO);
    }

    public ResponseEntity<VenueDTO> addInstrument(Long instrumentId, Long venueId) {
        VenueEntity venueEntity = venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceAccessException("Venue doesn't exist with id: " + venueId));

        InstrumentEntity instrumentEntity = instrumentRepository.findById(instrumentId)
                .orElseThrow(() -> new ResourceAccessException("Instrument doesn't exist with id: " + instrumentId));

        venueEntity.addInstrument(instrumentEntity);

        VenueEntity savedEntity = venueRepository.save(venueEntity);

        VenueDTO savedDTO = mapToVenueDTO(savedEntity);

        return ResponseEntity.ok(savedDTO);
    }

    public  List<VenueDTO> getAllVenues() {
        List<VenueEntity> venueEntities = venueRepository.findAll();

        List<VenueDTO> venuesDTO = venueEntities.stream()
                .map(venueEntity -> mapToVenueDTO(venueEntity))
                .collect(Collectors.toList());

        return venuesDTO;
    }

    public List<MemberDTO> getMembersOfVenue(Long id) {
        VenueEntity venueEntity = getVenueById(id);

        return venueEntity.getMemberEntities()
                .stream()
                .map(memberEntity -> memberService.mapToDto(memberEntity))
                .collect(Collectors.toList());
    }

    public ResponseEntity<VenueDTO> updateVenue(@RequestBody VenueDTO venueDTO, @PathVariable Long id) {
        VenueEntity venueEntity = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Venue doesn't exist with id: " + id));

        venueEntity.setName(venueDTO.getName());
        venueEntity.setCountry(venueDTO.getCountry());
        venueEntity.setCity(venueDTO.getCity());

        VenueEntity savedEntity = venueRepository.save(venueEntity);

        VenueDTO savedDTO = mapToVenueDTO(savedEntity);

        return ResponseEntity.ok(savedDTO);
    }

    public VenueDTO mapToVenueDTO(VenueEntity venueEntity) {
        VenueDTO venueDTO = new VenueDTO();

        venueDTO.setId(venueEntity.getId());
        venueDTO.setCity(venueEntity.getCity());
        venueDTO.setCountry(venueEntity.getCountry());
        venueDTO.setName(venueEntity.getName());

        return venueDTO;
    }


    public VenueEntity getVenueById(Long id) { // TODO: modify return type
        return venueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venue not found with id " + id));
    }

    public List<InstrumentDTO> getInstrumentsOfVenue(Long id) {
        VenueEntity venueEntity = getVenueById(id);

        return venueEntity.getInstrumentEntities()
                .stream()
                .map(instrumentEntity -> instrumentService.mapToDTO(instrumentEntity))
                .collect(Collectors.toList());
    }

    private InstrumentEntity getInstrumentById(Long id) {
        return instrumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Intrument not found with id " + id));

    }

    
}

