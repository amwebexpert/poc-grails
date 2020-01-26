package helloworld

import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value

class WelcomeController {

    static responseFormats = ['json', 'html']

    // In Groovy code you must use single quotes around the string for the value of the Value annotation otherwise it is
    // interpreted as a GString not a Spring expression.
    @Value('${info.app.internal.counter}')
    String counterInternal

    @Value('${info.app.testSystemProp}')
    String testSystemProp

    MyService myService

    MyBean myBean

    BookService bookService

    def demoBindingOld() {
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
    // http://localhost:8080/welcome/books (because we defined JSON as a priority inside responseFormats at the beginning of the controller class)
    def books() {
        if (bookService.count() == 0) {
            def bindingMap = [name: '  Sarah  ', author: 'Stephen King', age: 63, date: '1965-12-08']
            def book = new Book(bindingMap)
            bookService.save(book)
        }

        // The respond method is the preferred way to return JSON
        respond bookService.list()
    }

    // Customized JSON example
    def grailsMapToJson = {
        // Grails Map object
        def myHomeAddress = [
                building: "25",
                street  : "High Street",
                city    : "Cambridge",
                country : "UK",
                pref    : true
        ]

        // Grails Map object
        def myWorkAddress = [
                building: "1",
                street  : "Science Park",
                city    : "Cambridge",
                country : "UK"]

        // Top level Grails Map object
        def dave = [
                name     : "David Bower",
                addresses: [myHomeAddress, myWorkAddress]]

        // Each Grails map becomes a JSON object and Grails lists become JSON arrays
        render dave as JSON
    }

    // http://localhost:8080/welcome/objectToJson
    def objectToJson = {
        def bindingMap = [name: '  Sarah  ', author: 'Stephen King', age: 63, date: '1965-12-08']
        def book = new Book(bindingMap)

        response.contentType = "application/json"
        render book.encodeAsJSON()

        // Notice that the ‘as’ operator is not overloaded for plain objects so if we try to write:
        // book as JSON
        // We get: Cannot cast object with class 'Book' to class 'grails.converters.JSON'
    }

    // http://localhost:8080/welcome/domainToJson
    def domainToJson = {
        def bindingMap = [name: '  Sarah  ', author: 'Stephen King', age: 63, date: '1965-12-08']
        def book = new Book(bindingMap)

        render book as JSON
    }

}
