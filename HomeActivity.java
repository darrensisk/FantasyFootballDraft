package ie.darren_sisk.fantasyfootballdraft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView name, playerName;
    Button myTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


         name = (TextView) findViewById(R.id.name);

         Bundle bundle = getIntent().getExtras();
         name.setText(bundle.getString("username"));


        myTeam = (Button)findViewById(R.id.myTeamButton);
        myTeam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                startActivity(new Intent(HomeActivity.this, MyTeamActivity.class));
            }
        });

    }
}
