package com.company.Server;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Despachante {

	public String selecionaEqueleto(String request) {
		JSONObject myMenssager = null;
		Class<?> objRef = null;
		Method method = null;
		String resposta = null;
		try {
			myMenssager =new JSONObject(request);

			objRef = Class.forName("com.company.Server."+myMenssager.get("class") + "Skeleton");
			String methodName = myMenssager.getString("method");
			System.out.println("Executando: " + methodName);
			method = objRef.getMethod(methodName,String.class);
			resposta = (String)(method.invoke(objRef.newInstance(),myMenssager.toString()));
		}
		catch (NullPointerException e) {
			System.out.println(e.getCause());
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getCause());
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
//	
//	public String invoque(String mensagem) {
//		
//		String vetor[] = mensagem.split(",");
//		String result = vetor[1];
//		String argumentos = vetor[0]+","+vetor[2];
//		
//		String mensagemRetorno = null;
//		if(result.equals("+")){
//			mensagemRetorno=esqueleto.add(argumentos);
//		}else if(result.equals("-")) {
//			mensagemRetorno=esqueleto.sub(argumentos);
//		}
//		else if(result.equals("*")){
//			mensagemRetorno=esqueleto.mult(argumentos);
//		}
//		else if(result.equals("/")){
//			mensagemRetorno=esqueleto.div(argumentos);
//		}
//		return mensagemRetorno;
//	}
}
