package rt.kit.url2me.dto

import javax.validation.constraints.NotBlank

data class UrlInput(@field:NotBlank val url: String)

// TODO: add URL validation
// TODO: NotBlank
