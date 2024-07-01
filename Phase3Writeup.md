Task 3.1

The `main` and `start` method in `UserInterface` was changed. If no command-line arguments are provided, the `start` method will give the user the option to register or log in. For registration, the user's input will get a strict non-blank validation and a duplicate validation for the login name.

The `createOrganization` method in `DataManger` was added. This method is used to send a request to the server to create the organization.

In addition to the client side, a corresponding `/createOrg` http interface has been added to the server side (api.js). This is a reference to the admin.js interface of the same name, but returns json instead of html.