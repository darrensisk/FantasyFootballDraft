package ie.darren_sisk.fantasyfootballdraft;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.MalformedJsonException;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTeamActivity extends AppCompatActivity {

    String JSON_STRING;
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
    }


    public void getJSON(View view){

        new BackgroundTask().execute();

    }


    class BackgroundTask extends AsyncTask<Void,Void,String>
    {

        String json_url;

        @Override
        protected void onPreExecute() {


            json_url = "http://192.168.8.110/phptest";

        }

        @Override
        protected String doInBackground(Void... voids){

            try{

                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();


                while((JSON_STRING = bufferedReader.readLine())!=null)
                {

                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedJsonException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String result) {
            TextView textview = (TextView) findViewById(R.id.textview);
            textview.setText(result);
            json_string = result;

        }
    }


    public void parseJSON(View view)
    {

    }



}
