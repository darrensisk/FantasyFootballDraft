package ie.darren_sisk.fantasyfootballdraft;

/**
 * Created by Darren_Sisk on 14/02/2017.
 */
public class Players {

    private String position, firstname, lastname;

    public Players(String firstname, String lastname, String position){

        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPosition(position);

    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
