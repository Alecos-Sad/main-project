package by.sadovnick.datageneratekafka.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextXPath {

    private final XML xml;
    private final String node;

    @Override
    public String toString() {
        return this.xml
                .nodes(this.node)
                .get(0)
                .xpath("text()")
                .get(0);
    }
}
