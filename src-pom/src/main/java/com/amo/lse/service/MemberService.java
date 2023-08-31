package com.amo.lse.service;

import com.amo.lse.dto.MemberDTO;
import com.amo.lse.model.MemberEntity;
import com.amo.lse.model.VenueEntity;
import com.amo.lse.repository.MemberRepository;
import com.amo.lse.repository.VenueRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.ResourceAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    MemberRepository memberRepository;
    VenueRepository venueRepository;
    VenueService venueService;


    public MemberService(MemberRepository memberRepository, VenueRepository venueRepository) {
        this.memberRepository = memberRepository;
        this.venueRepository = venueRepository;
    }

    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO, @PathVariable Long id) {
        MemberEntity updateMemberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Member doesn't exist with id: " + id));

        updateMemberEntity.setLEI(memberDTO.getLEI());
        updateMemberEntity.setAddress(memberDTO.getAddress());
        updateMemberEntity.setVenue(getVenue(memberDTO.getVenueId()));
        updateMemberEntity.setDescription(memberDTO.getDescription());
        updateMemberEntity.setLegalName(memberDTO.getLegalName());

        memberRepository.save(updateMemberEntity);

        MemberDTO savedDTO = mapToDto(updateMemberEntity);

        return ResponseEntity.ok(savedDTO);

    }


    public ResponseEntity<MemberDTO> createMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setLEI(memberDTO.getLEI());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setVenue(getVenue(memberDTO.getVenueId()));
        memberEntity.setDescription(memberDTO.getDescription());
        memberEntity.setLegalName(memberDTO.getLegalName());

        VenueEntity venueEntity = venueRepository.findById(memberDTO.getVenueId())
                .orElseThrow(() -> new EntityNotFoundException("Venue not found with id: " + memberDTO.getVenueId()));

        memberEntity.setVenue(venueEntity);

        // Adding a member to the list of the specified venue
        venueEntity.addMember(memberEntity);

        MemberEntity savedMember = memberRepository.save(memberEntity);

        MemberDTO savedMemberDTO = mapToDto(savedMember);

        return ResponseEntity.ok(savedMemberDTO);
    }

    public MemberDTO getMemberById(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id " + id));

        MemberDTO memberDTO = mapToDto(memberEntity);

        return memberDTO;
    }

    public List<MemberDTO> getAllMembers() {
        List<MemberEntity> members = memberRepository.findAll();


        List<MemberDTO> membersDTO = members.stream()
                .map(memberEntity -> mapToDto(memberEntity))
                .collect(Collectors.toList());

        return membersDTO;
    }

    public MemberDTO mapToDto(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId(memberEntity.getId());
        memberDTO.setLEI(memberEntity.getLEI());
        memberDTO.setAddress(memberEntity.getAddress());
        memberDTO.setDescription(memberEntity.getDescription());
        memberDTO.setLegalName(memberEntity.getLegalName());

        if (memberEntity.getVenue() != null) {
            memberDTO.setVenueId(memberEntity.getVenue().getId());
        }

        return memberDTO;
    }

    public MemberEntity mapToEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setId(memberDTO.getId());
        memberEntity.setDescription(memberDTO.getDescription());
        memberEntity.setLEI(memberDTO.getLEI());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setLegalName(memberDTO.getLegalName());
        memberEntity.setVenue(venueService.getVenueById(memberDTO.getVenueId()));


        return memberEntity;
    }


    private VenueEntity getVenue(Long venueId) {
        Optional<VenueEntity> venue = venueRepository.findById(venueId);
        if (venue.isPresent()) {
            return venue.get();
        }

        return null;
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
