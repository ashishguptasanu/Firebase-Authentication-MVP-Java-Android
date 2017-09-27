package registration.testing.firebase.com.firebaseregistration.Core.Login;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ashish on 27-09-2017.
 */

public class LoginInteractor implements LoginContract.Intractor {

    private LoginContract.onLoginListener mOnLoginListener;

    public LoginInteractor(LoginContract.onLoginListener onLoginListener){
        this.mOnLoginListener = onLoginListener;
    }
    @Override
    public void performFirebaseLogin(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mOnLoginListener.onSuccess(task.getResult().toString());
                        }
                        else {
                            mOnLoginListener.onFailure(task.getException().toString());
                        }
                    }
                });
    }
}
