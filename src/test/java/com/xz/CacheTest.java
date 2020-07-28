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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CacheTest {

    private static final String CLIENT_CACHE = "ZJW_CLIENT_CACHE";
    private static final String ACCESS_TOKEN_CACHE = "ZJW_ACCESS_TOKEN_CACHE";

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void put() {
        //cacheManager.getCache(CACHE_ACCESS_TOKEN_CLIENT).put(token.getAccessToken(), client);

/*        for (int i = 0; i < 0; i++) {
            Random random = new Random();
            int j = random.nextInt(1000);
            Client client = new Client();
            client.setAppKey("aaa" + i);
            client.setAppSecret("bbb" + i);
            cacheManager.getCache(CLIENT_CACHE).put("client" + i, client);
        }*/

        Random random = new Random();
        int j = random.nextInt(1000);
        Client client = new Client();
        client.setAppKey("aaa" + j);
        client.setAppSecret("bbb" + j);
        cacheManager.getCache(CLIENT_CACHE).put("client" + j, client);
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
        for (int i = 0; i < 10; i++) {
            AccessToken accessToken = new AccessToken();
            accessToken.setAppKey("zzz" + i);
            accessToken.setAppSecret("yyy" + i);
            cacheManager.getCache(ACCESS_TOKEN_CACHE).put("token" + i, accessToken);
        }
    }

    @Test
    public void getToken() {
        AccessToken token = cacheManager.getCache(ACCESS_TOKEN_CACHE).get("token0", AccessToken::new);
        log.info("token:{}", JSON.toJSONString(token));
    }

    @Test
    public void ll() {
        /**
         * 时间戳转换成字符串
         */
        Long time = 1595911795951l;
        SimpleDateFormat sf = null;
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("d:" + sf.format(d));

    }


}
