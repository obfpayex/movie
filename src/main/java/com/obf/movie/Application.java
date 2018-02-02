package com.obf.movie;

import com.obf.movie.config.ApplicationProperties;
import com.obf.movie.config.DefaultProfileUtil;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {
        setUtcTimezone();
        SpringApplication app = new SpringApplication(Application.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = env.getProperty("server.ssl.key-store") != null ? "https" : "http";

        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' v.{} is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "External: \t{}://{}:{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            Application.class.getPackage().getImplementationVersion(),
            protocol,
            env.getProperty("server.port"),
            protocol,
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            DefaultProfileUtil.getActiveProfiles(env));
    }

    private static void setUtcTimezone() {
        //Put system timezone in logback, so the logger logs timestamps with correct timezone
        MDC.put("TimeZone", TimeZone.getDefault().getID());
        //Set UTC as default timezone for this application
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));

        log.info("Setting application TimeZone to: {}", TimeZone.getDefault().getID());
    }
}
