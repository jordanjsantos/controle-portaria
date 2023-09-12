package br.com.controle.portaria.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, 
						      HttpServletResponse response,
						      Object controller) throws Exception {
		System.out.println("#############AutorizadorInterceptor#############");
		
		String uri = request.getRequestURI();
		if(uri.endsWith("acessoSistema") || uri.endsWith("efetuaLogin") 
				||  uri.contains("images") || uri.contains("include") 
				|| uri.contains("swagger-resources") || uri.contains("swagger-ui")
				|| uri.contains("api-docs")){
			return true;
		}
		if(request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}

        response.sendRedirect("acessoSistema");
	      
	    return false;
	}

}
