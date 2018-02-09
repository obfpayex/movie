package com.obf.movie.service;

import com.obf.movie.domain.Language;
import com.obf.movie.domain.Movie;
import com.obf.movie.repository.LanguageRepository;
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
public class LanguageService {

    private final LanguageRepository languageRepository;

    private static final Logger log = LoggerFactory.getLogger(LanguageService.class);

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Transactional(readOnly = true)
    public Language getOneLanguage(Long oid) {

        Language cat = languageRepository.findOneByOid(oid);
        if (cat == null)
            log.info("Could not find Category by oid: {}", oid);

        return cat;
    }

    @Transactional
    public Language saveNewLanguage(Language language) {
        log.info("Saving Category");
        language.setModified(Date.from(Instant.now()));
        language.setCreated(Date.from(Instant.now()));
        languageRepository.save(language);
        return language;
    }

    @Transactional
    public Language updateLanguage(Language language) {

        Language lang = languageRepository.findOneByOid(language.getOid());
        if (lang == null) {
            log.info("Could not find language by oid: {}", language.getOid());
            return null;
        }

        log.info("Updating language with oid: {}", lang.getOid());
        language.setModified(Date.from(Instant.now()));

        return languageRepository.save(language);
    }


    @Transactional
    public Language partialUpdate(Language transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        Language language = languageRepository.findOneByOid(transaction.getOid());
        if (language == null) {
            log.info("Could not find Transaction by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Partially updating Transaction with oid: {}", language.getOid());

        PartialUpdateUtil.copyNonNullProperties(transaction, language);
        language.setModified(Date.from(Instant.now()));
        return languageRepository.save(language);
    }

    @Transactional(readOnly = true)
    public List<Language> getAllLanguages() {

        List<Language> languages = languageRepository.findAll();
        if (languages == null)
            log.info("Could not find any languages");

        return languages;
    }


    List<Language> getLanguagesFromDB(List<Language> languages){

        List<Language> langs = new ArrayList<>();

        for (Language cat : languages){
            Language item = languageRepository.findOneByOid(cat.getOid());
            if (item!= null){
                langs.add(item);
            }
        }

        return langs.size() > 0 ? langs : null;

    }
}
