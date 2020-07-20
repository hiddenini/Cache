package com.xz;

import com.alibaba.fastjson.JSON;
import com.xz.vo.AccessToken;
import com.xz.vo.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CacheTest {

    private static final String CLIENT_CACHE = "CLIENT_CACHE";
    private static final String ACCESS_TOKEN_CACHE = "ACCESS_TOKEN_CACHE";

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void put() {
        //cacheManager.getCache(CACHE_ACCESS_TOKEN_CLIENT).put(token.getAccessToken(), client);

        for (int i = 0; i < 1; i++) {
            Client client = new Client();
            client.setAppKey("aaa" + i);
            client.setAppSecret("bbb" + i);
            cacheManager.getCache(CLIENT_CACHE).put("client" + i, client);
        }
    }

    @Test
    public void get() {
        Client client1 = cacheManager.getCache(CLIENT_CACHE).get("client0", Client::new);
        Client client2 = cacheManager.getCache(CLIENT_CACHE).get("client2", Client::new);

        log.info("client1:{}", JSON.toJSONString(client1));
        log.info("client2:{}", JSON.toJSONString(client2));

        Client client3 = cacheManager.getCache(CLIENT_CACHE).get("tgy", Client::new);
        log.info("client3:{}", JSON.toJSONString(client3));

    }

    @Test
    public void putToken() {
        cacheManager.getCache(ACCESS_TOKEN_CACHE).put("token", AccessToken.builder().appKey("zzz").appSecret("yyy").build());
    }

    @Test
    public void getToken() {
        AccessToken token = cacheManager.getCache(ACCESS_TOKEN_CACHE).get("token", AccessToken::new);
        log.info("token:{}", JSON.toJSONString(token));
    }

}
