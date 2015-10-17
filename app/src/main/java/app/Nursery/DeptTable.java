package app.Nursery;

public class DeptTable {

	int parent_id;
	String name;
	
	public DeptTable(int parent_id, String name) {
		super();
		this.parent_id = parent_id;
		this.name = name;
	}
	
	public DeptTable() {
		super();
		this.parent_id=0;
		this.name = null;
	}
	
	
	public int getid() {
		return parent_id;
	}
	public void setid(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

}
