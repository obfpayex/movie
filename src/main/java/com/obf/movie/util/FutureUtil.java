package com.obf.movie.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FutureUtil {

    // Waits for all futures to complete and returns a list of results.
    // If a future completes exceptionally then the resulting future will too.
    public static <T> CompletableFuture<List<T>> all(List<CompletableFuture<T>> futures) {
        CompletableFuture[] cfs = futures.toArray(new CompletableFuture[futures.size()]);

        return CompletableFuture.allOf(cfs)
            .thenApply((T) -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
            );
    }
}
