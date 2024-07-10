package com.example.day16;

import com.example.day16.many.Many;
import com.example.day16.many.ManyService;
import com.example.day16.one.OneService;
import com.example.day16.one.model.One;
import com.example.day16.one.model.OneDto;
import jakarta.persistence.criteria.CriteriaBuilder;
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
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read2")
    public void read2(Integer page, Integer size) {

        List<One> result = oneService.findOneByPage(page, size);

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/jpql01")
    public void jpql01() {

        List<One> result = oneService.jpql01();

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/jpql02")
    public void jpql02() {

        List<One> result = oneService.jpql02();

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/jpql03")
    public void jpql03() { // 한번에 너무 많은 데이터를 select

        List<One> result = oneService.jpql03();

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/jpql04")
    public void jpql04() {

        List<OneDto> result = oneService.jpql04();

        for (OneDto oneDto : result) {
            System.out.println(oneDto.getManyStr());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/jpql05")
    public void jpql05(Integer page, Integer size) { // jpql 아님

        List<One> result = oneService.jpql05(page, size);

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/jpql06")
    public void jpql06(Integer page, Integer size) {

        List<One> result = oneService.jpql06(page, size);

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/jpql07")
    public void jpql07() {

        List<One> result = oneService.jpql07();

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dsl01")
    public void dsl01() {

        List<One> result = oneService.dsl01();

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dsl02")
    public void dsl02() { // 한번에 다 가지고 오는거 안좋음

        List<One> result = oneService.dsl02();

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dsl03")
    public void dsl03(Integer page, Integer size) {
        // 그냥 50개를 다 select 한 다음에 잘라서 25개만 보여줌 -> 이 방법 x (ToMany 일 때)
        //      -> 페이징 처리 할꺼면 join 없이 dsl02에 limit 붙여서 해야함 -> dsl04
        List<One> result = oneService.dsl03(page, size);
        
        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dsl04")
    public void dsl04(Integer page, Integer size) {
        List<One> result = oneService.dsl04(page, size);

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dsl05")
    public void dsl05(Long idx, String str) {
        List<One> result = oneService.dsl05(idx, str);

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dsl06")
    public void dsl06(Long idx, String str) {
        List<One> result = oneService.dsl06(idx, str);

        for (One one : result) {
            for (Many many : one.getManyList()) {
                System.out.println(many.getStr());
            }
        }
    }

}
