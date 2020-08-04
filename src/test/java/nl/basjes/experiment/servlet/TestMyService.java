/*
 * Yet Another UserAgent Analyzer
 * Copyright (C) 2013-2020 Niels Basjes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.basjes.experiment.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.ALL;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMyService {

    private static final Logger LOG = LoggerFactory.getLogger(TestMyService.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private URI getURI(String path) {
        try {
            return new URI("http://localhost:" + port + "/experiment/v1" + path);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public void runTest(String path, MediaType accept, String expected) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(accept));

        HttpEntity<String> request = new HttpEntity<>("Niels Basjes", headers);

        URI url = getURI(path);

        ResponseEntity<String> response = this.restTemplate.exchange(url, GET, request, String.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        String body = response.getBody();

        LOG.info("GET {} ({})--> {}", url, accept ,body);

        assertNotNull(body);
        assertThat(body.contains(expected));
    }

    @Test
    public void testGetToText() {
        runTest("/dostuff",      TEXT_PLAIN, "Text");
        runTest("/docombi",      TEXT_PLAIN, "Text");
        runTest("/docombi/text", TEXT_PLAIN, "Text");
    }

    @Test
    public void testGetToJson() {
        runTest("/dostuff",      APPLICATION_JSON, "JSON");
        runTest("/docombi",      APPLICATION_JSON, "JSON");
        runTest("/docombi/json", APPLICATION_JSON, "JSON");
    }

    @Test
    public void testGetToXml() {
        runTest("/dostuff",      APPLICATION_XML, "XML");
        runTest("/docombi",      APPLICATION_XML, "XML");
        runTest("/docombi/xml",  APPLICATION_XML, "XML");
    }

    @Test
    public void testGetToAny() {
        runTest("/docombi/text", ALL, "Text");
        runTest("/docombi/json", ALL, "JSON");
        runTest("/docombi/xml",  ALL, "XML");
    }

}
