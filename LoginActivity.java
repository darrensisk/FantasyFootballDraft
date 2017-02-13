package ie.darren_sisk.fantasyfootballdraft;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    TextView textview;
    Button login_button;
    EditText UserName, Password;
    String username, password;
    String login_url = "http://192.168.8.110/login.php";
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textview = (TextView)findViewById(R.id.tvRegister);
        textview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        builder = new AlertDialog.Builder(LoginActivity.this);
        login_button = (Button)findViewById(R.id.bnLogin);
        UserName = (EditText)findViewById(R.id.login_name);
        Password = (EditText)findViewById(R.id.login_password);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = UserName.getText().toString();
                password = Password.getText().toString();

                if(username.equals("")||password.equals(""))
                {
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter a valid username and password");
                }
                else
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try{

                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        if(code.equals("login_failed")){
                                            builder.setTitle("Login error");
                                            displayAlert(jsonObject.getString("message"));
                                        }
                                        else
                                        {
                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                                            // Error is happening here
                                            Bundle bundle = new Bundle();
                                            bundle.putString("username", jsonObject.getString("username"));
                                            bundle.putString("email", jsonObject.getString("email"));
                                            intent.putExtras(bundle);



                                            startActivity(intent);
                                        }


                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                            error.printStackTrace();

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            return params;

                        }
                    };

                    MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);

                }


            }
        });


    }




    public void displayAlert(String message){


        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog , int which){
                UserName.setText("");
                Password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


}


