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
public class SwaggerPetstore_uploadFile_IT extends TestNGCitrusSupport {
    @CitrusTest
    @Test
    @Parameters("runner")
    public void swaggerPetstore_uploadFile_IT(@CitrusResource @Optional TestCaseRunner runner) {
        runner.run(echo("TODO: Code the test SwaggerPetstore_uploadFile_IT"));

        runner.run(http().client("httpClient")
            .send()
            .post(InterceptorHandler.getPath("/v2/pet/{petId}/uploadImage"))
            .contentType("application/json")
            .header("{petId}", "citrus:randomNumber(9)")
        );

        runner.run(http().client("httpClient")
            .receive()
            .response(HttpStatus.OK)
            .payload("{\"code\": \"@isNumber()@\",\"type\": \"@notEmpty()@\",\"message\": \"@notEmpty()@\"}")
        );

    }
}
