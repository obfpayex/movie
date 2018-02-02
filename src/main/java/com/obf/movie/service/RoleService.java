package com.obf.movie.service;


import com.obf.movie.domain.Role;
import com.obf.movie.repository.RoleRepository;
import com.obf.movie.util.PartialUpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public Role getOneRole(Long oid) {

        Role rol = roleRepository.findOneByOid(oid);
        if (rol == null)
            log.info("Could not find Role by oid: {}", oid);

        return rol;
    }

    @Transactional
    public Role saveNewRole(Role role) {
        log.info("Saving Role");
        role.setModified(Date.from(Instant.now()));
        role.setCreated(Date.from(Instant.now()));
        roleRepository.save(role);
        return role;
    }

    @Transactional
    public Role updateRole(Role role) {

        Role rol = roleRepository.findOneByOid(role.getOid());
        if (rol == null) {
            log.info("Could not find Role by oid: {}", role.getOid());
            return null;
        }

        log.info("Updating Role with oid: {}", rol.getOid());
        role.setModified(Date.from(Instant.now()));
        return roleRepository.save(role);
    }

    @Transactional
    public Role partialUpdate(Role transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        Role rol = roleRepository.findOneByOid(transaction.getOid());
        if (rol == null) {
            log.info("Could not find role by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Partially updating role with oid: {}", rol.getOid());

        PartialUpdateUtil.copyNonNullProperties(transaction, rol);
        rol.setModified(Date.from(Instant.now()));
        return roleRepository.save(rol);
    }
}
