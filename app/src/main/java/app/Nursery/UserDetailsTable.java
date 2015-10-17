package app.Nursery;

public class UserDetailsTable {

	String User_id,password,user_type_id;

	public UserDetailsTable(String User_id,
			String password, String user_type_id) {
		super();

		this.User_id = User_id;
		this.password = password;
		this.user_type_id = user_type_id;
	}

	public UserDetailsTable() {
		super();

		this.User_id = null;
		this.password = null;
		this.user_type_id = null;
	
	}



	public String getUserid() {
		return User_id;
	}

	public void setUserid(String User_id) {
		this.User_id = User_id;
	}



	public String getUsertypeid() {
		return user_type_id;
	}

	public void setUsertypeid(String User_type_id) {
		this.user_type_id = User_type_id;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
