package ie.darren_sisk.fantasyfootballdraft;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.MalformedJsonException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MyTeamActivity extends AppCompatActivity {


    String url = "http://192.168.1.14";
    String json_string;
    View v;
    ImageButton ls, rs, lm, rm, cm1, cm2, lb, rb, cb1, cb2, gk;
    ImageButton lsCover, rsCover, lmCover, rmCover, cm1Cover, cm2Cover,
                rbCover, lbCover, cb1Cover, cb2Cover, gkCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);

        ls = (ImageButton)findViewById(R.id.leftStrikerButton);
        rs = (ImageButton)findViewById(R.id.rightStrikerButton);
        lm = (ImageButton)findViewById(R.id.leftMidButton);
        rm = (ImageButton)findViewById(R.id.rightMidButton);
        cm1 = (ImageButton)findViewById(R.id.centreMidOneButton);
        cm2 = (ImageButton)findViewById(R.id.centreMidTwoButton);
        rb = (ImageButton)findViewById(R.id.rightBackButton);
        lb = (ImageButton)findViewById(R.id.leftBackButton);
        cb1 = (ImageButton)findViewById(R.id.centreBackOneButton);
        cb2 = (ImageButton)findViewById(R.id.centreBackTwoButton);
        gk = (ImageButton)findViewById(R.id.goalkeeper);

        lsCover = (ImageButton)findViewById(R.id.leftStrikerButtonCover);
        rsCover = (ImageButton)findViewById(R.id.rightStrikerButtonCover);
        lmCover = (ImageButton)findViewById(R.id.leftMidButtonCover);
        rmCover = (ImageButton)findViewById(R.id.rightMidButtonCover);
        cm1Cover = (ImageButton)findViewById(R.id.centreMidOneButtonCover);
        cm2Cover = (ImageButton)findViewById(R.id.centreMidTwoButtonCover);
        rbCover = (ImageButton)findViewById(R.id.rightBackButtonCover);
        lbCover = (ImageButton)findViewById(R.id.leftBackButtonCover);
        cb1Cover = (ImageButton)findViewById(R.id.centreBackOneButtonCover);
        cb2Cover = (ImageButton)findViewById(R.id.centreBackTwoButtonCover);
        gkCover = (ImageButton)findViewById(R.id.goalkeeperCover);


        ls.setVisibility(View.GONE);
        rs.setVisibility(View.GONE);
        lm.setVisibility(View.GONE);
        rm.setVisibility(View.GONE);
        cm1.setVisibility(View.GONE);
        cm2.setVisibility(View.GONE);
        lb.setVisibility(View.GONE);
        rb.setVisibility(View.GONE);
        cb1.setVisibility(View.GONE);
        cb2.setVisibility(View.GONE);
        gk.setVisibility(View.GONE);


    }



    //
    //get Left Striker in app
    //
    public void getLeftStriker(View view)  {

        new LoadLeftStriker().execute();

        lsCover.setVisibility(View.GONE);
        ls.setVisibility(View.VISIBLE);



    }

    //
    //get Right Striker in app
    //
    public void getRightStriker(View view)  {

        new LoadRightStriker().execute();



        rsCover.setVisibility(View.GONE);
        rs.setVisibility(View.VISIBLE);



    }

    //
    //get Left Mid in app
    //
    public void getLeftMid(View view)  {

        new LoadLeftMid().execute();

        lmCover.setVisibility(View.GONE);
        lm.setVisibility(View.VISIBLE);
    }


    //
    //get Right Mid in app
    //
    public void getRightMid(View view)  {

        new LoadRightMid().execute();

        rmCover.setVisibility(View.GONE);
        rm.setVisibility(View.VISIBLE);
    }



    //
    //get First Centre Mid in app
    //
    public void getCentreMidOne(View view)  {

        new LoadCentreMidOne().execute();

        cm1Cover.setVisibility(View.GONE);
        cm1.setVisibility(View.VISIBLE);
    }

    //
    //get Second Centre Mid in app
    //
    public void getCentreMidTwo(View view)  {

        new LoadCentreMidTwo().execute();

        cm2Cover.setVisibility(View.GONE);
        cm2.setVisibility(View.VISIBLE);
    }


    //
    //get Left Back in app
    //
    public void getLeftBack(View view)  {

        new LoadLeftBack().execute();

        lbCover.setVisibility(View.GONE);
        lb.setVisibility(View.VISIBLE);
    }


    //
    //get right Back in app
    //
    public void getRightBack(View view)  {

        new LoadRightBack().execute();

        rbCover.setVisibility(View.GONE);
        rb.setVisibility(View.VISIBLE);
    }


    //
    //get Centre Back One in app
    //
    public void getCentreBackOne(View view)  {

        new LoadCentreBackOne().execute();

        cb1Cover.setVisibility(View.GONE);
        cb1.setVisibility(View.VISIBLE);
    }

    //
    //get Centre Back Two in app
    //
    public void getCentreBackTwo(View view)  {

        new LoadCentreBackTwo().execute();

        cb2Cover.setVisibility(View.GONE);
        cb2.setVisibility(View.VISIBLE);
    }


    //
    //get Goalkeeper in app
    //
    public void getGoalkeeper(View view)  {

        new LoadGoalkeeper().execute();

        gkCover.setVisibility(View.GONE);
        gk.setVisibility(View.VISIBLE);
    }













    //
    //Run php script for Left Striker
    //
    class LoadLeftStriker extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getLeftStriker.php";
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
            textview.setText("");
            json_string = result;

        }
    }


    //
    //Run php script for Right Striker
    //
    class LoadRightStriker extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getRightStriker.php";
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
            textview.setText("");
            json_string = result;

        }
    }









    //
    //Run php script for Left Mid
    //
    class LoadLeftMid extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getLeftMid.php";
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
            textview.setText("");
            json_string = result;

        }
    }




    //
    //Run php script for Right Mid
    //
    class LoadRightMid extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getRightMid.php";
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
            textview.setText("");
            json_string = result;

        }
    }



    //
    //Run php script for First Centre Mid
    //
    class LoadCentreMidOne extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getCentreMidOne.php";
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
            textview.setText("");
            json_string = result;

        }
    }


    //
    //Run php script for Second Centre Mid
    //
    class LoadCentreMidTwo extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getCentreMidTwo.php";
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
            textview.setText("");
            json_string = result;

        }
    }



    //
    //Run php script for Left Back
    //
    class LoadLeftBack extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getLeftBack.php";
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
            textview.setText("");
            json_string = result;

        }
    }


    //
    //Run php script for Right Back
    //
    class LoadRightBack extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getRightBack.php";
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
            textview.setText("");
            json_string = result;

        }
    }


    //
    //Run php script for Centre Back One
    //
    class LoadCentreBackOne extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getCentreBackOne.php";
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
            textview.setText("");
            json_string = result;

        }
    }


    //
    //Run php script for Second Centre Back
    //
    class LoadCentreBackTwo extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getCentreBackTwo.php";
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
            textview.setText("");
            json_string = result;

        }
    }






    //
    //Run php script for Goalkeeper
    //
    class LoadGoalkeeper extends AsyncTask<Void,Void,String>
    {

        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {

            json_url = url+"/getGoalkeeper.php";
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
            textview.setText("");
            json_string = result;

        }
    }










    //display in listview
    public void parseJSON(View view)
    {

        if(json_string==null){
            Toast.makeText(getApplicationContext(), "First get JSON Data", Toast.LENGTH_LONG).show();

        }
        else{
            Intent intent = new Intent(this, PickPlayerActivity.class);
            intent.putExtra("json_data", json_string);
            startActivity(intent);

        }


    }



}
