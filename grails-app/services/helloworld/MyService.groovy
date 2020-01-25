package helloworld

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional

@Transactional
class MyService implements GrailsConfigurationAware {

    String lastPublishDate

    @Override
    void setConfiguration(Config config) {
        lastPublishDate = config.getProperty('info.app.lastPublishDate')
    }

}
