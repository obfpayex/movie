package com.obf.movie.service;

import com.obf.movie.domain.Country;
import com.obf.movie.repository.CountryRepository;
import com.obf.movie.util.PartialUpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public Country getOneCountry(Long oid) {

        Country cat = countryRepository.findOneByOid(oid);
        if (cat == null)
            log.info("Could not find Country by oid: {}", oid);

        return cat;
    }

    @Transactional
    public Country saveNewCountry(Country country) {
        log.info("Saving Country");
        country.setModified(Date.from(Instant.now()));
        country.setCreated(Date.from(Instant.now()));
        countryRepository.save(country);
        return country;
    }

    @Transactional
    public Country updateCountry(Country country) {

        Country country1 = countryRepository.findOneByOid(country.getOid());
        if (country1 == null) {
            log.info("Could not find Country by oid: {}", country.getOid());
            return null;
        }

        log.info("Updating Country with oid: {}", country1.getOid());
        country.setModified(Date.from(Instant.now()));

        return countryRepository.save(country);
    }


    @Transactional
    public Country partialUpdate(Country transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        Country Country = countryRepository.findOneByOid(transaction.getOid());
        if (Country == null) {
            log.info("Could not find Transaction by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Partially updating Transaction with oid: {}", Country.getOid());

        PartialUpdateUtil.copyNonNullProperties(transaction, Country);
        Country.setModified(Date.from(Instant.now()));
        return countryRepository.save(Country);
    }

    @Transactional(readOnly = true)
    public List<Country> getAllCountrys() {

        List<Country> countries = countryRepository.findAll();
        if (countries == null)
            log.info("Could not find any Countrys");

        return countries;
    }


    List<Country> getCountriesFromDB(List<Country> countries){

        List<Country> countryList = new ArrayList<>();

        for (Country country : countries){
            Country item = countryRepository.findOneByOid(country.getOid());
            if (item!= null){
                countryList.add(item);
            }
        }

        return countryList.size() > 0 ? countryList : null;

    }
}
