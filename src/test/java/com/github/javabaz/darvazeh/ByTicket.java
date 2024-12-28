package com.github.javabaz.darvazeh;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class ByTicket {

    @Given("The application is available at {string}")
    public void applicationBaseUrl(final String baseUrl) {
        log.info("Application is available at {}", baseUrl);
    }

    @When("The available connection to database is closing")
    public void whenTheAvailableConnectionToDatabaseIsClosing() {
        log.info("Connection closing");
    }

    @Then("The connection to the database is closed")
    public void thenTheConnectionToTheDatabaseIsClosed() {
        log.info("Connection closed");
    }
}
