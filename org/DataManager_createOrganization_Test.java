import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class DataManager_createOrganization_Test {

    @Test
    public void testCreateOrganization_Success() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        int code = dm.createOrganization("test", "test", "test", "test");
        assertEquals(code, 0);
    }

    @Test
    public void testCreateOrganization_Failure() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"error\",\"data\":\"\"}";
            }
        });

        try {
            int code = dm.createOrganization("test", "test", "test", "test");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Error in communicating with server");
        }
    }

    @Test
    public void testCreateOrganization_DuplicatedLoginName() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"error\",\"data\":\"login_1 dup key\"}";
            }
        });

        int code = dm.createOrganization("test", "test", "test", "test");
        assertEquals(code, 1);
    }
}
