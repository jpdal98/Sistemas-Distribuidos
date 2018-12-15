package servidor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mensagem.Mensagem;
import mensagem.Mensagem_requisicao;
import mensagem.Mensagem_resposta;

public class QtwiterDespachante {

//	private Mensagem_requisicao resposta;

	public Mensagem selecionaEqueleto(Mensagem_requisicao request) {

		Class<?> objRef = null;
		Mensagem resposta = null;
		boolean result = false;
		Method method;
		String defineEsqueleto = request.getObjectRef();
		String methodName = request.getMethod();

		try {
			objRef = Class.forName("servidor.Esqueleto");

			Object obj = objRef.newInstance();

			System.out.println("Executando: " + methodName);

			method = objRef.getMethod(methodName, Mensagem_requisicao.class);
				
			result = (boolean) method.invoke(obj, request);
			
			resposta = new Mensagem_resposta(result);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return resposta;
	}
}