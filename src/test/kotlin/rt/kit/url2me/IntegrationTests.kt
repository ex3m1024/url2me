package rt.kit.url2me

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import rt.kit.url2me.controller.MainController

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val controller: MainController) {

    @Test
    fun test() {
        WebTestClient
            .bindToController(controller)
            .build()
            .get()
            .uri("/generate")
            .exchange()
            .expectStatus().isOk
        // TODO: cover /generate and /{code} with simple integration tests
    }
}
