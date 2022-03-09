package ru.bilty.cover.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CollectionUtilsTest {

    private static final String EXPECTED_STRING = "test";

    @Test
    void get() {
        List<String> strings = List.of(EXPECTED_STRING);
        assertEquals(CollectionUtils.get(strings, 0), EXPECTED_STRING);
        assertNull(CollectionUtils.get(strings, 1));

    }
}