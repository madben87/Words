package com.ben.words.util;

import java.util.Objects;

public class KeyGenerator {

    public static long generateKey(String str1, String str2) {
        long l = Objects.hash(str1, str2);
        return l;
    }
}
