package com.example.jpatest.nplus1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nplus1")
public class NPlus1Controller {
    private final ManyService manyService;
    private final OneService oneService;

    public NPlus1Controller(ManyService manyService, OneService oneService) {
        this.manyService = manyService;
        this.oneService = oneService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public void create() {
        List<Many> manyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Many many = new Many();
            many.setStr("many" + i);
            manyList.add(many);
        }
        manyService.saveAll(manyList);
        List<One> oneList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            One one = new One();
            one.setStr("one" + i);
            one.setManyList(manyList);
            oneList.add(one);
        }
        oneService.saveAll(oneList);
        System.out.println("-------------------------------");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public void read() {

        List<One> result = oneService.findAll();

        for (One one : result) {
            for (Many many : one.getManyList()) {
                many.getStr();
            }
        }
    }
}
