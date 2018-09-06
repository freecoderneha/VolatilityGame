package hello.abc.neha.volatilitygame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class InvestorLogin extends AppCompatActivity {

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextMobile;
    private TextInputEditText textInputEditTextCity;
    private RadioGroup radioGroupIg;

    private AppCompatButton appCompatButtonRegister;

    private User user;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investor_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextMobile = (TextInputEditText) findViewById(R.id.textInputEditTextMobile);
        textInputEditTextCity = (TextInputEditText) findViewById(R.id.textInputEditTextCity);

        radioGroupIg=(RadioGroup) findViewById(R.id.radioIg);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });

    }

    private void registerUser() {
        final String name = textInputEditTextName.getText().toString().trim();
        final String email = textInputEditTextEmail.getText().toString().trim();
        final String password = textInputEditTextPassword.getText().toString().trim();
        final String mobile = textInputEditTextMobile.getText().toString().trim();
        final String indianglobal = ((RadioButton) findViewById(radioGroupIg.getCheckedRadioButtonId())).getText().toString();
        final String city = textInputEditTextCity.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            textInputEditTextName.setError("Please enter username");
            textInputEditTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            textInputEditTextEmail.setError("Please enter your email");
            textInputEditTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputEditTextEmail.setError("Enter a valid email");
            textInputEditTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            textInputEditTextPassword.setError("Enter a password");
            textInputEditTextPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(city)) {
            textInputEditTextCity.setError("Enter city");
            textInputEditTextCity.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mobile)) {
            textInputEditTextMobile.setError("Enter mobile number");
            textInputEditTextMobile.requestFocus();
            return;
        }

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("mobile", mobile);
                params.put("city", city);
                params.put("indianglobal", indianglobal);

                Log.d("params", String.valueOf(params));

               return requestHandler.sendPostRequest(AppConfig.URL_REGISTER, params);
            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);
                final String TAG = "myApp";
                Log.d(TAG,s);
                try {
                    JSONObject obj = new JSONObject(s);
                        if (obj.getInt("error")==3) {
                        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                        User user = new User(
                                obj.getString("name"),
                                obj.getString("email"),
                                obj.getString("password"),
                                obj.getString("mobile"),
                                obj.getString("city"),
                                obj.getString("indianglobal")
                        );

                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        Intent i = new Intent(InvestorLogin.this,AdvisorLogin.class);
                        startActivity(i);

                        }

                        else if(obj.getInt("error")==4){
                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();

                    }
                    else if(obj.getInt("error")==2){
                        Toast.makeText(getApplicationContext(), "Indian Global error", Toast.LENGTH_SHORT).show();
                    }
                     else if(obj.getInt("error")==5) {
                        Toast.makeText(getApplicationContext(), "Some internal error", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
            RegisterUser ru = new RegisterUser();
            ru.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i=new Intent(InvestorLogin.this,MainActivity.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(InvestorLogin.this, MainActivity.class);
        startActivity(i);
    }
}
