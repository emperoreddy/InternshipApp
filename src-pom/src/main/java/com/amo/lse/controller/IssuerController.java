package com.amo.lse.controller;

import com.amo.lse.dto.InstrumentDTO;
import com.amo.lse.dto.IssuerDTO;
import com.amo.lse.service.IssuerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issuers")
public class IssuerController {

    IssuerService issuerService;

    public IssuerController(IssuerService issuerService) {
        this.issuerService = issuerService;
    }

    @PostMapping
    public ResponseEntity<IssuerDTO> createIssuer(@RequestBody IssuerDTO issuerDTO) {
        return issuerService.createIssuer(issuerDTO);
    }

    @PostMapping("{issuerId}/instruments/{instrumentId}")
    public ResponseEntity<IssuerDTO> addInstrument(@PathVariable(name = "issuerId") Long issuerId,
                                                   @PathVariable(name = "instrumentId") Long instrumentId) {
        return issuerService.addInstrument(issuerId, instrumentId);
    }

    @PutMapping("{issuerId}")
    public ResponseEntity<IssuerDTO> updateIssuer(@PathVariable Long issuerId, @RequestBody IssuerDTO issuerDTO) {
        return issuerService.updateIssuer(issuerId, issuerDTO);
    }

    @GetMapping
    public ResponseEntity<List<IssuerDTO>> getAllIssuers() {
        return ResponseEntity.ok(issuerService.getAllIssuers());
    }

    @GetMapping("{issuerId}")
    public ResponseEntity<IssuerDTO> getIssuerById(@PathVariable Long issuerId) {
        return issuerService.getIssuerById(issuerId);
    }

    @GetMapping("{issuerId}/instruments")
    public ResponseEntity<List<InstrumentDTO>> getInstrumentsOfIssuer(@PathVariable Long issuerId) {
        return issuerService.getInstrumentsOfIssuer(issuerId);
    }

    @DeleteMapping("{issuerId}")
    public void deleteIssuer(@PathVariable Long issuerId) {
        issuerService.deleteIssuer(issuerId);
    }

}
