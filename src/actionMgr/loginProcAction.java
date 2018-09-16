package actionMgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.*;
import dbConnector.userDAO;
public class loginProcAction implements CMD_Act{

	@Override
	public String requestedProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String email    = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("\n__________________loginProc");
		System.out.println("email  >>> "+email);
		System.out.println("passwd >>> "+password);
		System.out.println("___________________________\n");
		boolean loginChecker = new userDAO().proc_Login(email, password);
				
		session.setAttribute("loginChecker", loginChecker);
		session.setAttribute("email", email);
		
		return "/mgr_account/loginProc.jsp";
	}

}
