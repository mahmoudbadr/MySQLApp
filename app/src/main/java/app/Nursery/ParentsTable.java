package app.Nursery;

/**
 * Created by Mahmoud on 10/24/2015.
 */
public class ParentsTable {

    String Child_id;

    public ParentsTable(String Child_id) {
        super();

        this.Child_id = Child_id;
    }

    public ParentsTable() {
        super();

        this.Child_id = null;


    }



    public String getChild_id() {
        return Child_id;
    }

   /* public void setUserid(String User_id) {
        this.User_id = User_id;
    }
*/


}

