package com.test.testserver;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final TestRepository testRepository;

    @GetMapping("/test/")
    public Result<List<TestData>> get() {
        System.out.println("test");
        return new Result(testRepository.findAll());
    }

    @GetMapping("/test/{id}")
    public TestData getContent(@PathVariable("id") Integer id) {
        System.out.println("tteesstt");
        return testRepository.findById(id).get();
    }

    @PostMapping("/test")
    public Result<TestData> create(@RequestBody TestData testData) {
        testData = testRepository.save(testData);
        System.out.println("testData" + testData);
        return new Result(testData);
    }

    @DeleteMapping("/test/{id}")
    public Result<Integer> delete(@PathVariable int id) {
        Result r = null;
        try {
            TestData testData = testRepository.findById(id).orElseThrow();
            testRepository.delete(testData);
            r = new Result(id);
        } catch (Exception  e) {
            r = new Result();
        }
        return r;
    }
}
