package com.consol.citrus;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.actions.ReceiveMessageAction.Builder.receive;
import static com.consol.citrus.actions.SendMessageAction.Builder.send;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.TestNGCitrusSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.lanit.utils.InterceptorHandler;

/**
 * TODO: Description
 *
 * @author Unknown
 * @since 2021-03-19
 */
public class SwaggerPetstore_loginUser_IT extends TestNGCitrusSupport {
    @CitrusTest
    @Test
    @Parameters("runner")
    public void swaggerPetstore_loginUser_IT(@CitrusResource @Optional TestCaseRunner runner) {
        runner.run(echo("TODO: Code the test SwaggerPetstore_loginUser_IT"));

        runner.run(http().client("httpClient")
            .send()
            .get(InterceptorHandler.getPath("/v2/user/login"))
            .contentType("application/json")
            .queryParam("password", "citrus:randomString(10)")
            .queryParam("username", "citrus:randomString(10)")
        );

        runner.run(http().client("httpClient")
            .receive()
            .response(HttpStatus.OK)
            .header("X-Expires-After", "@matchesDatePattern('yyyy-MM-dd'T'hh:mm:ss')@")
            .header("X-Rate-Limit", "@isNumber()@")
            .payload("@notEmpty()@")
        );

    }
}
