package helloworld

import groovy.transform.ToString

@ToString
class Book {

    String name
    String author

    static constraints = {
    }

}
