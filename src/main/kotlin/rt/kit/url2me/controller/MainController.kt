package rt.kit.url2me.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rt.kit.url2me.dto.UrlInput
import rt.kit.url2me.dto.UrlOutput
import rt.kit.url2me.service.ShortenerService
import javax.validation.Valid

@RestController
@RequestMapping("/")
class MainController(val service: ShortenerService, @Value("\${urldomain}") val domain: String) {

    @PostMapping("generate")
    fun generate(@Valid @RequestBody body: UrlInput) = ResponseEntity(UrlOutput("$domain/${service.shorten(body.url)}"), HttpStatus.OK)

    @GetMapping("{code}")
    fun resolve(@PathVariable(value = "code") code: String) =
            ResponseEntity<Any>(HttpHeaders().also { it.add("Location", service.resolve(code)) }, HttpStatus.MOVED_PERMANENTLY)
}
