package com.github.javabaz.darvazeh;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class ByTicket {


        @Given("user open site and click on the buy tickets button")
        public void user_open_site_and_click_on_the_buy_tickets_button() {
            log.info("User opened site and clicked on the buy tickets button");
            // Implement the step to open the site and click on the buy tickets button
            // For example, using a mock setup or simulating the click action
            // WebDriver driver = new ChromeDriver();
            // driver.get("http://localhost:8080");
            // WebElement buyTicketsButton = driver.findElement(By.id("buy-tickets-button"));
            // buyTicketsButton.click();
        }

        @When("we have no tickets")
        public void we_have_no_tickets() {
            log.info("No tickets are available");
            // Implement the step to check if no tickets are available
            // Simulate or check the condition for no tickets available
        }

        @Then("give exception")
        public void give_exception() {
            log.info("Exception is given");
            // Implement the step to throw an exception
        }

    @When("we have  no tickets")
    public void weHaveNoTickets() {
    }


}

