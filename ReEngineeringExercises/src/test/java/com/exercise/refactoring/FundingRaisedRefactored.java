package com.exercise.refactoring;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FundingRaisedRefactored{

    /**
     * Rigourous Test :-)
     */
    @Test
    public void whereGivenCompany() throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "Facebook");

        assertEquals(7, FundingRaised.where(options).size());
    }

    @Test
    public void whereGivenCity() throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("city", "Tempe");

        assertEquals(3, FundingRaised.where(options).size());
    }

    @Test
    public void whereGivenState() throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("state", "CA");

        assertEquals(873, FundingRaised.where(options).size());
    }

    @Test
    public void whereGivenRound() throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("round", "a");

        assertEquals(582, FundingRaised.where(options).size());
    }

    @Test
    public void multipleOptions() throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("round", "a");
        options.put("company_name", "Facebook");

        assertEquals(1, FundingRaised.where(options).size());
    }

    @Test
    public void whereNotExists() throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "NotFacebook");

        assertEquals(0, FundingRaised.where(options).size());
    }

    @Test
    public void whereCorrectKeys() throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "Facebook");

        Map<String, String> row = FundingRaised.where(options).get(0);

        assertEquals("facebook", row.get("permalink"));
        assertEquals("Facebook", row.get("company_name"));
        assertEquals("450", row.get("number_employees"));
        assertEquals("web", row.get("category"));
        assertEquals("Palo Alto", row.get("city"));
        assertEquals("CA", row.get("state"));
        assertEquals("1-Sep-04", row.get("funded_date"));
        assertEquals("500000", row.get("raised_amount"));
        assertEquals("angel", row.get("round"));
    }

    @Test
    public void findByGivenCompanyName() throws IOException, NoSuchEntryException {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "Facebook");

        Map<String, String> row = FundingRaised.findBy(options);

        assertEquals("facebook", row.get("permalink"));
        assertEquals("Facebook", row.get("company_name"));
        assertEquals("450", row.get("number_employees"));
        assertEquals("web", row.get("category"));
        assertEquals("Palo Alto", row.get("city"));
        assertEquals("CA", row.get("state"));
        assertEquals("1-Sep-04", row.get("funded_date"));
        assertEquals("500000", row.get("raised_amount"));
        assertEquals("angel", row.get("round"));
    }

    @Test
    public void findByGivenState() throws IOException, NoSuchEntryException {
        Map<String, String> options = new HashMap<>();
        options.put("state", "CA");

        Map<String, String> row = FundingRaised.findBy(options);

        assertEquals("digg", row.get("permalink"));
        assertEquals("Digg", row.get("company_name"));
        assertEquals("60", row.get("number_employees"));
        assertEquals("web", row.get("category"));
        assertEquals("San Francisco", row.get("city"));
        assertEquals("CA", row.get("state"));
        assertEquals("1-Dec-06", row.get("funded_date"));
        assertEquals("8500000", row.get("raised_amount"));
        assertEquals("b", row.get("round"));
    }

    @Test
    public void findByMultipleOptions() throws IOException, NoSuchEntryException {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "Facebook");
        options.put("round", "c");

        Map<String, String> row = FundingRaised.findBy(options);

        assertEquals("facebook", row.get("permalink"));
        assertEquals("Facebook", row.get("company_name"));
        assertEquals("450", row.get("number_employees"));
        assertEquals("web", row.get("category"));
        assertEquals("Palo Alto", row.get("city"));
        assertEquals("CA", row.get("state"));
        assertEquals("1-Oct-07", row.get("funded_date"));
        assertEquals("300000000", row.get("raised_amount"));
        assertEquals("c", row.get("round"));
    }

    @Test
    public void findByNotExists() {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "NotFacebook");
        options.put("round", "c");

        assertThrows(NoSuchEntryException.class, () -> FundingRaised.findBy(options));
    }
}
