package com.demo.mvc;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.demo.mvc.mapper")
@Transactional
@Rollback
public abstract class BaseTest extends TestCase {

    @BeforeClass
    public static void setup() {

    }
}
