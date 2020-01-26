package helloworld

import grails.converters.JSON
import grails.util.Environment

class BootStrap {

    def init = { ctx ->

        Environment.executeForCurrentEnvironment {
            production {
                log.info("Production environment starting...")
            }
            development {
                log.info("Development environment starting...")
            }
        }

        // registerCustomMarshallerForBook()
    }

    // https://manbuildswebsite.com/2010/02/15/rendering-json-in-grails-part-3-customise-your-json-with-object-marshallers/
    def registerCustomMarshallerForBook = {
        JSON.registerObjectMarshaller(Book) {
            def result = [:]

            result['title'] = it.name
            result['author'] = it.author
            result['publishDate'] = it.date
            result['full'] = "Book ${it.name} by author '${it.author}'"

            return result
        }
    }

    def destroy = {
    }
}
