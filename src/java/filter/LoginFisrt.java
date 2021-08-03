/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Account;

/**
 *
 * @author Dell Inc
 */
public class LoginFisrt implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Account account = (Account) req.getSession().getAttribute("account");

        String url = req.getRequestURI();
        //user access to makeQuiz or manageQuiz or takeQuiz
        if (url.contains("Quiz")) {
            //user is not login
            if (account == null) {
                request.setAttribute("message", "You must login firstly");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            //user access to home
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
