package helloworld

import org.springframework.beans.factory.annotation.Value

class WelcomeController {

    // In Groovy code you must use single quotes around the string for the value of the Value annotation otherwise it is
    // interpreted as a GString not a Spring expression.
    @Value('${info.app.internal.counter}')
    String counterInternal

    @Value('${info.app.testSystemProp}')
    String testSystemProp

    MyService myService

    MyBean myBean

    BookService bookService

    def demoBinding() {
        def bindingMap = [name: '  Sarah  ', author: 'Stephen King', age: 63, date: '1965-12-08']
        def book = new Book(bindingMap)

        render(
                text: book.toString(),
                contentType: "text/plain",
                encoding: "UTF-8"
        )
    }

    // http://localhost:8080/welcome/demoParams?age=90&author=Tolkien&name=Lord%20of%20the%20ring
    def demoParams() {
        // http://docs.grails.org/3.3.11/ref/Servlet%20API/request.html
        def userAgent = request.getHeader("User-Agent")
        def book = new Book(params) // bind request parameters onto properties of book
        def info = "User agent: ${userAgent}\nAuthor info: ${params.author}, age = ${params.age}, name = ${params.name}.\n${book}"

        render(
                text: info,
                contentType: "text/plain",
                encoding: "UTF-8"
        )
    }

    def showContextVersion() {
        log.info('Inside the showContextVersion method of controller')

        def info = "servletContext: ${servletContext.getMajorVersion()}.${servletContext.getMinorVersion()}"

        render info
    }

    def test() {
        log.info('Inside the test method of controller')

        def config = grailsApplication.config
        def lastPublishDate = myService.lastPublishDate
        def counter = config.getProperty('info.app.counter', Integer, 5)

        def info = "Configurations: ${lastPublishDate}.\nConter: ${counter}. \nConter internal: ${counterInternal}.\ntestSystemProp: ${testSystemProp}\n${myBean}\nParams: ${params}"
        render(
                text: info,
                contentType: "text/plain",
                encoding: "UTF-8"
        )
    }

    // http://localhost:8080/welcome/books.json
    def books() {
        // The respond method is the preferred way to return JSON
        respond bookService.list()
    }

}
