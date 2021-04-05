package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
        model.addAttribute("data","hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public  String helloMvc(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //"hello spring" 거의 쓸일 없음 문자열을 http의 body로 그대로 전송
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 반환하고 @ResponseBody 를 적어주면 json방식으로 반환하는게 기본이다.
    }

    static class Hello{
        private String name;
        //getter setter alt+insert 단축키
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
