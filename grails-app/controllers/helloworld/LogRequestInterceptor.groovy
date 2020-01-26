package helloworld


class LogRequestInterceptor {

    LogRequestInterceptor() {
        match(controller: "welcome", action: "domainToJson")
    }

    boolean before() {
        log.info("Here Here Here Here Here Here Here Here Here Here Here Here Here Here ")
        true
    }

    boolean after() {
        log.info("There There There There There There There There There it is!")
        true
    }

    void afterView() {
    }
}
