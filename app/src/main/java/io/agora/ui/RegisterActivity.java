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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.terms).setOnClickListener(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                // INITIATE REGISTER
                final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Registering");
                pd.show();
                APIManager.getInstance().registerUser(this,
                        name.getText().toString(), email.getText().toString(),
                        password.getText().toString(), false, new APIManagerCallback() {
                            @Override
                            public void onSuccess(Object object) {
                                Toast.makeText(RegisterActivity.this,
                                        "Registration successful", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                                finish();
                            }

                            @Override
                            public void onFailure(String status) {
                                pd.dismiss();
                                Toast.makeText(RegisterActivity.this,
                                        "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        });
                break;
            case R.id.login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.terms:
                Toast.makeText(RegisterActivity.this, "Terms & Conditions coming soon", Toast.LENGTH_LONG);
                break;
        }
    }
}
