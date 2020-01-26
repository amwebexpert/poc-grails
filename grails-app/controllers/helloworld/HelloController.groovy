package helloworld

class HelloController {

    def index() {
        render "amwebexpert@gmail.com"
    }

    // http://localhost:8080/hello/redirectDemo
    def redirectDemo() {
        // Also redirects to the index action in the home controller
        redirect(controller: 'welcome', action: 'test', params: [myparam: "myvalue"])
    }
}
