/*
Based on:
    https://www.twilio.com/docs/verify
    https://www.twilio.com/docs/verify/quickstart/java-servlets
    https://github.com/TwilioDevEd/account-security-quickstart-servlets/blob/master/src/main/java/com/twilio/accountsecurity/services/PhoneVerificationService.java
Libraries:
    Java Helper Library, download link from: https://www.twilio.com/docs/libraries/java
    Authy and JSON libraries: authy-java-1.2.0.jar and json-20150729.jar
    Download from: https://jar-download.com/explore-java-source-code.php?a=authy-java&g=com.authy&v=1.2.0&downloadable=1
 */
package authyjavasamples;

import com.authy.AuthyApiClient;
import com.authy.api.Params;
import com.authy.api.Verification;
import java.io.DataInputStream;

public class PhoneVerification {

    // -------------------------------------------------------------------------
    private static final String AUTHY_API_KEY = System.getenv("AUTHY_API_KEY");
    private static final String PARAM_VIA = "sms";    // <sms|call>
    private static final String PARAM_COUNTRYCODE = System.getenv("AUTHY_PHONE_COUNTRYCODE");
    private static final String PARAM_PHONENUMBER = System.getenv("AUTHY_PHONE_NUMBER1");
    // -------------------------------------------------------------------------
    private AuthyApiClient authyApiClient = new AuthyApiClient(AUTHY_API_KEY);

    public boolean sendOTP(String countryCode, String phoneNumber, String via) throws Exception {
        Params params = new Params();
        params.setAttribute("code_length", "4");
        Verification verification = authyApiClient.getPhoneVerification().start(
                phoneNumber, countryCode, via, params
        );
        if (!verification.isOk()) {
            logAndThrow("start Error: " + verification.getMessage());
            return false;
        }
        System.out.println("+ start, success: " + verification.getMessage());
        return true;
    }

    public void verify(String countryCode, String phoneNumber, String token) throws Exception {
        Verification verification = authyApiClient.getPhoneVerification().check(
                phoneNumber, countryCode, token
        );
        if (!verification.isOk()) {
            logAndThrow("verify Error: " + verification.getMessage());
            return;
        }
        System.out.println("+ verify, success: " + verification.getMessage());
    }

    public PhoneVerification(AuthyApiClient authyApiClient) {
        this.authyApiClient = authyApiClient;
    }

    private void logAndThrow(String message) {
        System.out.println("- logAndThrow, " + message);
    }

    public static void main(String[] args) {
        System.out.println("+++ Start.");
        System.out.println("");
        System.out.println("+ PhoneVerificationService/main, SID:                     " + System.getenv("ACCOUNT_SID"));
        System.out.println("+ PhoneVerificationService/main, AUTH_TOKEN:              " + System.getenv("AUTH_TOKEN"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_ID:                " + System.getenv("AUTHY_ID"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_ID_EMAIL:          " + System.getenv("AUTHY_ID_EMAIL"));
        System.out.println("+ PhoneVerificationService/main, PHONE_NUMBER1:           " + System.getenv("PHONE_NUMBER1"));
        System.out.println("+ PhoneVerificationService/main, PHONE_NUMBER2:           " + System.getenv("PHONE_NUMBER2"));
        System.out.println("+ PhoneVerificationService/main, AUTHY_API_KEY:           " + AUTHY_API_KEY);
        System.out.println("+ PhoneVerificationService/main, AUTHY_PHONE_COUNTRYCODE: " + PARAM_COUNTRYCODE);
        System.out.println("+ PhoneVerificationService/main, AUTHY_PHONE_NUMBER1:     " + PARAM_PHONENUMBER);
        int a = 1;
        if (a == 1) {
            return;
        }
        PhoneVerification phonVeriService = new PhoneVerification(new AuthyApiClient(AUTHY_API_KEY));
        try {
            System.out.println("++ Send the token to: " + PARAM_PHONENUMBER);
            if (!phonVeriService.sendOTP(PARAM_COUNTRYCODE, PARAM_PHONENUMBER, PARAM_VIA)) {
                System.out.println("- Token not sent, exit this program.");
                return;
            }
            System.out.println("-------------------------");
            String thePrompt = "++ Enter the received token (or exit)> ";
            String paramToken = "";
            try (DataInputStream console = new DataInputStream(System.in)) {
                while (paramToken.length() != 4) {
                    System.out.print(thePrompt);
                    paramToken = console.readLine();
                    if (paramToken.equalsIgnoreCase("exit")) {
                        System.out.println("++ Exit.");
                        return;
                    } else if (paramToken.length() != 4) {
                        System.out.println("+ Must be 4 digits.");
                    }
                }
            }
            System.out.println("+ The received token: " + paramToken);
            phonVeriService.verify(PARAM_COUNTRYCODE, PARAM_PHONENUMBER, paramToken);
        } catch (Exception e) {
            System.out.println("- Exception caught.");
        }
        System.out.println("");
        System.out.println("+++ Exit.");
    }

}
