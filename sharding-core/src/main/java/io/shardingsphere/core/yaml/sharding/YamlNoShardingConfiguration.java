package io.shardingsphere.core.yaml.sharding;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.shardingsphere.core.api.config.NoShardingConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

/**
 * @author fengjianxin
 * @version 2018/7/19.
 */
@Getter @Setter
public class YamlNoShardingConfiguration {

    private String defaultKeyGeneratorColumnName;

    private Map<String, String> keyGeneratorColumnNames = Maps.newLinkedHashMap();

    private Set<String> excludeTables = Sets.newHashSet();

    public NoShardingConfiguration build() {
        NoShardingConfiguration result = new NoShardingConfiguration();
        result.setDefaultKeyGeneratorColumnName(defaultKeyGeneratorColumnName);
        result.setKeyGeneratorColumnNames(keyGeneratorColumnNames);
        result.setExcludeTables(excludeTables);
        return result;
    }

}
