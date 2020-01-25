package helloworld

import org.springframework.beans.factory.annotation.Value

class WelcomeController {

    // In Groovy code you must use single quotes around the string for the value of the Value annotation otherwise it is
    // interpreted as a GString not a Spring expression.
    @Value('${info.app.internal.counter}')
    String counterInternal

    MyService myService

    def test() {
        def config = grailsApplication.config
        def lastPublishDate = myService.lastPublishDate
        def counter = config.getProperty('info.app.counter', Integer, 5)

        render "Configurations: ${lastPublishDate}. Conter: ${counter}. Conter internal: ${counterInternal}"
    }

}
