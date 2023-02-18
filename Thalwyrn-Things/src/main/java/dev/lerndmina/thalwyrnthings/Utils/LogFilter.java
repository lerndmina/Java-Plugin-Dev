package dev.lerndmina.thalwyrnthings.Utils;

import dev.lerndmina.thalwyrnthings.Main;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LogFilter extends AbstractFilter {
    protected final Main main; // Define main class

    public LogFilter(Main main) {
        this.main = main;
    }

    public void registerFilter() {
        Logger logger = (Logger) LogManager.getRootLogger();
        logger.addFilter(this);

    }


    @Override
    public Result filter(LogEvent event) {
        return event == null ? Result.NEUTRAL : isLoggable(event.getMessage().getFormattedMessage());
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return isLoggable(msg.getFormattedMessage());
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return isLoggable(msg);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        return msg == null ? Result.NEUTRAL : isLoggable(msg.toString());
    }

    private Result isLoggable(String msg) {
        if (msg != null) {
            if (stringContainsItemFromList(msg, main.getConfig().getStringList("messagesToBlock"))){
                return Result.DENY;
            }
        }
        return Result.NEUTRAL;
    }

    public static boolean stringContainsItemFromList(String inputStr, @NotNull List<String> items) {
        return items.stream().anyMatch(inputStr::contains);
    }
}