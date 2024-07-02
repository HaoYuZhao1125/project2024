package edu.upenn.cis573.project;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;



public class AndroidDataManagerUpdatePasswordTest {
    private DataManager dm;
    @Test(expected=IllegalStateException.class)
    public void testUpdatePassword_WebClientIsNull() {

        dm = new DataManager(null);
        dm.updatePassword("hy", "123");
        Assert.fail("DataManager.updatePassword does not throw IllegalStateException when WebClient is null");

    }

    @Test(expected=IllegalArgumentException.class)
    public void testUpdatePassword_LoginIsNull() {

        dm = new DataManager(new WebClient("10.0.2.2", 3001));
        dm.updatePassword(null, "password");
        Assert.fail("DataManager.updatePassword does not throw IllegalArgumentxception when contributorId is null");

    }

    @Test(expected=IllegalArgumentException.class)
    public void testUpdatePassword_PasswordIsNull() {

        dm = new DataManager(new WebClient("10.0.2.2", 3001));
        dm.updatePassword("11111", null);
        Assert.fail("DataManager.updatePassword does not throw IllegalArgumentxception when password is null");

    }

    @Test(expected=IllegalStateException.class)
    public void testUpdatePassword_WebClientCannotConnectToServer() {

        // this assumes no server is running on port 3002
        dm = new DataManager(new WebClient("10.0.2.2", 3002));
        dm.updatePassword("login", "password");
        Assert.fail("DataManager.updatePassword does not throw IllegalStateException when WebClient cannot connect to server");

    }

    @Test(expected=IllegalStateException.class)
    public void testUpdatePassword_WebClientReturnsNull() {

        dm = new DataManager(new WebClient("10.0.2.2", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return null;
            }
        });
        dm.updatePassword("login", "password");
        Assert.fail("DataManager.updatePassword does not throw IllegalStateException when WebClient returns null");

    }

    @Test
    public void testUpdatePassword_EmptyContributorId() {
        dm = new DataManager(new WebClient("10.0.2.2", 3001));
        Assert.assertFalse(dm.updatePassword("", "password"));
    }

    @Test
    public void testUpdatePassword_EmptyPassword() {
        dm = new DataManager(new WebClient("10.0.2.2", 3001));
        Assert.assertFalse(dm.updatePassword("222", ""));
    }

    @Test
    public void testUpdatePassword_success() {
        dm = new DataManager(new WebClient("10.0.2.2", 3001));
        Assert.assertTrue(dm.updatePassword("665f748781f3a2f976687919", "12345"));
    }


    @Test(expected=IllegalStateException.class)
    public void testUpdatePassword_WebClientReturnsError() {

        dm = new DataManager(new WebClient("10.0.2.2", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "{\"status\":\"error\",\"error\":\"An unexpected database error occurred\"}";
            }
        });
        dm.updatePassword("login", "password");
        Assert.fail("DataManager.updatePassword does not throw IllegalStateException when WebClient returns error");

    }

    @Test(expected=IllegalStateException.class)
    public void testUpdatePassword_WebClientReturnsMalformedJSON() {

        dm = new DataManager(new WebClient("10.0.2.2", 3001) {
            @Override
            public String makeRequest(String resource, Map<String, Object> queryParams) {
                return "I AM NOT JSON!";
            }
        });
        dm.updatePassword("login", "password");
        Assert.fail("DataManager.updatePassword does not throw IllegalStateException when WebClient returns malformed JSON");

    }

}
