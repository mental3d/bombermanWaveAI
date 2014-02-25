package com.utils;

import com.WebSocketRunner;
import org.apache.log4j.LogManager;

/**
 * @author Leon
 * @date 25.02.14
 */
public class BomberLogger {
    // Now we use one logger the BomberLogger
    public static String LOGGER_NAME = "BomberLogger";
    private static String LOG_FILE_NAME = "logs.txt";
    private static String UNKNOWN_CLASS_NAME = "UnknownClass";

    public static void log(String message){
        log(message, UNKNOWN_CLASS_NAME);
    }

    public static void log(String message, String className){
        LogManager.getLogger(LOGGER_NAME)
                .debug("Class: " + className + "Message: " + message);
    }

    public static void log(String message, Class clazz){
        log(message, clazz.getName());
    }

    public static void log(String message, Object obj){
        log(message, obj.getClass().getName());
    }

}
