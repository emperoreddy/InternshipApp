package com.amo.lse.service;

import com.amo.lse.dto.InstrumentDTO;
import com.amo.lse.model.InstrumentEntity;
import com.amo.lse.model.IssuerEntity;
import com.amo.lse.repository.InstrumentRepository;
import com.amo.lse.repository.IssuerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentService {

    InstrumentRepository instrumentRepository;
    IssuerRepository issuerRepository;

    public InstrumentService(IssuerRepository issuerRepository, InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
        this.issuerRepository = issuerRepository;
    }

    public InstrumentDTO mapToDTO(InstrumentEntity instrumentEntity) {
        InstrumentDTO instrumentDTO = new InstrumentDTO();

        instrumentDTO.setDescription(instrumentEntity.getDescription());
        instrumentDTO.setId(instrumentEntity.getId());
        instrumentDTO.setCurrency(instrumentEntity.getCurrency());
        instrumentDTO.setISIN(instrumentEntity.getISIN());
        instrumentDTO.setStatus(instrumentEntity.getStatus());
        instrumentDTO.setEffectiveDate(instrumentEntity.getEffectiveDate());
        instrumentDTO.setType(instrumentEntity.getType());
        instrumentDTO.setIssuerId(instrumentEntity.getIssuerEntity().getId());

        return instrumentDTO;
    }

    public ResponseEntity<InstrumentDTO> createInstrument(InstrumentDTO instrumentDTO) {
        InstrumentEntity instrumentEntity = new InstrumentEntity();

        instrumentEntity.setId(instrumentDTO.getId());
        instrumentEntity.setDescription(instrumentDTO.getDescription());
        instrumentEntity.setCurrency(instrumentDTO.getCurrency());
        instrumentEntity.setISIN(instrumentDTO.getISIN());
        instrumentEntity.setStatus(instrumentDTO.getStatus());
        instrumentEntity.setEffectiveDate(instrumentDTO.getEffectiveDate());
        instrumentEntity.setType(instrumentDTO.getType());

        IssuerEntity issuerEntity = issuerRepository.findById(instrumentDTO.getIssuerId())
                .orElseThrow(() -> new EntityNotFoundException("Issuer not found with id " + instrumentDTO.getIssuerId()));

        instrumentEntity.setIssuerEntity(issuerEntity);

        InstrumentEntity savedInstrumentEntity = instrumentRepository.save(instrumentEntity);

        InstrumentDTO savedDTO = mapToDTO(savedInstrumentEntity);

        return ResponseEntity.ok(savedDTO);
    }

    public List<InstrumentDTO> getAllInstruments() {
        List<InstrumentEntity> instrumentEntities = instrumentRepository.findAll();

        List<InstrumentDTO> instrumentDTOS = instrumentEntities
                .stream()
                .map(instrumentEntity -> mapToDTO(instrumentEntity))
                .collect(Collectors.toList());

        return instrumentDTOS;
    }

    public InstrumentEntity findInstrumentById(Long instrumentId) {
        InstrumentEntity instrumentEntity = instrumentRepository.findById(instrumentId)
                .orElseThrow(() -> new EntityNotFoundException("Instrument not found with id " + instrumentId));

        return instrumentEntity;
    }

    public ResponseEntity<InstrumentDTO> updateInstrument(InstrumentDTO instrumentDTO, Long instrumentId) {
        InstrumentEntity instrumentEntity = findInstrumentById(instrumentId);

        instrumentEntity.setCurrency(instrumentDTO.getCurrency());
        instrumentEntity.setISIN(instrumentDTO.getISIN());
        instrumentEntity.setStatus(instrumentDTO.getStatus());
        instrumentEntity.setEffectiveDate(instrumentDTO.getEffectiveDate());
        instrumentEntity.setType(instrumentDTO.getType());
        instrumentEntity.setDescription(instrumentDTO.getDescription());

        instrumentRepository.save(instrumentEntity);

        InstrumentDTO savedDto = mapToDTO(instrumentEntity);

        return ResponseEntity.ok(savedDto);

    }

    public ResponseEntity<InstrumentDTO> getInstrumentById(Long instrumentId) {
        InstrumentEntity instrumentEntity = findInstrumentById(instrumentId);

        InstrumentDTO instrumentDTO = mapToDTO(instrumentEntity);

        return ResponseEntity.ok(instrumentDTO);
    }

    public void deleteInstrument(Long instrumentId) {
       instrumentRepository.deleteById(instrumentId);
    }
}
