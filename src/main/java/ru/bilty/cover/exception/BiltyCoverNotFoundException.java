package ru.bilty.cover.exception;

import ru.bilty.cover.model.BoxingLink;

import java.text.MessageFormat;

public class BiltyCoverNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5724538315727031665L;

    public BiltyCoverNotFoundException(BoxingLink boxingLink) {
        super(MessageFormat.format("URL {0} not found", boxingLink.getBoxingLink()));
    }
}
