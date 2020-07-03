package com.vacation.manager.service;



import com.vacation.manager.model.Role;
import com.vacation.manager.model.Worker;
import com.vacation.manager.repositoryP.WorkerJooqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final WorkerJooqRepository workerRepository;

    @Autowired
    public UserDetailsServiceImpl(WorkerJooqRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Worker> worker = workerRepository.findByEmail(email);
        if (worker.isEmpty()) throw new UsernameNotFoundException(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : workerRepository.getUserRoles(email)){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(worker.get().getEmail(), worker.get().getPassword(), grantedAuthorities);
    }

}
