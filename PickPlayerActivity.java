package ie.darren_sisk.fantasyfootballdraft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PickPlayerActivity extends AppCompatActivity {


    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    PlayerAdapter playerAdapter;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_player);
        listview = (ListView) findViewById(R.id.listview);
        playerAdapter = new PlayerAdapter(this, R.layout.row_layout);
        listview.setAdapter(playerAdapter);
        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String firstname, surname, position;

            while(count<jsonArray.length()){

                JSONObject jo = jsonArray.getJSONObject(count);

                firstname = jo.getString("firstname");
                surname = jo.getString("surname");
                position = jo.getString("position");


                Players players = new Players(firstname, surname, position);

                playerAdapter.add(players);
                count ++;


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
