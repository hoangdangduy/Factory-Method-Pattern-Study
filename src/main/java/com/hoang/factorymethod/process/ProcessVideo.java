package com.hoang.factorymethod.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Configuration
@PropertySource(value = "classpath:application.properties")
@ConfigurationProperties
@ComponentScan({
        "com.hoang.factorymethod.*"
})
public class ProcessVideo implements CommandLineRunner{

    private static final Logger logger = LoggerFactory.getLogger(ProcessVideo.class);
    public static String url = "https://drive.google.com/get_video_info?docid=0BxrpnFGicM4remhWcUxnbDVGdlU";

    @Value("${PROXY.IFI}")
    public static String PROXY_IFI;

    @Override
    public void run(String... strings) throws Exception {

        logger.error("ok123: " + PROXY_IFI);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
//        logger.error(result.getBody());
        String bodyResponse = result.getBody();
        bodyResponse = URLDecoder.decode(bodyResponse, "UTF-8");
        bodyResponse = URLDecoder.decode(bodyResponse, "UTF-8");

        int first_FMT_STREAM_ITAG = bodyResponse.indexOf("fmt_stream_map");
        int last_FMT_STREAM_ITAG = bodyResponse.indexOf("url_encoded_fmt_stream_map");

        String urls = bodyResponse.substring(first_FMT_STREAM_ITAG + "fmt_stream_map".length() + 1, last_FMT_STREAM_ITAG - 1);

        List<String> lstUrls = new ArrayList<>();

        while (urls.contains("key=ck2")) {
            urls = urls.trim();
            if (urls.indexOf(",") == 0) {
                urls = urls.substring(1);
            }
            lstUrls.add(urls.substring(0, urls.indexOf("key=ck2") + "key=ck2".length()));
            urls = urls.substring(urls.indexOf("key=ck2") + "key=ck2".length());
        }

        for (String str: lstUrls) {
            logger.error(str);
        }
    }
}
