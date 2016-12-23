import java.io.IOException;

public class Main {
	
	private static Compilador c = new Compilador();
	private static Conversor cc = new Conversor();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		compilar();
		converter();
	}
	
	public static void compilar(){
		try {
			c.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void converter(){
		try {
			cc.run(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
