package registration.testing.firebase.com.firebaseregistration.Core.Registration;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ashish on 27-09-2017.
 */

public class RegistrationPresenter implements RegistrationContract.Presenter, RegistrationContract.onRegistrationListener {
    private RegistrationContract.View mRegisterView;
    private RegistrationInteractor mRegistrationInteractor;

    public RegistrationPresenter(RegistrationContract.View registerView){
        this.mRegisterView = registerView;
        mRegistrationInteractor = new RegistrationInteractor(this);
    }
    @Override
    public void register(Activity activity, String email, String password) {
        mRegistrationInteractor.performFirebaseRegistration(activity,email,password);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        mRegisterView.onRegistrationSuccess(firebaseUser);
    }

    @Override
    public void onFailure(String message) {
        mRegisterView.onRegistrationFailure(message);

    }
}
