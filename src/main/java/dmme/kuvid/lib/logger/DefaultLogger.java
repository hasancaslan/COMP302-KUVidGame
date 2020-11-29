package dmme.kuvid.lib.logger;

import dmme.kuvid.constants.LogLevel;

import java.time.Instant;

/**
 * Logs time, thread, class and log level respectively.
 *
 * @author Hasan Can
 */
public class DefaultLogger extends Logger {
    public DefaultLogger(LogLevel level) {
        super(level);
    }

    @Override
    protected void write(String level, String message) {
        System.out.println(String.format("[%s] [%s] [%s] %s",
                Instant.now(),
                Thread.currentThread().getName(),
                level,
                message));
    }
}