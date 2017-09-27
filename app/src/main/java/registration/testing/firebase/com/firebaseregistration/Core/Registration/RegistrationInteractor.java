package registration.testing.firebase.com.firebaseregistration.Core.Registration;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ashish on 27-09-2017.
 */

public class RegistrationInteractor implements RegistrationContract.Intractor {

    private static final String TAG = RegistrationInteractor.class.getSimpleName();
    private RegistrationContract.onRegistrationListener mOnRegistrationListener;

    public RegistrationInteractor(RegistrationContract.onRegistrationListener onRegistrationListener){
        this.mOnRegistrationListener = onRegistrationListener;
    }
    @Override
    public void performFirebaseRegistration(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            mOnRegistrationListener.onFailure(task.getException().getMessage());
                        }else{
                            mOnRegistrationListener.onSuccess(task.getResult().getUser());
                        }
                    }
                });
    }
}
