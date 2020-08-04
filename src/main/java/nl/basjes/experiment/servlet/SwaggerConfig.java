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

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {

        final ArrayList<Response> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseBuilder()
            .code("200")
            .description("Successfully parsed the provided input")
            .build());
        responseMessages.add(new ResponseBuilder()
            .code("503")
            .description("Internal error, or currently still busy starting up.")
            .build());

        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("experiment")
            .select()
            .apis(withMethodAnnotation(ApiOperation.class))
            .build()
            .globalResponses(GET, responseMessages)
            .globalResponses(POST, responseMessages)
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            "Experimental servlet",
            null,
            "0.1",
            null,
            new Contact("Niels Basjes", "https://niels.basjes.nl", null),
            null,
            null,
            Collections.<VendorExtension>emptyList());
    }
}
