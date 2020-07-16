package rt.kit.url2me.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import rt.kit.url2me.dto.UrlInput
import rt.kit.url2me.dto.UrlOutput
import rt.kit.url2me.service.ShortenerService
import javax.validation.Valid

@RestController
@RequestMapping("/")
class MainController(val service: ShortenerService) {

    @PostMapping("generate")
    @ResponseStatus(HttpStatus.OK)
    fun generate(@Valid @RequestBody body: UrlInput) = UrlOutput("localhost:8080/${service.shorten(body.url)}")

    // TODO: Exception classes, ExceptionHandler

    @GetMapping("{code}")
    fun resolve(@PathVariable(value = "code") code: String) = "TODO: Redirecting to ${service.resolve(code)}..."

}
