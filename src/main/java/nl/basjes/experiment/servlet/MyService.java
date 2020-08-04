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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@SuppressWarnings("deprecation") // The description field may be deprecated but in SpringFox it is still used.
@Api(tags = "Experiment", description = "Experimental service")
@SpringBootApplication
@RestController
public class MyService {

    private static final Logger LOG = LoggerFactory.getLogger(MyService.class);

    private static final String  API_BASE_PATH  = "/experiment/v1";

    private static final String EXAMPLE_JSON = "[ { \"Key\": \"Value\"} ]";

    private static final String EXAMPLE_XML = "<Key>Value</Key>";

    private static final String EXAMPLE_TEXT = "Key=Value";

    // =============== Text OUTPUT ===============

    @ApiOperation("Do stuff")
    @ApiResponses({
        @ApiResponse(
            code = 200, // HttpStatus.OK
            message = "It worked Text",
            examples = @Example({
                @ExampleProperty(mediaType = TEXT_PLAIN_VALUE,          value = EXAMPLE_TEXT),
                @ExampleProperty(mediaType = APPLICATION_JSON_VALUE,    value = EXAMPLE_JSON),
                @ExampleProperty(mediaType = APPLICATION_XML_VALUE,     value = EXAMPLE_XML)
            })
        )
    })
    @GetMapping(
            value = { API_BASE_PATH + "/dostuff" }, //, API_BASE_PATH + "/dostuff/text" },
            produces = TEXT_PLAIN_VALUE
    )
    public String getTextGET() {
        return "Text: GET";
    }

    // =============== JSON OUTPUT ===============

    @ApiOperation("Do stuff")
    @ApiResponses({
            @ApiResponse(
                    code = 200, // HttpStatus.OK
                    message = "It worked Json",
                    examples = @Example({
                            @ExampleProperty(mediaType = TEXT_PLAIN_VALUE,          value = EXAMPLE_TEXT),
                            @ExampleProperty(mediaType = APPLICATION_JSON_VALUE,    value = EXAMPLE_JSON),
                            @ExampleProperty(mediaType = APPLICATION_XML_VALUE,     value = EXAMPLE_XML)
                    })
            )
    })
    @GetMapping(
            value = { API_BASE_PATH + "/dostuff"}, //, API_BASE_PATH + "/dostuff/json" },
            produces = APPLICATION_JSON_VALUE)
    public String getJSonGET() {
        return "JSON: GET";
    }

    // =============== XML OUTPUT ===============

    @ApiOperation("Do stuff")
    @ApiResponses({
            @ApiResponse(
                    code = 200, // HttpStatus.OK
                    message = "It worked XML",
                    examples = @Example({
                            @ExampleProperty(mediaType = TEXT_PLAIN_VALUE,          value = EXAMPLE_TEXT),
                            @ExampleProperty(mediaType = APPLICATION_JSON_VALUE,    value = EXAMPLE_JSON),
                            @ExampleProperty(mediaType = APPLICATION_XML_VALUE,     value = EXAMPLE_XML)
                    })
            )
    })
    @GetMapping(
            value = { API_BASE_PATH + "/dostuff"}, //, API_BASE_PATH + "/dostuff/xml" },
            produces = APPLICATION_XML_VALUE)
    public String getXMLGET() {
        return "XML: GET";
    }

    
    
    // =================================================================================================================
    // =============== Text OUTPUT ===============

    @ApiOperation("Do stuff")
    @ApiResponses({
            @ApiResponse(
                    code = 200, // HttpStatus.OK
                    message = "It worked Text",
                    examples = @Example({
                            @ExampleProperty(mediaType = TEXT_PLAIN_VALUE,          value = EXAMPLE_TEXT),
                            @ExampleProperty(mediaType = APPLICATION_JSON_VALUE,    value = EXAMPLE_JSON),
                            @ExampleProperty(mediaType = APPLICATION_XML_VALUE,     value = EXAMPLE_XML)
                    })
            )
    })
    @GetMapping(
            value = { API_BASE_PATH + "/docombi", API_BASE_PATH + "/docombi/text" },
            produces = TEXT_PLAIN_VALUE
    )
    public String getTextGET2() {
        return "Text: GET";
    }

    // =============== JSON OUTPUT ===============

    @ApiOperation("Do stuff")
    @ApiResponses({
            @ApiResponse(
                    code = 200, // HttpStatus.OK
                    message = "It worked Json",
                    examples = @Example({
                            @ExampleProperty(mediaType = TEXT_PLAIN_VALUE,          value = EXAMPLE_TEXT),
                            @ExampleProperty(mediaType = APPLICATION_JSON_VALUE,    value = EXAMPLE_JSON),
                            @ExampleProperty(mediaType = APPLICATION_XML_VALUE,     value = EXAMPLE_XML)
                    })
            )
    })
    @GetMapping(
            value = { API_BASE_PATH + "/docombi", API_BASE_PATH + "/docombi/json" },
            produces = APPLICATION_JSON_VALUE)
    public String getJSonGET2() {
        return "JSON: GET";
    }

    // =============== XML OUTPUT ===============

    @ApiOperation("Do stuff")
    @ApiResponses({
            @ApiResponse(
                    code = 200, // HttpStatus.OK
                    message = "It worked XML",
                    examples = @Example({
                            @ExampleProperty(mediaType = TEXT_PLAIN_VALUE,          value = EXAMPLE_TEXT),
                            @ExampleProperty(mediaType = APPLICATION_JSON_VALUE,    value = EXAMPLE_JSON),
                            @ExampleProperty(mediaType = APPLICATION_XML_VALUE,     value = EXAMPLE_XML)
                    })
            )
    })
    @GetMapping(
            value = { API_BASE_PATH + "/docombi", API_BASE_PATH + "/docombi/xml" },
            produces = APPLICATION_XML_VALUE)
    public String getXMLGET2() {
        return "XML: GET";
    }









    // ================= MAIN ==================

    public static void main(String[] args) {
        SpringApplication.run(MyService.class, args);
    }
}
