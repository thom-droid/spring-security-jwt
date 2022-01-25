package org.test.jwttest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.jwttest.domain.Member;
import org.test.jwttest.domain.Role;
import org.test.jwttest.repo.MemberRepo;
import org.test.jwttest.repo.RoleRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepo memberRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepo.findByEmail(username);
        if(member == null){
            log.info("member not found");
            throw new UsernameNotFoundException("no member found by given email");
        } else {
            log.info("member found : {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        member.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(member.getEmail(), member.getPassword(), authorities);
    }

    public List<Member> getMembers(){
        log.info("find all members");
        return memberRepo.findAll();
    }

    public Member getMemberByEmail(String email){
        log.info("find by email : {}", email);
        return memberRepo.findByEmail(email);
    }

    public Member saveMember(Member member){
        log.info("saving member with data:  {} {} {} {}", member.getEmail(), member.getPassword(), member.getMemberName(), member.getRole() );
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepo.save(member);
    }

    public Role saveRole(Role role){
        log.info("saving role : {}", role.getName());
        return roleRepo.save(role);
    }

    public void addRoleToMember(String email, String roleName){
        Member member = memberRepo.findByEmail(email);
        log.info("member found: {}", member.getMemberName());

        Role role = roleRepo.findByName(roleName);
        log.info("role found: {}", role.getName());

        member.getRole().add(role);
    }


}
