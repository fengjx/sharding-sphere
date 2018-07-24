package io.shardingsphere.core.api.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

/**
 * @author fengjianxin
 * @version 2018/7/19.
 */
@Getter
@Setter
public class NoShardingConfiguration {

    private String defaultKeyGeneratorColumnName;

    private Map<String, String> keyGeneratorColumnNames = Maps.newLinkedHashMap();

    private Set<String> excludeTables = Sets.newHashSet();

}
