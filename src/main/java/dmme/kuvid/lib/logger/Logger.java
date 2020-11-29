package dmme.kuvid.lib.logger;

import dmme.kuvid.constants.LogLevel;

/**
 * Base logger class.
 *
 * @author Hasan Can
 */
public abstract class Logger {
    private LogLevel level;

    public Logger() {
        this(LogLevel.DEBUG);
    }

    public Logger(LogLevel level) {
        this.level = level;
    }

    /**
     * Writes log messages.
     *
     * @param level   Log level
     * @param msg Log message
     *
     * @author Hasan Can
     */
    protected abstract void write(String level, String msg);

    /**
     * Debug log message.
     *
     * @param msg Log message
     *
     * @author Hasan Can
     */
    public void d(String msg) {
        if (LogLevel.DEBUG.compareTo(level) >= 0) {
            write("DEBUG", msg);
        }
    }

    /**
     * Info log message.
     *
     * @param msg Log message
     *
     * @author Hasan Can
     */
    public void i(String msg) {
        if (LogLevel.INFO.compareTo(level) >= 0) {
            write("INFO", msg);
        }
    }

    /**
     * Warning log message.
     *
     * @param msg Log message
     *
     * @author Hasan Can
     */
    public void w(String msg) {
        if (LogLevel.WARNING.compareTo(level) >= 0) {
            write("WARNING", msg);
        }
    }

    /**
     * Error log message.
     *
     * @param msg Log message
     *
     * @author Hasan Can
     */
    public void e(String msg) {
        if (LogLevel.ERROR.compareTo(level) >= 0) {
            write("ERROR", msg);
        }
    }

    public LogLevel getLoggingLevel() {
        return level;
    }

    public void setLoggingLevel(LogLevel level) {
        this.level = level;
    }
}