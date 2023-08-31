package com.amo.lse.controller;

import com.amo.lse.dto.InstrumentDTO;
import com.amo.lse.service.InstrumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

    InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public List<InstrumentDTO> getAllInstruments() {
        return instrumentService.getAllInstruments();
    }


    @GetMapping("{id}")
    public ResponseEntity<InstrumentDTO> getInstrumentById(@PathVariable Long id) {
       return instrumentService.getInstrumentById(id);
    }

    @PostMapping
    public ResponseEntity<InstrumentDTO> createInstrument(@RequestBody InstrumentDTO instrumentDTO) {
        return instrumentService.createInstrument(instrumentDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<InstrumentDTO> updateInstrument(@RequestBody InstrumentDTO instrumentDTO, @PathVariable Long id) {
       return instrumentService.updateInstrument(instrumentDTO, id);
    }

    @DeleteMapping("{id}")
    public void deleteInstrument(@PathVariable Long id) {
       instrumentService.deleteInstrument(id);
    }


}
