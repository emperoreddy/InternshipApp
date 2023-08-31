package com.amo.lse.service;

import com.amo.lse.dto.InstrumentDTO;
import com.amo.lse.dto.IssuerDTO;
import com.amo.lse.model.InstrumentEntity;
import com.amo.lse.model.IssuerEntity;
import com.amo.lse.repository.IssuerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuerService {

    IssuerRepository issuerRepository;
    InstrumentService instrumentService;

    public IssuerService(InstrumentService instrumentService, IssuerRepository issuerRepository) {
        this.issuerRepository = issuerRepository;
        this.instrumentService = instrumentService;
    }

    public ResponseEntity<IssuerDTO> createIssuer(IssuerDTO issuerDTO) {
        IssuerEntity issuerEntity = new IssuerEntity();

        issuerEntity.setDescription(issuerDTO.getDescription());
        issuerEntity.setId(issuerDTO.getId());
        issuerEntity.setLEI(issuerDTO.getLEI());
        issuerEntity.setLegalName(issuerDTO.getLegalName());
        issuerEntity.setInstrumentEntities(null);

        IssuerEntity savedEntity = issuerRepository.save(issuerEntity);

        IssuerDTO savedDto = mapToDto(savedEntity);

        return ResponseEntity.ok(savedDto);
    }


    public IssuerDTO mapToDto(IssuerEntity issuerEntity) {
        IssuerDTO issuerDTO = new IssuerDTO();

        issuerDTO.setDescription(issuerEntity.getDescription());
        issuerDTO.setId(issuerEntity.getId());
        issuerDTO.setLEI(issuerEntity.getLEI());
        issuerDTO.setLegalName(issuerEntity.getLegalName());

        return issuerDTO;
    }


    public List<IssuerDTO> getAllIssuers() {
        List<IssuerEntity> issuerEntities = issuerRepository.findAll();

        List<IssuerDTO> issuerDTOS = issuerEntities.stream()
                .map(issuerEntity -> mapToDto(issuerEntity))
                .collect(Collectors.toList());

        return issuerDTOS;
    }

    public IssuerEntity findIssuerById(Long issuerId) {
        IssuerEntity issuerEntity = issuerRepository.findById(issuerId)
                .orElseThrow(() -> new EntityNotFoundException("Issuer not found with id" + issuerId));

        return issuerEntity;
    }

    public ResponseEntity<IssuerDTO> addInstrument(Long issuerId, Long instrumentId) {
        IssuerEntity issuerEntity = findIssuerById(issuerId);
        InstrumentEntity instrumentEntity = instrumentService.findInstrumentById(instrumentId);

        if (!issuerEntity.getInstrumentEntities().contains(instrumentEntity)) {
            issuerEntity.addInstrument(instrumentEntity);
            IssuerEntity savedEntity = issuerRepository.save(issuerEntity);
            IssuerDTO savedDto = mapToDto(savedEntity);
            return ResponseEntity.ok(savedDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    public ResponseEntity<List<InstrumentDTO>> getInstrumentsOfIssuer(Long issuerId) {
        IssuerEntity issuerEntity = findIssuerById(issuerId);

        List<InstrumentDTO> instrumentDTOs = issuerEntity.getInstrumentEntities()
                .stream()
                .map(instrumentEntity -> instrumentService.mapToDTO(instrumentEntity)) // Assuming you have a method to map to InstrumentDTO
                .collect(Collectors.toList());

        return ResponseEntity.ok(instrumentDTOs);
    }


    public void deleteIssuer(Long issuerId) {
       issuerRepository.deleteById(issuerId);
    }

    public ResponseEntity<IssuerDTO> getIssuerById(Long issuerId) {
        IssuerEntity issuerEntity = issuerRepository.findById(issuerId)
                .orElseThrow(() -> new EntityNotFoundException("Issuer not found with id " + issuerId));

        IssuerDTO issuerDTO = mapToDto(issuerEntity);

        return ResponseEntity.ok(issuerDTO);
    }

    public ResponseEntity<IssuerDTO> updateIssuer(Long issuerId, IssuerDTO issuerDTO) {
        IssuerEntity issuerEntity = findIssuerById(issuerId);

        issuerEntity.setDescription(issuerDTO.getDescription());
        issuerEntity.setLEI(issuerDTO.getLEI());
        issuerEntity.setLegalName(issuerDTO.getLegalName());

        IssuerEntity savedEntity = issuerRepository.save(issuerEntity);

        IssuerDTO savedDto = mapToDto(savedEntity);

        return ResponseEntity.ok(savedDto);
    }
}
