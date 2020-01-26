package helloworld

import grails.databinding.BindUsing
import grails.databinding.BindingFormat
import groovy.transform.ToString

@ToString
class Book {

    String author

    @BindUsing({ obj, source -> source['name']?.toUpperCase().trim() })
    String name

    @BindingFormat('yyyy-MM-dd')
    Date date

    static constraints = {
    }

}
