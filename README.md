# poc-grails
Grails POC &amp; experimentations

## Starting from command line

    grails -DSYS_PROP_VALUE="this-is-my-usefull-system-property" run-app

## Build and run as a jar

    grails clean
    grails package
    /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.232.b09-0.el7_7.x86_64/bin/java -DSYS_PROP_VALUE=yyyyy -jar build/libs/helloworld-0.1.war
