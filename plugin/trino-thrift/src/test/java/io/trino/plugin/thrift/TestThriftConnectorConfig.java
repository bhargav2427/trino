/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.thrift;

import com.google.common.collect.ImmutableMap;
import io.airlift.units.DataSize;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;
import static io.airlift.units.DataSize.Unit.MEGABYTE;

public class TestThriftConnectorConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(ThriftConnectorConfig.class)
                .setMaxResponseSize(DataSize.of(16, MEGABYTE))
                .setMetadataRefreshThreads(1)
                .setLookupRequestsConcurrency(1));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = ImmutableMap.<String, String>builder()
                .put("trino-thrift.max-response-size", "2MB")
                .put("trino-thrift.metadata-refresh-threads", "10")
                .put("trino-thrift.lookup-requests-concurrency", "8")
                .buildOrThrow();

        ThriftConnectorConfig expected = new ThriftConnectorConfig()
                .setMaxResponseSize(DataSize.of(2, MEGABYTE))
                .setMetadataRefreshThreads(10)
                .setLookupRequestsConcurrency(8);

        assertFullMapping(properties, expected);
    }
}
