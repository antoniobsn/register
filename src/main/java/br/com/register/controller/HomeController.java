package br.com.register.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asampaio on 30/12/17.
 */

@RestController
@RequestMapping("api/v1/")
public class HomeController {

    @GetMapping("teste")
    public void test(){
        System.out.print("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[TESTE]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
    }
}
