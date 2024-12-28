package com.github.javabaz.darvazeh;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/github/javabaz/darvazeh")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.github.javabaz.darvazeh")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@RunMe and not @Skip")
public class RunCucumberTests {
}
