package com.moshka;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;



@RunWith(JUnitPlatform.class)
@SelectClasses({SampleTest.class,BicycleChallengeApplicationTests.class})
public class SampleTest {

    @BeforeAll
    public void beforeAl(){

        System.out.println(" ******************** Before All tests  In Sample Class************");
    }
}
