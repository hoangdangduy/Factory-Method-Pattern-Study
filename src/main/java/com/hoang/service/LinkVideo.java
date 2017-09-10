package com.hoang.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LinkVideo {

    private static final Logger logger = LoggerFactory.getLogger(LinkVideo.class);

    public final static String url = "https://mail.google.com/e/get_video_info?docid=";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getListLink", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getListLink(@RequestParam(value = "docId") String docId) throws UnsupportedEncodingException {
        Map<String, String> mapUrlVideo = new HashMap<>();
        ResponseEntity<String> result = restTemplate.getForEntity(url + docId, String.class);
        try {
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
                String[] strSplit = str.split("\\|");
                mapUrlVideo.put(strSplit[0], strSplit[1]);
            }

            return new Gson().toJson(mapUrlVideo);
        } catch (Exception e) {
            logger.error(e.getMessage() + "\n" + result.getBody());

        }
        return null;
    }
}
