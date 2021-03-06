package com.criteo.hadoop.garmadon.agent.headers;

import com.criteo.hadoop.garmadon.schema.events.Header;

public final class StandaloneHeader {
    private Header.SerializedHeader header;

    private StandaloneHeader() {
        this.header = createCachedHeader();
    }

    private Header.SerializedHeader createCachedHeader() {
        //build the header for the whole application once
        return Header.newBuilder()
                .withId(Utils.getStandaloneId())
                .addTag(Header.Tag.STANDALONE.name())
                .addTags(System.getProperty("garmadon.tags"))
                .withHostname(Utils.getHostname())
                .withUser(Utils.getUser())
                .withPid(Utils.getPid())
                .withMainClass(Utils.getArrayJavaCommandLine()[0])
                .buildSerializedHeader();
    }

    private static class SingletonHolder {
        private final static StandaloneHeader INSTANCE = new StandaloneHeader();
    }

    public static StandaloneHeader getInstance() {
        return StandaloneHeader.SingletonHolder.INSTANCE;
    }

    public Header.SerializedHeader getHeader() {
        return header;
    }

}
