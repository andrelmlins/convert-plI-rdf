package Predicados;

public class Dependency {
	public String dep;
	public String token;
	
	public Dependency(String dep, String token) {
		this.dep = dep;
		this.token = token;
	}
	
	public String getDep() {
		return dep;
	}
	
	public void setDep(String dep) {
		this.dep = dep;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
