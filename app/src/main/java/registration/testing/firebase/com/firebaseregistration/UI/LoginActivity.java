package registration.testing.firebase.com.firebaseregistration.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import registration.testing.firebase.com.firebaseregistration.Core.Login.LoginContract;
import registration.testing.firebase.com.firebaseregistration.Core.Login.LoginPresenter;
import registration.testing.firebase.com.firebaseregistration.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View{
    Button btnLogin;
    TextView tvRegister;
    EditText edtEmail, edtPassword;
    private LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

    }

    private void initViews() {
        btnLogin = (Button)findViewById(R.id.button_login);
        btnLogin.setOnClickListener(this);
        tvRegister = (TextView)findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        edtEmail = (EditText)findViewById(R.id.email_login);
        edtPassword = (EditText)findViewById(R.id.password_login);

        mLoginPresenter = new LoginPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                checkLoginDetails();
                break;
            case R.id.tv_register:
                moveToRegisterActivity();
                break;
        }
    }

    private void moveToRegisterActivity() {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    private void checkLoginDetails() {
        if(!TextUtils.isEmpty(edtEmail.getText().toString()) && !TextUtils.isEmpty(edtPassword.getText().toString())){
            initLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
        }else{
            if(TextUtils.isEmpty(edtEmail.getText().toString())){
                edtEmail.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(edtPassword.getText().toString())){
                edtPassword.setError("Please enter password");
            }
        }
    }

    private void initLogin(String email, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(this, email, password);
    }

    @Override
    public void onLoginSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Logged in" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
    }
}
