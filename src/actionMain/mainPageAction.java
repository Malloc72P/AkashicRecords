package actionMain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbConnector.*;
import action.*;
public class mainPageAction implements CMD_Act{

	@Override
	public String requestedProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		String      email        = null;
		userDTO     uData        = null;
		HttpSession session      = null;
		
		if(request != null) {
			session = request.getSession();
		}
		
		if(session.getAttribute("email") != null){
			System.out.println("로그인 돼있는 상태임......");
			email = (String)session.getAttribute("email");
			uData        = new userDAO().getUserData(email);
		}
		else {
			System.out.println("로그인 안돼있는상태임...");
		}
		
		request.setAttribute("email" , email);
		request.setAttribute("uData" , uData       );
		return "/mainPage.jsp";
	}

}
