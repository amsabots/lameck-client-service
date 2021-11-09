package com.amsabots.jenzi.client_service.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static String randomHexValue() {
        return Integer.toHexString(ThreadLocalRandom.current().nextInt(150, 255 + 1));
    }

    public static String createRandomColor() {
        return String.format("#%s%s%s", randomHexValue(), randomHexValue(), randomHexValue());
    }
}
