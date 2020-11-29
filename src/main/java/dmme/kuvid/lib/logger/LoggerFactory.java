package dmme.kuvid.lib.logger;

import  dmme.kuvid.constants.Config;
import  dmme.kuvid.constants.LogLevel;

public class LoggerFactory {
    private LogLevel logLevel = Config.DEFAULT_LOG_LEVEL;

    public LoggerFactory() {

    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Creates a default logger
     *
     * @return Creates a default logger
     *
     * @author Hasan Can
     */

    public Logger createLogger() {
        return new DefaultLogger(logLevel);
    }

    /**
     * Creates a logger with given class' name
     *
     * @param cls Class to get class name from
     * @return Class aware logger
     *
     * @author Hasan Can
     */

    public Logger createLogger(Class<?> cls) {
        return new ClassNameLogger(cls, logLevel);
    }
}