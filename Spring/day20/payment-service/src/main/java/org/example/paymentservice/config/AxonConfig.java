package org.example.paymentservice.config;

import org.axonframework.common.caching.Cache;
import com.thoughtworks.xstream.XStream;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.example.common.config.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {"org.example.**"});

        return xStream;
    }


    @Bean
    public SnapshotTriggerDefinition snapshotTrigger(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, Constants.SNAPSHOT_COUNT);
    }

    @Bean
    public Cache snapshotCache() {
        return new WeakReferenceCache();
    }
}
