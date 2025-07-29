import Excepciones.ObjectAlreadyExistsException;
import logico.Cliente;
import logico.Componente;
import logico.Microprocesador;
import logico.Tienda;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Componente dupe =new Microprocesador(1, 449.99, 12, "CPU-AMD-001", "AMD", "Ryzen 9 5950X", "AM4", "4.9GHz","Microprocesador");
		
		try {
		Tienda.getInstance().agregarComponente(dupe);
		} catch (ObjectAlreadyExistsException e) {
			e.printStackTrace();
		}

	}

}
