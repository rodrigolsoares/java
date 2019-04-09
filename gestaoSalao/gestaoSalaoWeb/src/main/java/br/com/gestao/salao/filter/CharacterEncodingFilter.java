package br.com.gestao.salao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.jsf")
public class CharacterEncodingFilter implements Filter {
   
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
      throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

  
    public void init(FilterConfig filterConfig) throws ServletException {
    }

   
    public void destroy() {
    }
}
