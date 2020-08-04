package rt.kit.url2me.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@RestControllerAdvice
class ApplicationExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(Exception::class)])
    fun handleServerException(ex: Exception) = ResponseEntity(mapOf("message" to (ex.message)), HttpStatus.INTERNAL_SERVER_ERROR)

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus,
            request: WebRequest): ResponseEntity<Any> =
        ResponseEntity(mapOf("message" to (ex.message)), HttpStatus.BAD_REQUEST)

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus,
            request: WebRequest): ResponseEntity<Any> =
            ResponseEntity(mapOf("message" to (errorFieldsAsMap(ex))), HttpStatus.BAD_REQUEST)

    private fun errorFieldsAsMap(ex: MethodArgumentNotValidException) = ex.bindingResult.fieldErrors
            .map { it.field to it.defaultMessage }
            .toMap()
}
