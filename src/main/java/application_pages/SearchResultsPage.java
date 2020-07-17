package application_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.GeneralFunctions;

import java.time.LocalDate;
import java.util.List;

public class SearchResultsPage extends GeneralFunctions {

    //Main Page elements
    private static By resultList = By.xpath("//div[@itemprop = 'itemListElement']");
    private static By resultLocationHeader = By.xpath("//button[@data-index = '0']");
    private static By resultDateHeader = By.xpath("//button[@data-index = '1']");
    private static By resultGuestsHeader = By.xpath("//button[@data-index = '2']");
    private static By resultPropertyTitle = By.xpath("//div[@class = '_1c2n35az']");
    private static By resultPropertyPrice = By.xpath("//span[@class ='_1p7iugi']");
    private static By btnMoreFilters = By.xpath("(//span[contains(text(), 'More filters')])[1]");
    //More filters elements
    private static By moreFiltersPanel = By.xpath("//div[@class = '_12k64q0']");
    private static By extraPool = By.xpath("//input[@id='filterItem-checkbox-amenities-7' and @name ='Pool']");
    private static By addBeds = By.xpath("//div[@id='filterItem-stepper-min_beds-0']/button[2]");
    private static By addBedrooms = By.xpath("//div[@id='filterItem-stepper-min_bedrooms-0']/button[2]");
    private static By addBathrooms = By.xpath("//div[@id='filterItem-stepper-min_bathrooms-0']/button[2]");
    private static By btnShowResults = By.xpath("//button[@data-testid = 'more-filters-modal-submit-button']");
    private static By amenitiesTitle = By.xpath("//h2[contains(text(), 'Amenities')]");
    private static By amenities = By.xpath("//div[@class = '_19xnuo97']/div/div[1]");

    //Map elements
    private static By mapHighlightedProperty = By.cssSelector("div[style*='background-color: rgb(34, 34, 34);']");
    private static By mapPropertyTitle = By.xpath("//div[@class = '_v96gnbz']");
    private static By mapPropertyPrice = By.xpath("//div[@class = '_mvzr1f2']/span/span[@class='_1p7iugi']");

    /**
     * Method to click on button More filters
     */
    private static void clickMoreFilters() {
        waitForElement(btnMoreFilters);
        driver.findElement(btnMoreFilters).click();
    }

    /**
     * Method click on extra filter results
     */
    public static void clickShowFilterResults() {
        waitForElement(btnShowResults);
        WebElement btnShowResult = driver.findElement(btnShowResults);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnShowResult);
    }

    /**
     * Method to validate that the result matches the desired location
     *
     * @param location
     */
    public static void validateLocation(String location) {
        waitForElement(resultLocationHeader);
        String locationResultHeader = returnStringElement(resultLocationHeader);
        if (locationResultHeader.contains(location)) {
            System.out.println("The location result is as expected: " + location);
        } else {
            Assert.fail("The location results are not from : " + location);
        }
    }

    /**
     * Method to validate that the result matches the desired number of guests, using string
     *
     * @param guests
     */
    public static void validateGuests(String guests) {
        waitForElement(resultGuestsHeader);
        String locationResultHeader = returnStringElement(resultGuestsHeader);
        if (locationResultHeader.contains(guests)) {
            System.out.println("The guests result is as expected: " + guests);
        } else {
            Assert.fail("The guests results are not from : " + guests);
        }
    }

    /**
     * Method to validate the desired period of time, using current date and days interval
     * Note: the method might need to be changed, current date is not used
     *
     * @param dayCounter
     */
    public static void validateTimePeriod(int dayCounter) {
        waitForElement(resultDateHeader);
        LocalDate currentdate = LocalDate.now();
        LocalDate futureDate = currentdate.plusDays(dayCounter);
        int currentDay = currentdate.getDayOfMonth();
        int futureDay = futureDate.getDayOfMonth();
        String searchResultPeriod = returnStringElement(resultDateHeader);
        if (searchResultPeriod.contains(Integer.toString(currentDay)) &&
                searchResultPeriod.contains(Integer.toString(futureDay))) {
            System.out.println("The time period result returned is as expected: " +
                    searchResultPeriod.substring(searchResultPeriod.lastIndexOf("\n")));
        } else {
            Assert.fail("The time period result is not as expected: " +
                    searchResultPeriod.substring(searchResultPeriod.lastIndexOf("\n")));
        }
    }

    /**
     * Method to validate that the result fulfills the guests criteria
     *
     * @param guests
     */
    public static void validateGuestAccomodationResults(String guests) {
        List<WebElement> resultList = driver.findElements(By.xpath("//div[@class= '_kqh46o'][1]"));
        for (WebElement result : resultList) {
            String[] resultRoom = result.getText().split(" guests");
            String guestsRoomResult = resultRoom[0];
            if (Integer.parseInt(guests) > Integer.parseInt(guestsRoomResult)) {
                Assert.fail("Test failed: The results can not accommodate the desired number of guests!");
            }
        }
    }

    /**
     * Method to validate bedrooms extra filter
     *
     * @param bedrooms
     */
    public static void validateBedroomsAccomodationResults(String bedrooms) {
        waitForElement(By.xpath("//div[@class= '_kqh46o'][1]"));
        List<WebElement> resultList = driver.findElements(By.xpath("//div[@class= '_kqh46o'][1]"));
        for (WebElement result : resultList) {
            String[] resultBedroom = result.getText().split(" bedrooms");
            String bedroomResult = resultBedroom[0].substring(Math.max(resultBedroom[0].length() - 2, 0)).trim();
            if (Integer.parseInt(bedrooms) > Integer.parseInt(bedroomResult)) {
                Assert.fail("Test failed: The results can not accommodate the desired number of guests for bedrooms!");
            }
        }
    }

    /**
     * Method to select extra facilities
     * Note: can be extended for any desired amenitie
     *
     * @param pool
     */
    public static void selectExtraFacilities(boolean pool) {
        if (pool = true) {
            WebElement poolCheckbox = driver.findElement(extraPool);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", poolCheckbox);
        }
    }

    /**
     * Method to add extra filters
     *
     * @param beds
     * @param bedrooms
     * @param bathrooms
     */
    public static void selectExtraBedsBedRooms(String beds, String bedrooms, String bathrooms) {
        clickMoreFilters();
        waitForElement(moreFiltersPanel);
        if (beds != null) {
            if (driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_beds-0']/div/span[1]")).getText()
                    .equalsIgnoreCase(beds)) {
                System.out.println("The Beds are selected as: " + beds);
            } else {
                do {
                    driver.findElement(addBeds).click();
                } while (!driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_beds-0']/div/span[1]"))
                        .getText().equalsIgnoreCase(beds));
            }
        }
        if (bedrooms != null) {
            if (driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_bedrooms-0']/div/span[1]")).getText()
                    .equalsIgnoreCase(bedrooms)) {
                System.out.println("The Bedrooms are selected as: " + bedrooms);
            } else {
                do {
                    driver.findElement(addBedrooms).click();
                } while (!driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_bedrooms-0']/div/span[1]"))
                        .getText().equalsIgnoreCase(bedrooms));
            }
        }
        if (bathrooms != null) {
            if (driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_bathrooms-0']/div/span[1]")).getText()
                    .equalsIgnoreCase(bathrooms)) {
                System.out.println("The Bathrooms are selected as: " + bathrooms);
            } else {
                do {
                    driver.findElement(addBathrooms).click();
                } while (!driver.findElement(By.xpath("//div[@id='filterItem-stepper-min_bathrooms-0']/div/span[1]"))
                        .getText().equalsIgnoreCase(bathrooms));
            }
        }
    }

    /**
     * Method to click on the results list, by Index
     *
     * @param index
     */
    public static void openResultListByIndex(int index) {
        waitForElement(resultList);
        List<WebElement> results = driver.findElements(resultList);
        for (int i = 0; i < results.size(); i++) {
            if (i == index) {
                results.get(i).click();
            }
        }
    }

    /**
     * Method to validate the amenities present in the results
     */
    public static void validateAmenities(String amenitie) {
        waitForElement(amenitiesTitle);
        boolean found = false;
        List<WebElement> resultAmenities = driver.findElements(amenities);
        for (WebElement resultAmenitie : resultAmenities) {
            if (resultAmenitie.getText().contains(amenitie)) {
                System.out.println("Amenitie was found: " + resultAmenitie.getText());
                found = true;
                break;
            }
        }
        if (found = false) {
            Assert.fail("The : " + amenitie + " ammenitie, was not found in the result!");
        }
    }

    /**
     * Method to hove over an search result by index
     *
     * @param index
     */
    public static void hoverOnResultSearch(int index) {
        waitForElement(resultList);
        Actions action = new Actions(driver);
        List<WebElement> results = driver.findElements(resultList);
        for (int i = 0; i < results.size(); i++) {
            if (i == index) {
                action.moveToElement(results.get(i)).perform();
            }
        }
    }

    /**
     * Method to validate the price highlighted on the map, matches with the result hovered on Map
     */
    public static void validatePropertyMapHighlight(int index) {
        waitForElement(mapHighlightedProperty);
        String mapPropertyCost = returnStringElement(mapHighlightedProperty);
        String propertyPrice = returnStringElementbyIndexList(index, resultPropertyPrice);
        String finalPropertyPrice = propertyPrice.substring(propertyPrice.lastIndexOf("\n"));
        if (!finalPropertyPrice.contains(mapPropertyCost)) {
            Assert.fail("The property price on the map highlighted is not the same as the result");
        }
    }

    /**
     * Method to click on highlighted property
     */
    public static void clickOnHighlightedPropertyOnMap() {
        waitForElement(mapHighlightedProperty);
        WebElement highlightedProperty = driver.findElement(mapHighlightedProperty);
        highlightedProperty.click();
    }

    /**
     * Method to validate the popup values from map against the results
     *
     * @param index
     */
    public static void validateDetailOnMapWithSearchResults(int index) {
        String propertyResultTitle = returnStringElementbyIndexList(index, resultPropertyTitle);
        String propertyMapTitle = returnStringElement(mapPropertyTitle);
        Assert.assertEquals(propertyResultTitle, propertyMapTitle);

        String mapPopUpPropertyCost = returnStringElement(mapPropertyPrice);
        String finalMapPopUpPropertyCost = mapPopUpPropertyCost.substring(mapPopUpPropertyCost.lastIndexOf("\n"));
        String propertyPrice = returnStringElementbyIndexList(index, resultPropertyPrice);
        String finalPropertyPrice = propertyPrice.substring(propertyPrice.lastIndexOf("\n"));
        Assert.assertEquals(finalMapPopUpPropertyCost, finalPropertyPrice);
    }

}
