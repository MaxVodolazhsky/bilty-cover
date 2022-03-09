package ru.bilty.cover.service;

import ru.bilty.cover.model.BoxingLink;
import ru.bilty.cover.model.UnboxingLink;

public interface LinkService {

    BoxingLink boxingLink(final UnboxingLink unboxingLink);

    UnboxingLink unboxingLink(final BoxingLink boxingLink);
}
