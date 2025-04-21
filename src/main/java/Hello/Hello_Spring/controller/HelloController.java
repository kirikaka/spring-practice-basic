package Hello.Hello_Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","Hello1!");
        return "Hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model) {
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value="name") String name) {
        return "Hello " + name; //String에 값을 넣으면 hello value가 된다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public String helloApi(@RequestParam(value="name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return "hello" + hello.name;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
}
