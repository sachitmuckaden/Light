package Transferdetails;

public class SignUpVO {
	private String nickname;
	private String username;
	private String password;
	private String confirmpassword;
	
	public SignUpVO()
	{
		super();
	}
	
	public String getName() {
		return nickname;
	}

	public void setName(String name) {
		this.nickname = name;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmPassword) {
		this.confirmpassword = confirmPassword;
	}
	
	
}