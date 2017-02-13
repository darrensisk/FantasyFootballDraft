package ie.darren_sisk.fantasyfootballdraft;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    AlertDialog.Builder builder;
    String reg_url = "http://192.168.8.110/register.php";
    String name, email, username, password, confirmpassword;
    EditText regName, regEmail, regPassword, regUsername, regConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        regEmail = (EditText) findViewById(R.id.regEmail);
        regName = (EditText) findViewById(R.id.regName);
        regUsername = (EditText) findViewById(R.id.regUsername);
        regPassword = (EditText) findViewById(R.id.regPassword);
        regConfirmPassword = (EditText) findViewById(R.id.regConfimPassword);

        Button bnRegister = (Button) findViewById(R.id.bnRegister);

        builder = new AlertDialog.Builder(RegisterActivity.this);

        bnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                name = regName.getText().toString();
                email = regEmail.getText().toString();
                username = regUsername.getText().toString();
                password = regPassword.getText().toString();
                confirmpassword = regConfirmPassword.getText().toString();

                if (name.equals("")||email.equals("")||username.equals("")||password.equals("")||confirmpassword.equals("")){

                    builder.setTitle("Oops..");
                    builder.setMessage("Please fill all the fields");
                    displayAlert("input_error");
                }

                else{


                    if(!(password.equals(confirmpassword))){


                        builder.setTitle("Oops...");
                        builder.setMessage("You're passwords are not matching");
                        displayAlert("input_error");

                    }
                    else
                    {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONArray jsonArray = new JSONArray(response);
                                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                                            String code = jsonObject.getString("code");
                                            String message = jsonObject.getString("message");
                                            builder.setTitle("Server Response...");
                                            builder.setMessage(message);
                                            displayAlert(code);

                                        } catch(JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("name", name);
                                params.put("email", email);
                                params.put("username", username);
                                params.put("password", password);

                                return params;
                            }
                        };
                        MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);


                    }


                }
            }
        });
    }


    public void displayAlert(final String code){

    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which){

            if(code.equals("input_error")){

                regPassword.setText("");
                regConfirmPassword.setText("");

            }
            else if (code.equals("reg_success"))
            {

                finish();

            }
            else if (code.equals("reg_failed"))
            {
                regName.setText("");
                regPassword.setText("");
                regEmail.setText("");
                regUsername.setText("");
                regConfirmPassword.setText("");


            }
        }

    });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }





}








