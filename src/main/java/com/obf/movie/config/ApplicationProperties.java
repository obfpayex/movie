package com.obf.movie.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private Integer apiRetryCount = 1;
    private Boolean apiRequestLog = false;

    private String foo;
    private String bar;
    private final Async async = new Async();

    private FooBar fooBar;

    private final Metrics metrics = new Metrics();

    public Metrics getMetrics() {
        return metrics;
    }

    public static class Metrics {

        private final Jmx jmx = new Jmx();
        private final Logs logs = new Logs();

        public Logs getLogs() {
            return logs;
        }

        public static class Logs {
            private boolean enabled = false;
            private int reportFrequency = 60;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public int getReportFrequency() {
                return reportFrequency;
            }

            public void setReportFrequency(int reportFrequency) {
                this.reportFrequency = reportFrequency;
            }
        }

        public Jmx getJmx() {
            return jmx;
        }

        public static class Jmx {
            private boolean enabled = false;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

    }

    public Boolean getApiRequestLog() {
        return apiRequestLog;
    }

    public void setApiRequestLog(Boolean apiRequestLog) {
        this.apiRequestLog = apiRequestLog;
    }

    public Integer getApiRetryCount() {
        return apiRetryCount;
    }

    public void setApiRetryCount(Integer apiRetryCount) {
        this.apiRetryCount = apiRetryCount;
    }

    public static class FooBar {
        private String foo;
        private String bar;

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getBar() {
            return bar;
        }

        public void setBar(String bar) {
            this.bar = bar;
        }
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public FooBar getFooBar() {
        return fooBar;
    }

    public void setFooBar(FooBar fooBar) {
        this.fooBar = fooBar;
    }

    public Async getAsync() {
        return async;
    }

    public static class Async {
        private int corePoolSize = 2;
        private int maxPoolSize = 50;
        private int queueCapacity = 10000;

        public Async() {
        }

        public int getCorePoolSize() {
            return this.corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return this.maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return this.queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }
}
