package helloworld

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.core.GrailsApplicationLifeCycle
import org.springframework.context.annotation.Bean

class Application extends GrailsAutoConfiguration implements GrailsApplicationLifeCycle {

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    MyBean myBean() {
        new MyBean()
    }

    @Override
    void onStartup(Map<String, Object> event) {
        super.onStartup(event)
        log.info("********** HelloWorld demo APP STARTUP")
    }

    @Override
    void onShutdown(Map<String, Object> event) {
        super.onShutdown(event)
        log.info("********** HelloWorld demo APP SHUTDOWN")
    }

}