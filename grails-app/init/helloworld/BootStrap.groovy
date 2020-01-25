package helloworld

import grails.util.Environment

class BootStrap {

    def init = { ctx ->

        Environment.executeForCurrentEnvironment {
            production {
                // do something in production
                log.info("Production environnement starting...")
                ctx.setAttribute("env", "prod")
            }
            development {
                // do something only in development
                log.info("Development environnement starting...")
                ctx.setAttribute("env", "dev")
            }
        }

        ctx.setAttribute("foo", "bar")
    }

    def destroy = {
    }
}
