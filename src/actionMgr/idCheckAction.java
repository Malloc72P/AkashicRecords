package actionMgr;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import dbConnector.userDAO;
public class idCheckAction implements CMD_Act{

	@Override
	public String requestedProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		System.out.println("idCheckAction>>> email : "+email);
		
		userDAO dataMgr = new userDAO();
		boolean emailChecker = dataMgr.proc_emailChecker(email);
		request.setAttribute("emailChecker", emailChecker);
		
		return "/mgr_account/idCheck.jsp";
	}

}
