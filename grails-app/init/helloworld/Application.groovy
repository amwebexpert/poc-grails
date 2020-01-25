package helloworld

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.context.annotation.Bean

class Application extends GrailsAutoConfiguration {

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    MyBean myBean() {
        return new MyBean()
    }

}