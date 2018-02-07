package com.obf.movie.service;


import com.obf.movie.domain.RoleType;
import com.obf.movie.repository.RoleTypeRepository;
import com.obf.movie.util.PartialUpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;

@Service
public class RoleTypeService {

    private final RoleTypeRepository roleRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleTypeService.class);

    public RoleTypeService(RoleTypeRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public RoleType getOneRoleType(Long oid) {

        RoleType rol = roleRepository.findOneByOid(oid);
        if (rol == null)
            log.info("Could not find RoleType by oid: {}", oid);

        return rol;
    }

    @Transactional
    public RoleType saveNewRoleType(RoleType roleType) {
        log.info("Saving RoleType");
        roleType.setModified(Date.from(Instant.now()));
        roleType.setCreated(Date.from(Instant.now()));
        roleRepository.save(roleType);
        return roleType;
    }

    @Transactional
    public RoleType updateRoleType(RoleType roleType) {

        RoleType rol = roleRepository.findOneByOid(roleType.getOid());
        if (rol == null) {
            log.info("Could not find RoleType by oid: {}", roleType.getOid());
            return null;
        }

        log.info("Updating RoleType with oid: {}", rol.getOid());
        roleType.setModified(Date.from(Instant.now()));
        return roleRepository.save(roleType);
    }

    @Transactional
    public RoleType partialUpdate(RoleType transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        RoleType rol = roleRepository.findOneByOid(transaction.getOid());
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
