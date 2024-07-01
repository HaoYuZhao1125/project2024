import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class DataManager_makeDonation_Test {

    @Test
    public void testSuccessfulDonation() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.makeDonation("contributor1", "fund1", 100);
        assertEquals("success", result);
    }

    @Test
    public void testFailedDonation() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"failure\",\"message\":\"Invalid fund ID\"}";
            }
        });

        String result = dm.makeDonation("contributor1", "fund1", 100);
        assertNull(result);
    }

    @Test
    public void testNullContributorId() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        try {
            dm.makeDonation(null, "fund1", 100);
        } catch (IllegalArgumentException e) {
            assertEquals("Contributor ID and Fund ID cannot be null", e.getMessage());
        }
    }

    @Test
    public void testNullFundId() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        try {
            dm.makeDonation("contributor1", null, 100);
        } catch (IllegalArgumentException e) {
            assertEquals("Contributor ID and Fund ID cannot be null", e.getMessage());
        }
    }

    @Test
    public void testEmptyContributorId() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.makeDonation("", "fund1", 100);
        assertEquals("Contributor ID and Fund ID cannot be empty", result);
    }

    @Test
    public void testEmptyFundId() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.makeDonation("contributor1", "", 100);
        assertEquals("Contributor ID and Fund ID cannot be empty", result);
    }

    @Test
    public void testNegativeAmount() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.makeDonation("contributor1", "fund1", -100);
        assertEquals("Donation amount must be positive", result);
    }

    @Test
    public void testZeroAmount() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.makeDonation("contributor1", "fund1", -1);
        assertEquals("Donation amount must be positive", result);
    }

    @Test
    public void testExceptionInMakeDonation() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                throw new IllegalStateException("Error in communicating with server");
            }
        });

        try {
            dm.makeDonation("contributor1", "fund1", 300);
        } catch (IllegalStateException e) {
            assertEquals("Error in communicating with server", e.getMessage());
        }
    }
}

