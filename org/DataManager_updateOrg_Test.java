import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class DataManager_updateOrg_Test {
    @Test
    public void testUpdateOrg_Success() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"success\"}";
            }
        });

        String result = dm.updateOrg("validOrgId", "newName", "newDescription");
        assertEquals("success", result);
    }

    @Test
    public void testUpdateOrg_Failure() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"failure\"}";
            }
        });

        String result = dm.updateOrg("validOrgId", "newName", "newDescription");
        assertEquals("fail", result);
    }

    @Test
    public void testUpdateOrg_Exception() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                throw new IllegalArgumentException("Some errors occurred.");
            }
        });

        try {
            dm.updateOrg("validOrgId", "newName", "newDescription");
        } catch (IllegalStateException e) {
            assertEquals("Error in communicating with server: Some errors occurred.", e.getMessage());
        }
    }

    @Test
    public void testUpdateOrg_InvalidOrgId() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001));

        String result = dm.updateOrg(null, "newName", "newDescription");
        assertEquals("Invalid Organization ID.", result);

        result = dm.updateOrg("", "newName", "newDescription");
        assertEquals("Invalid Organization ID.", result);
    }

    @Test
    public void testUpdateOrg_InvalidName() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001));

        String result = dm.updateOrg("validOrgId", null, "newDescription");
        assertEquals("Invalid name.", result);

        result = dm.updateOrg("validOrgId", "", "newDescription");
        assertEquals("Invalid name.", result);
    }

    @Test
    public void testUpdateOrg_InvalidDescription() {
        DataManager dm = new DataManager(new WebClient("localhost", 3001));

        String result = dm.updateOrg("validOrgId", "newName", null);
        assertEquals("Invalid description.", result);

        result = dm.updateOrg("validOrgId", "newName", "");
        assertEquals("Invalid description.", result);
    }
}

