package ru.otus.java.qa.pro;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("ru.otus.java.qa.pro")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ru.otus.java.qa.pro")
public class RunnerTest {
}
