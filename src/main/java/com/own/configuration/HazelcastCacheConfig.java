package com.own.configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.*;
import com.hazelcast.config.MaxSizeConfig.MaxSizePolicy;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.own.constants.CacheNames;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("hazelcast-cache")
public class HazelcastCacheConfig {

    @Bean
    public Config hazelCastConfig() {

        Config config = new Config();
        config.setInstanceName("hazelcast-cache");

        MapConfig allUsersCache = new MapConfig();
        allUsersCache.setTimeToLiveSeconds(20);
        allUsersCache.setEvictionPolicy(EvictionPolicy.LFU);
        allUsersCache.setMaxSizeConfig(new MaxSizeConfig(2, MaxSizePolicy.FREE_HEAP_PERCENTAGE));
        config.getMapConfigs().put(CacheNames.ALLUSER.Name, allUsersCache);

        MapConfig groupsCache = new MapConfig();
        groupsCache.setTimeToLiveSeconds(20);
        groupsCache.setEvictionPolicy(EvictionPolicy.LFU);
        config.getMapConfigs().put(CacheNames.GROUPS.Name, groupsCache);
        MapConfig usernameCache = new MapConfig();
        usernameCache.setTimeToLiveSeconds(20);
        usernameCache.setEvictionPolicy(EvictionPolicy.LFU);
        usernameCache.setMaxSizeConfig(new MaxSizeConfig(2,
                MaxSizePolicy.USED_HEAP_SIZE));
        usernameCache.setMaxIdleSeconds(15);
        config.getMapConfigs().put(CacheNames.USERNAME.Name, usernameCache);

        return config;
    }

    @Bean
    CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(config);
        return hzClient;
    }
}