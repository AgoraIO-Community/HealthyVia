package io.agora.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.agora.largegroupcall.R;
import io.agora.ui.callback.APIManagerCallback;
import io.agora.ui.manager.APIManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.terms).setOnClickListener(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                // INITIATE LOGIN
                final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("Registering");
                pd.show();
                APIManager.getInstance().loginUser(this,
                        email.getText().toString(),
                        password.getText().toString(), new APIManagerCallback() {
                            @Override
                            public void onSuccess(Object object) {
                                Toast.makeText(LoginActivity.this,
                                        "Registration successful", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();
                            }

                            @Override
                            public void onFailure(String status) {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this,
                                        "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        });
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
            case R.id.terms:
                Toast.makeText(LoginActivity.this, "Terms & Conditions coming soon", Toast.LENGTH_LONG);
                break;
        }
    }
}
