package registration.testing.firebase.com.firebaseregistration.Core.Registration;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ashish on 27-09-2017.
 */

public interface RegistrationContract {
    interface View{
        void onRegistrationSuccess(FirebaseUser firebaseUser);
        void onRegistrationFailure(String message);
    }

    interface Presenter{
        void register(Activity activity, String email, String password);
    }

    interface Intractor{
        void performFirebaseRegistration(Activity activity, String email, String password);
    }

    interface onRegistrationListener{
        void onSuccess(FirebaseUser firebaseUser);
        void onFailure(String message);
    }
}
