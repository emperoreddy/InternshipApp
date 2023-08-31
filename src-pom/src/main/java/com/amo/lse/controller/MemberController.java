package com.amo.lse.controller;

import com.amo.lse.dto.MemberDTO;
import com.amo.lse.repository.MemberRepository;
import com.amo.lse.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {


    MemberRepository memberRepository;
    MemberService memberService;

    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> membersDTO = memberService.getAllMembers();

        return ResponseEntity.ok(membersDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) {
        MemberDTO memberDTO = memberService.getMemberById(id);

        return ResponseEntity.ok(memberDTO);
    }


    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        return memberService.createMember(memberDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO, @PathVariable Long id) {
        return memberService.updateMember(memberDTO, id);
    }

    @DeleteMapping("{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}

