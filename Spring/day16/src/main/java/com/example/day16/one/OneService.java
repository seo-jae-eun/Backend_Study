package com.example.day16.one;

import com.example.day16.one.model.One;
import com.example.day16.one.model.OneDto;
import com.example.day16.one.querydsl.OneRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OneService {
    private final OneRepository oneRepository;


    public void saveAll(List<One> oneList) {
        oneRepository.saveAll(oneList);
    }

    public List<One> findAll() {

        return oneRepository.findAll();
    }

    public List<One> jpql01() {

        return oneRepository.findAll();
    }

    public List<One> jpql02() {

        return oneRepository.findAllByIdxGreaterThan(1L);
    }

    public List<One> jpql03() {

        return oneRepository.findAllOneWithMany();
    }


    public List<OneDto> jpql04() {
        List<OneDto> result = oneRepository.findAllOneDto();

        return result;
    }


    public List<One> jpql05(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "idx"));
        Page<One> result = oneRepository.findPageBy(pageable);

        List<One> oneList = result.getContent();
        System.out.println("total : " + result.getTotalElements()); // 전체 수를 카운트 하는 건 안좋음
        System.out.println("num : " + result.getNumber());
        System.out.println("totalPage : " + result.getTotalPages());
        System.out.println("first : " + result.isFirst());
        System.out.println("next : " + result.hasNext());
        System.out.println("last : " + result.isLast());

        return oneList;
    }

    public List<One> jpql06(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "idx"));
        Slice<One> result = oneRepository.findSliceBy(pageable);

        List<One> oneList = result.getContent();
//        System.out.println("total : " + result.getTotalElements());
        System.out.println("num : " + result.getNumber());
//        System.out.println("totalPage : " + result.getTotalPages());
        System.out.println("first : " + result.isFirst());
        System.out.println("next : " + result.hasNext()); // total 개수를 세지 않기 때문에 만약에 LIMIT 0,5를 했을 대 0,6으로 SELECT 함
        System.out.println("last : " + result.isLast());

        return oneList;
    }


    public List<One> jpql07() {
        List<One> result = oneRepository.findAllEntityGraphBy();


        return result;
    }


    public List<One> findOneByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"));
        Page<One> result = oneRepository.findAll(pageable);

        List<One> oneList = result.getContent();

        return oneList;
    }

    public List<One> dsl01() {
        List<One> result = oneRepository.findAll();

        return result;
    }

    public List<One> dsl02() {
        List<One> result = oneRepository.findAllWithMany();

        return result;
    }

    public List<One> dsl03(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"));
        List<One> result = oneRepository.findAllWithManyPaging(pageable);

        return result;
    }

    public List<One> dsl04(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"));
        List<One> result = oneRepository.findAllWithManyPaging2(pageable);

        return result;
    }

    public List<One> dsl05(Long idx, String str) {
        List<One> result = oneRepository.findAllWithCriteria(idx, str);

        return result;
    }

    public List<One> dsl06(Long idx, String str) {
        List<One> result = oneRepository.findAllWithCriteria2(idx, str);

        return result;
    }
}
