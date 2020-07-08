package com.vacation.manager.service;



import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.exception.messages.WorkersMessages;
import com.vacation.manager.model.Role;
import com.vacation.manager.model.Worker;
import com.vacation.manager.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final WorkerRepository workerRepository;

    @Autowired
    public UserDetailsServiceImpl( WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String enterprise = username.substring(username.indexOf("/") + 1);
        String mail = username.substring(0, username.indexOf("/"));

        Worker worker = workerRepository.findByEmailAndEnterprise(mail, enterprise)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.NOT_FOUND).build());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : workerRepository.getUserRoles(worker.getId())){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(worker.getEmail(), worker.getPassword(), grantedAuthorities);
    }

}
