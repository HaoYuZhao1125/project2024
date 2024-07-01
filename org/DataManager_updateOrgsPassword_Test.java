import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
public class DataManager_updateOrgsPassword_Test {
    @Test
    public void testSuccess() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {

            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";

            }
        });
        String updatedOrgsPassword = dm.updateOrgsPassword("12345", "123456");
        assertNotNull(updatedOrgsPassword);
        assertEquals("success", updatedOrgsPassword);
    }
    @Test
    public void testUnsuccessful() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {

            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"failure\"}";

            }
        });
        String updatedOrgsPassword = dm.updateOrgsPassword("12345", "123456");
        assertEquals("fail", updatedOrgsPassword);
    }

    @Test
    public void testException() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                throw new IllegalArgumentException("Some errors occurred.");
            }
        });

        try {
            dm.updateOrgsPassword("1234", "1234567");
        } catch (IllegalStateException e) {
            assertEquals("Error in communicating with server", e.getMessage());
        }
    }
    @Test
    public void testEmptyNullInputParam1() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.updateOrgsPassword(null, "112334");
        assertEquals("Invalid Organization ID.", result);

        result = dm.updateOrgsPassword("", "332213");
        assertEquals("Invalid Organization ID.", result);
    }

    @Test
    public void testEmptyNullInputParam2() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.updateOrgsPassword("11233", null);
        assertEquals("Invalid Password.", result);

        result = dm.updateOrgsPassword("33332", "");
        assertEquals("Invalid Password.", result);
    }
}
