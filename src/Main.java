import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Compilador c = new Compilador();
		try {
			c.run();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
