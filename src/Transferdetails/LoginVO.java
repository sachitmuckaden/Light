package Transferdetails;
/* Transfer object for login action */ 

public class LoginVO 
{
	private String status;
	private int userId;
	private String name;
	
	
	
	public LoginVO() 
	{
		super();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}