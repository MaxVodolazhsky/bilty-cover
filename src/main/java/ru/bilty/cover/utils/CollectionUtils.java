package ru.bilty.cover.utils;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CollectionUtils {

    public <T> T get(List<T> list, int index) {
        return list.size() > index ? list.get(index) : null;
    }
}
