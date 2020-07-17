import application_pages.SearchPage;
import application_pages.SearchResultsPage;
import org.testng.annotations.Test;
import utils.GeneralFunctions;

public class TestsSearchCriteriaFiltersAndMap {


    //Test: Verify that the results match the search criteria
    @Test(priority = 0)
    public void testValidateSearchCriteria() {
        //Data required for test
        String location = "Rome";
        String numberAdults = "2";
        String numberChildren = "1";
        String numberTotalGuests = "3";
        int daysTotal = 7;

        //Step 01: Start the browser and open the page
        GeneralFunctions.openBrowser(GeneralFunctions.getSourceLink());
        //Step 02: Validate that the page is open
        SearchPage.validateTitle();
        //Step 03: Enter the Location in the search bar
        SearchPage.enterSearchLocationInput(location);
        //Step 04: Select the Start date
        SearchPage.clickStartDatePicker();
        SearchPage.selectCalendarCurrentDate();
        //Step 05: Select the End date to be 7 days more than start date
        SearchPage.selectCalendarDayEnd(daysTotal);
        //Step 06: Select the guests number
        SearchPage.selectGuests(numberAdults, numberChildren, "0");
        //Step 07: Click on Search button
        SearchPage.clickSearchButton();
        //Step 08: Validate that the result matches the desired Location
        SearchResultsPage.validateLocation(location);
        //Step 09: Validate that the result matches the desired number of guests
        SearchResultsPage.validateGuests(numberTotalGuests);
        //Step 10: Validate that the result matches the desired time period
        SearchResultsPage.validateTimePeriod(daysTotal);
        //Step 11: Validate that the result accomodation matches the selected number of guests
        SearchResultsPage.validateGuestAccomodationResults(numberTotalGuests);
        //Step 12: Close browser
        GeneralFunctions.closeBrowser();
    }

    //Test: Verify that the results and details page match the extra filters
    @Test(priority = 1)
    public void testValidateDetailsPageExtraFilters() {
        //Data required for test
        String location = "Rome";
        String numberAdults = "2";
        String numberChildren = "1";
        String numberTotalGuests = "3";
        String amenitie = "Pool";
        String numberBedrooms = "5";
        int daysTotal = 7;
        int firstProperty = 0;

        //Step 01: Start the browser and open the page
        GeneralFunctions.openBrowser(GeneralFunctions.getSourceLink());
        //Step 02: Validate that the page is open
        SearchPage.validateTitle();
        //Step 03: Enter the Location in the search bar
        SearchPage.enterSearchLocationInput(location);
        //Step 04: Select the Start date
        SearchPage.clickStartDatePicker();
        SearchPage.selectCalendarCurrentDate();
        //Step 05: Select the End date to be 7 days more than start date
        SearchPage.selectCalendarDayEnd(daysTotal);
        //Step 06: Select the guests number
        SearchPage.selectGuests(numberAdults, numberChildren, "0");
        //Step 07: Click on Search button
        SearchPage.clickSearchButton();
        //Step 08: Validate that the result matches the desired Location
        SearchResultsPage.validateLocation(location);
        //Step 09: Validate that the result matches the desired number of guests
        SearchResultsPage.validateGuests(numberTotalGuests);
        //Step 10: Validate that the result matches the desired time period
        SearchResultsPage.validateTimePeriod(daysTotal);
        //Step 11: Validate that the result accomodation matches the selected number of guests
        SearchResultsPage.validateGuestAccomodationResults(numberTotalGuests);
        //Step 12: Select extra filters
        SearchResultsPage.selectExtraBedsBedRooms("0", numberBedrooms, "0");
        SearchResultsPage.selectExtraFacilities(true);
        //Step 13: Click show results
        SearchResultsPage.clickShowFilterResults();
        //Step 14: Validate that the filtered results can accomodate at least the desired number of bedrooms
        SearchResultsPage.validateBedroomsAccomodationResults(numberBedrooms);
        //Step 15: Open first result
        SearchResultsPage.openResultListByIndex(firstProperty);
        GeneralFunctions.switchtoSecondChromeTab();
        //Step 16: Validate in the amenities popup pool is displayed under facilities category
        SearchResultsPage.validateAmenities(amenitie);
        //Step 17: Close browser
        GeneralFunctions.closeBrowser();
    }

    //Test to verify that a property is displayed on the map correctly
    @Test(priority = 2)
    public void testValidatePropertyMap() {
        //Data required for test
        String location = "Rome";
        String numberAdults = "2";
        String numberChildren = "1";
        String numberTotalGuests = "3";
        int daysTotal = 7;
        int firstProperty = 0;

        //Step 01: Start the browser and open the page
        GeneralFunctions.openBrowser(GeneralFunctions.getSourceLink());
        //Step 02: Validate that the page is open
        SearchPage.validateTitle();
        //Step 03: Enter the Location in the search bar
        SearchPage.enterSearchLocationInput(location);
        //Step 04: Select the Start date
        SearchPage.clickStartDatePicker();
        SearchPage.selectCalendarCurrentDate();
        //Step 05: Select the End date to be 7 days more than start date
        SearchPage.selectCalendarDayEnd(daysTotal);
        //Step 06: Select the guests number
        SearchPage.selectGuests(numberAdults, numberChildren, "0");
        //Step 07: Click on Search button
        SearchPage.clickSearchButton();
        //Step 08: Validate that the result matches the desired Location
        SearchResultsPage.validateLocation(location);
        //Step 09: Validate that the result matches the desired number of guests
        SearchResultsPage.validateGuests(numberTotalGuests);
        //Step 10: Validate that the result matches the desired time period
        SearchResultsPage.validateTimePeriod(daysTotal);
        //Step 11: Validate that the result accomodation matches the selected number of guests
        SearchResultsPage.validateGuestAccomodationResults(numberTotalGuests);
        //Step 12: Hover over the first property
        SearchResultsPage.hoverOnResultSearch(firstProperty);
        //Step 13: Validate the property is displayed on the map
        SearchResultsPage.validatePropertyMapHighlight(firstProperty);
        //Step 14: Click on Highlighted property on map
        SearchResultsPage.clickOnHighlightedPropertyOnMap();
        //Step 15: Validate that the results from the popup match the result from search
        SearchResultsPage.validateDetailOnMapWithSearchResults(firstProperty);
        //Step 16: Close browser
        GeneralFunctions.closeBrowser();
    }


}
