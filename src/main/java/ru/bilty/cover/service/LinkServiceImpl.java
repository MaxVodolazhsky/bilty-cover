package ru.bilty.cover.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import ru.bilty.cover.exception.BiltyCoverNotFoundException;
import ru.bilty.cover.model.BoxingLink;
import ru.bilty.cover.model.UnboxingLink;
import ru.bilty.cover.utils.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private static final Pattern PATTERN_FOR_BOXING = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");
    private static final Pattern PATTERN_FOR_UNBOXING = Pattern.compile("^http://bilty-cover/\\d+$");

    private final List<UnboxingLink> links;

    @Override
    public BoxingLink boxingLink(final UnboxingLink unboxingLink) {
        if (isValid(unboxingLink)) {
            return links.contains(unboxingLink) ? new BoxingLink(generateLink(links.indexOf(unboxingLink))) : addAndGenerate(unboxingLink);
        }
        throw new IllegalStateException(MessageFormat.format("Illegal URL for boxing {0}. " +
                "You must use a URL matching the given regex {1}", unboxingLink.getUnboxingLink(), PATTERN_FOR_BOXING.toString()));
    }

    @Override
    public UnboxingLink unboxingLink(final BoxingLink boxingLink) {
        UnboxingLink unboxingLink = CollectionUtils.get(links, getCounter(boxingLink));
        if (unboxingLink != null) {
            return unboxingLink;
        }
        throw new BiltyCoverNotFoundException(boxingLink);
    }

    private boolean isValid(UnboxingLink unboxingLink) {
        return PATTERN_FOR_BOXING.matcher(unboxingLink.getUnboxingLink()).matches();
    }

    private int getCounter(final BoxingLink boxingLink) {
        final String link = boxingLink.getBoxingLink();
        if (PATTERN_FOR_UNBOXING.matcher(boxingLink.getBoxingLink()).matches()) {
            return Integer.parseInt(link.replaceAll("http://bilty-cover/", ""));
        }
        throw new IllegalStateException(MessageFormat.format("Illegal URL for unboxing {0}. " +
                "You must use a URL matching the given regex {1}", link, PATTERN_FOR_UNBOXING.toString()));
    }

    private BoxingLink addAndGenerate(final UnboxingLink unboxingLink) {
        links.add(unboxingLink);
        return new BoxingLink(generateLink(links.size() - 1));
    }

    private String generateLink(final int identifier) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("bilty-cover")
                .path(String.valueOf(identifier))
                .build()
                .toUri()
                .toString();

    }
}
