# Url2me
- **Url2me** is a REST API for a URL shortener
- currently in active development
- written in Kotlin, based on Spring Boot
- _not affiliated with https://url2.me/_

## How to launch
- Compile and run Application.kt  
(Docker to be added soon)

## How to use
- Send a POST to /generate with a "url" String, containing a proper URL, to get your URL formatted (you will get a URL like localhost:8080/yourCode)
- Send a GET to /yourCode (basically, visit the generated URL) to get redirected to your original URL
