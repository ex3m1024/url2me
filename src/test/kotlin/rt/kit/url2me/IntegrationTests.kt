package rt.kit.url2me

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import rt.kit.url2me.controller.MainController
import rt.kit.url2me.dto.UrlInput

private const val URI_GENERATE = "/generate"
private const val URI_RESOLVE_VALID = "/abcd"
private const val VALID_URL = "https://google.com"

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val controller: MainController) {

    @Test
    fun `Assert shortened URL is generated correctly`() {
        WebTestClient
            .bindToController(controller)
            .build()
            .post()
            .uri(URI_GENERATE)
            .body(Mono.just(UrlInput(VALID_URL)), UrlInput::class.java)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("url").isNotEmpty
    }

    @Test
    fun `Assert shortened URL is not generated if URL is not supplied`() {
        WebTestClient
            .bindToController(controller)
            .build()
            .post()
            .uri(URI_GENERATE)
            .exchange()
            .expectStatus().isBadRequest
    }

    @Test
    fun `Assert shortened URL is resolved correctly`() {
        WebTestClient
            .bindToController(controller)
            .build()
            .get()
            .uri(URI_RESOLVE_VALID)
            .exchange()
            .expectStatus().isOk
    }
}
