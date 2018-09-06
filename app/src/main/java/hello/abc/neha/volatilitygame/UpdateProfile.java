package hello.abc.neha.volatilitygame;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

public class UpdateProfile extends AppCompatActivity {


    private TextInputEditText editTextName1;
    private TextInputEditText editTextEmail1;
    private TextInputEditText editTextPassword1;
    private TextInputEditText editTextUserType1;
    private TextInputEditText editTextMobile1;
    private TextInputEditText editTextCity1;
    private TextInputEditText editTextOrg1;
    private TextInputEditText editTextAddress1;

    User user = SharedPrefManager.getInstance(this).getUser();

    private AppCompatButton appCompatButtonUpdate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        String data1 = getIntent().getExtras().getString("email");
        String data2 = getIntent().getExtras().getString("name");
        String data3 = getIntent().getExtras().getString("address");
        String data4 = getIntent().getExtras().getString("mobile");
        String data5 = getIntent().getExtras().getString("city");
        String data7 = getIntent().getExtras().getString("org");
        String data8 = getIntent().getExtras().getString("usertype");


        editTextName1 = (TextInputEditText) findViewById(R.id.textInputEditTextName1);
        editTextName1.setText(data2);
        editTextEmail1 = (TextInputEditText) findViewById(R.id.textInputEditTextEmail1);
        editTextEmail1.setText(data1);
        editTextEmail1.setEnabled(false);
        editTextAddress1 = (TextInputEditText) findViewById(R.id.textInputEditTextAdd1);
        editTextAddress1.setText(data3);
        editTextPassword1 = (TextInputEditText) findViewById(R.id.textInputEditTextPassword1);
        editTextPassword1.setText("");
        editTextOrg1 = (TextInputEditText) findViewById(R.id.textInputEditTextOrg1);
        editTextOrg1.setText(data7);
        editTextCity1 = (TextInputEditText) findViewById(R.id.textInputEditTextCity1);
        editTextCity1.setText(data5);
        editTextMobile1 = (TextInputEditText) findViewById(R.id.textInputEditTextMobile1);
        editTextMobile1.setText(data4);
        editTextUserType1 = (TextInputEditText) findViewById(R.id.textInputEditTextUserType1);
        editTextUserType1.setText(data8);
        appCompatButtonUpdate=(AppCompatButton) findViewById(R.id.appCompatButtonUpdate);


        appCompatButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatefinal();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(UpdateProfile.this, AdvisorIndian.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(UpdateProfile.this, AdvisorIndian.class);
        startActivity(i);
    }


    private void updatefinal() {

        final String name = editTextName1.getText().toString();
        final String email = editTextEmail1.getText().toString();
        final String password = editTextPassword1.getText().toString();
        final String mobile = editTextMobile1.getText().toString();
        final String city = editTextCity1.getText().toString();
        final String address = editTextAddress1.getText().toString();
        final String user_type = editTextUserType1.getText().toString();
        final String org = editTextOrg1.getText().toString();

        class Update extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject obj = new JSONObject(s);
                    if (obj.getBoolean("updated")) {
                        Toast.makeText(getApplicationContext(), "Successfully updated details", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), AdvisorIndian.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error in updating details", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(user.getId()));
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("mobile", mobile);
                params.put("city", city);
                params.put("name_of_organisation", org);
                params.put("address", address);


                //returing the response
                return requestHandler.sendPostRequest(AppConfig.URL_UPDATE_WRITE, params);
            }
        }

        Update ul = new Update();
        ul.execute();
    }
}

