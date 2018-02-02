package com.obf.movie.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public final class ResponseUtil {
    private ResponseUtil() {
    }

    public static <T> ResponseEntity<T> wrapOrNotFound(Optional<T> maybeResponse) {
        return wrapOrNotFound(maybeResponse, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResponseEntity<T> wrapOrNotFound(Optional<T> maybeResponse, HttpHeaders header) {
        return (ResponseEntity) maybeResponse.map((response) -> ResponseEntity.ok().headers(header).body(response)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
