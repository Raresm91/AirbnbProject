package application_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.GeneralFunctions;

import java.time.LocalDate;

public class SearchPage extends GeneralFunctions {

    private static String MAIN_TITLE = "You don't need to go far to find what matters.";
    private static By title = By.xpath("//div[@class= '_cpvtgy']");

    //Page element Search
    private static By btnSearch = By.xpath("//button[@type= 'submit']");
    private static By locationSearch = By.xpath("//input[@id = 'bigsearch-query-attached-query']");
    private static By checkInSearch = By.xpath("(//button[@class = '_g3m4nkc'])[1]");
    private static By guestsSearch = By.xpath("//button[@data-testid= 'structured-search-input-field-guests-button']");

    //Page elements Guests selection
    private static By guestPanel = By.xpath("//div[@data-testid ='structured-search-input-field-guests-panel']");
    private static By guestsAddAdults = By.xpath("//div[@id='stepper-adults']/button[2]");
    private static By guestsAddChildren = By.xpath("//div[@id='stepper-children']/button[2]");
    private static By guestsAddInfants = By.xpath("//div[@id='stepper-infants']/button[2]");


    /**
     * Method to validate the title after, opening the page
     */
    public static void validateTitle() {
        waitForElement(title);
        Assert.assertEquals(MAIN_TITLE, driver.findElement(title).getText());
    }

    /**
     * Method for entering location input in the search bar
     *
     * @param input
     */
    public static void enterSearchLocationInput(String input) {
        waitForElement(locationSearch);
        WebElement location = driver.findElement(locationSearch);
        location.sendKeys(input);
    }

    /**
     * Method for click on search button
     */
    public static void clickSearchButton() {
        waitForElement(btnSearch);
        driver.findElement(btnSearch).click();
    }

    /**
     * Method for click on Start date
     */
    public static void clickStartDatePicker() {
        waitForElement(checkInSearch);
        WebElement webElement = driver.findElement(checkInSearch);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    /**
     * Method for click on Guests selection
     */
    public static void clickGuestsSelection() {
        waitForElement(guestsSearch);
        WebElement webElement = driver.findElement(guestsSearch);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    /**
     * Method that returns the current date
     *
     * @return
     */
    public static String returnCurrentCalendarDate() {
        LocalDate currentdate = LocalDate.now();
        String sCurrentDate = String.valueOf(currentdate);
        return (sCurrentDate);
    }

    /**
     * Method that returns the desired future date, using int parameter
     *
     * @param dayCounter
     * @return
     */
    public static String returnFutureCalendarDate(int dayCounter) {
        LocalDate currentdate = LocalDate.now();
        LocalDate futureDate = currentdate.plusDays(dayCounter);
        String sFutureDate = String.valueOf(futureDate);
        return (sFutureDate);
    }

    /**
     * Method to select from calendar current date
     */
    public static void selectCalendarCurrentDate() {
        String day = returnCurrentCalendarDate();
        WebElement Date = driver.findElement(By.xpath("//div[contains(@data-testid, 'datepicker-day-" + day + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Date);
    }

    /**
     * Method to select caledar end day, using parameter for days period
     *
     * @param days
     */
    public static void selectCalendarDayEnd(int days) {
        String daysCounter = returnFutureCalendarDate(days);
        WebElement Date = driver.findElement(By.xpath("//div[contains(@data-testid, 'datepicker-day-" + daysCounter + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Date);
    }

    /**
     * Method for selecting the desired number of adults, children or infants
     *
     * @param adults
     * @param children
     * @param infants
     */
    public static void selectGuests(String adults, String children, String infants) {
        clickGuestsSelection();
        waitForElement(guestPanel);
        if (adults != null) {
            waitForElement(By.xpath("//div[@id='stepper-adults']/div/span[1]"));
            if (driver.findElement(By.xpath("//div[@id='stepper-adults']/div/span[1]")).getText()
                    .equalsIgnoreCase(adults)) {
                System.out.println("The Adults are selected as: " + adults);
            } else {
                do {
                    driver.findElement(guestsAddAdults).click();
                } while (!driver.findElement(By.xpath("//div[@id='stepper-adults']/div/span[1]"))
                        .getText().equalsIgnoreCase(adults));
            }
        }
        if (children != null) {
            waitForElement(By.xpath("//div[@id='stepper-children']/div/span[1]"));
            if (driver.findElement(By.xpath("//div[@id='stepper-children']/div/span[1]")).getText()
                    .equalsIgnoreCase(children)) {
                System.out.println("The Children are selected as: " + children);
            } else {
                do {
                    driver.findElement(guestsAddChildren).click();
                } while (!driver.findElement(By.xpath("//div[@id='stepper-children']/div/span[1]"))
                        .getText().equalsIgnoreCase(children));
            }
        }
        if (infants != null) {
            waitForElement(By.xpath("//div[@id='stepper-infants']/div/span[1]"));
            if (driver.findElement(By.xpath("//div[@id='stepper-infants']/div/span[1]")).getText()
                    .equalsIgnoreCase(infants)) {
                System.out.println("The Infants are selected as: " + infants);
            } else {
                do {
                    driver.findElement(guestsAddInfants).click();
                } while (!driver.findElement(By.xpath("//div[@id='stepper-infants']/div/span[1]"))
                        .getText().equalsIgnoreCase(infants));
            }
        }
    }
}
