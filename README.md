# Sample Twilio Account Security Java Programs

Java programs to send requests to the Twilio Account Security APIs: 2FA and Phone Verification.

    To get started, start with Phone Verification.
    Create a Twilio account, a free Trial works fine (https://twilio.com/console).
    In the Authy section of the Twilio Console, follow the Get Started steps to make your account 2FA enabled, and enter an application (Friendly name) to create an API key.
    In your environment, set your Authy required variables.
    Run AuthyJavaSamples.java to echo the environment variables that are used by Authy Java sample programs.

Note, to set the environment variables for NetBeans, I have a shell script that sets the variables then starts NetBeans.
This insures the the environment variables are available in NetBeans.

````
$ cat ~/nb.sh 
export ACCOUNT_SID=your_account_sid
export AUTH_TOKEN=your_account_auth_token
export AUTHY_ID=sample_user_authy_id (example: 30012357
export AUTHY_ID_EMAIL=sample_user_email_address
export AUTHY_PHONE_COUNTRYCODE=+1
export AUTHY_PHONE_NUMBER1=2223331234
export PHONE_NUMBER1=+12223331111
export PHONE_NUMBER2=+12223332222
"/Applications/NetBeans/NetBeans 8.2.app/Contents/Resources/NetBeans/bin/netbeans" &
````

You can check the program before running it, to know which variables are required, for which program.

## Authy Java Programs That Use the Authy Helper Library

PhoneVerification.java:
Set PARAM_COUNTRYCODE and PARAM_PHONENUMBER to your mobile phone number.
Run PhoneVerification.java, which sends a Phone Verification OTP.
The phone receives the OTP.
In PhoneVerification.java, you are prompted to enter the passcode.
Enter the OTP. A request is sent to verify the entered passcode.
PhoneVerification.java, receives and displays the result: valid or not.

SendMsg1.java is a sample program to send an SMS message for testing.

## Authy Java Programs Without Using the Authy Helper Library

Authy.java:
Set API_KEY to your Twilio Console Authy Application API key.
This is a class of methods to make requests to the Twilio Account Security API.

AuthyPhoneVerification.java:
Set PARAM_COUNTRYCODE and PARAM_PHONENUMBER to your mobile phone number.
Sends a request to the Phone Verification API.
Receives and displays the response.
Prompt and wait for the user to enter the passcode that was sent by the Twilio service to the person's phone.
The Twilio service receives the request, generates a passcode and sends it to the person's phone. 
After the passcode is entered into this program's prompt, a request is sent to verify the entered passcode.
Receives and displays the response. The response confirms the validity of the passcode.

AuthyPhoneVerificationCheck.java:
(Second part of AuthyPhoneVerification.java)
Prompt and wait for the user to enter the passcode that was sent by the Twilio service to the person's phone.
After the passcode is entered into this program's prompt, a request is sent to verify the entered passcode.
Receives and displays the response. The response confirms the validity of the passcode.

AuthyUserAdd.java:
Send a request to the Twilio Account Security API, to have a 2FA user added.

AuthyUserRemove.java:
Send a request to the Twilio Account Security API, to have a 2FA user removed.

AuthyUserStatus.java:
Send a request to the Twilio Account Security API, to get the status of a 2FA user.

AuthyPushAuth.java:
Send a Push Authentication notification request to a 2FA user, using the Twilio Account Security API.

AuthyPushCheck.java:
Send a request to check the status of a Push Authentication notification.

AuthyToken.java:
Send a request to the Twilio Account Security API, to have a token sent to a 2FA user's phone number.
Prompt for the token that was sent.
The Twilio Account Security service generates and sends a token to a 2FA user's phone.
After the token is entered into the prompt, this program send a request to verify the entered token.
The response confirms the validity of the token.

AuthyTokenCheck.java:
(Second part of AuthyToken.java)
Prompt for the token that was sent.
After the token is entered into the prompt, this program send a request to verify the entered token.
The response confirms the validity of the token.

TwLookup.java:
Send a request to get information about a phone number, any phone number in the world.

TwSendSmsMsg.java:
Send an SMS to a phone number, any phone number in the world.

TwCalls.java:
Set ACCOUNT_SID and AUTH_TOKEN to your Twilio Console SID and token.
This is a class of methods to make requests to Twilio Lookup and other APIs.

HttpCalls.java:
To send HTTP POST and GET requests.
Return the response.

PrintJson.java:
To pretty print JSON responses from the Twilio services.

PrintXml.java:
To pretty print XML responses from the Twilio services.
