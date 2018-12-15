package Servidor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Testee {

	@Test
	void test() {
		Despachante des = new Despachante();
		
		System.out.println( des.invoke("add 4 4") );
	
	}

}
