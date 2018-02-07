package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.obf.movie.domain.RoleType;
import com.obf.movie.service.RoleTypeService;
import com.obf.movie.util.ResponseUtil;
import com.payex.vas.common.vasutil.utils.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/obf-movie-server/api")
public class RoleTypeRecource {

    private static final Logger log = LoggerFactory.getLogger(MovieResource.class);

    private final RoleTypeService roleService;

    public RoleTypeRecource(RoleTypeService roleService) {
        this.roleService = roleService;
    }


    @Timed
    @GetMapping(value = "/roletype/{oId}")
    public ResponseEntity<RoleType> getOneRoleType(@PathVariable("oId") Long oId) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("get invoked: {} ", oId);

            RoleType response = roleService.getOneRoleType(oId);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", stopwatch.stop().toString());
        }
    }

    @Timed
    @PostMapping(value = "/roletype")
    public ResponseEntity<RoleType> addRoleType(@RequestBody RoleType request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("add() invoked for category with oid: {} ", request.getOid());

            RoleType response = roleService.saveNewRoleType(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "add", stopwatch.stop().toString());
        }
    }

    @Timed
    @PutMapping(value = "/roletype")
    public ResponseEntity<RoleType> updateRoleType(@RequestBody @Valid RoleType request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("update() invoked for category with oid: {} ", request.getOid());

            RoleType response = roleService.updateRoleType(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update", stopwatch.stop().toString());
        }
    }

    @Timed
    @PatchMapping(value = "/roletype")
    public ResponseEntity<RoleType> partialUpdate(@RequestBody RoleType request) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("partialUpdate() invoked for transaction with oid: {} ", request.getOid());

            RoleType response = roleService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "partialUpdate", stopwatch.stop().toString());
        }
    }
}
