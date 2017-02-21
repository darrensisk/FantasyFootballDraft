package ie.darren_sisk.fantasyfootballdraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darren_Sisk on 14/02/2017.
 */
public class PlayerAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public PlayerAdapter(Context context, int resource){
        super(context, resource);

    }


    public void add(Players object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        PlayerHolder playerHolder;
        if (row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            playerHolder = new PlayerHolder();
            playerHolder.tx_firstname = (TextView) row.findViewById(R.id.tx_firstname);
            playerHolder.tx_lastname = (TextView) row.findViewById(R.id.tx_lastname);
            playerHolder.tx_position = (TextView) row.findViewById(R.id.tx_position);
            row.setTag(playerHolder);

        }
        else{
            playerHolder = (PlayerHolder) row.getTag();


        }
        Players players = (Players) this.getItem(position);
        playerHolder.tx_firstname.setText(players.getFirstname());
        playerHolder.tx_lastname.setText(players.getLastname());
        playerHolder.tx_position.setText(players.getPosition());

        return row;
    }


    class PlayerHolder{

        TextView tx_firstname, tx_lastname, tx_position;

    }

}
