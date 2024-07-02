Project Phase 3 Write-up
Q1.

Tasks that we would like graded for this phase are 3.1, 3.2, 3.3, 3.4, 3.6

Q2.

Task 3.1

The main and start method in UserInterface was changed. If no command-line arguments are provided, the start method will give the user the option to register or log in. For registration, the user's input will get a strict non-blank validation and a duplicate validation for the login name.

The createOrganization method in DataManger was added. This method is used to send a request to the server to create the organization.

In addition to the client side, a corresponding /createOrg http interface has been added to the server side (api.js). This is a reference to the admin.js interface of the same name, but returns json instead of html.


Task 3.2

A new API called updateOrgPassword has been created in api.js, this allows users to change their organization's password and update the password data in mongodb. A new method called updateOrgsPassword was created in datamanager to call the updateOrgPassword API to update the password data. Finally, a new method called updateOrgPassword is created in the userinterface to guide the user to change their password and call updateOrgsPassword in datamanager to complete the data update.

Task 3.3
Created a new API in api.js called updateOrg, which allows users to change the name and description of their organization and update this data in Mongodb. In datamanager, a new method called updateOrg is created to call the updateOrg API to update the name and description. Finally, a new method called updateOrg is created in the userinterface to direct the user to change their name and description and call updateOrg in the datamanager to update the data. Also optimized the interaction logic with the user in the userinterface to make the overall operation more logical.
Task 3.4
Created "makeDonation" method in "DataManager" class that allows making a donation to a specified fund on behalf of a contributor. It includes defensive checks to ensure that the inputs are not null or invalid and throws exceptions if any required parameter is invalid. Additionally, comprehensive JUnit tests were created to validate various scenarios, such as successful donations, invalid inputs, and exception handling. The user interface was also updated to include fields for entering onBehalfOfContributorId when making a donation, ensuring that error messages are displayed appropriately for invalid inputs. If the user successfully makes a donation, then there will be the listing of all the donations for that fund showing.
Task3.6
Modified MenuActivity.java to add a new button called “change password” at the MenuActivity screen. Accordingly, a new button is added to the activity_menu.xml in order to display the button. A new java class ChangePasswordActivity.java and its layout xml “activity_change_password.xml are added. Once the user clicks on the “change password” button, they will be directed to another page, which will prompt them to verify their old password. if it is correctly entered, the app will prompt them to enter the new password twice; 
if the two entries match, the user’s ID and new password should be sent to the server using the RESTful API (api.js) so that it can update the contributor’s password; 
if the user incorrectly enters the current password, or if the two entries for the new password do not match, the user should see an appropriate error message and then go back to the screen where they had the option to change the password. 

There is also a change to the api.js. A new api called /updateContributorPassword is added to api.js, which allows users to change their organization's password and update the password data in mongodb.

A new method called “updatePassword” is added to the dataManager, which will call the /updateContributorPassword api. This method will be called in ChangePasswordActivity.java to modify the password for the contributor. A new test file is added to the androidTest folder that is used to test the newly added method in dataManager.

Lastly, the contributor.java is modified. A new getter and setter method for password are added so that we can use it to verify the old password in the changePasswordActivity.java.


Q3.

Q4.

The userinterface section modifies the way to get the login and password in the main function. Previously, these two variables were retrieved from command line arguments, and they did not change while the program was running. In order to allow the program to automatically use the new password after updating it, instead of requiring the user to provide the new password as a command line argument each time. To solve this problem, user need to manually enter their login and password after starting the run main function.



Q5.
Jiahao Guo: Task 3.2 & Task 3.3
Leyi Jiang: Task 3.1
Rachel Feng: Task 3.4
Haoyu Zhao: Task 3.6
