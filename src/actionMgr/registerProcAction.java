package actionMgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CMD_Act;
import dbConnector.*;
public class registerProcAction implements CMD_Act{

	@Override
	public String requestedProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		/*
				<label class="w3-text-dark-grey" style="font-weight: bold;">E-mail</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="email">
				<br>
				<label class="w3-text-dark-grey" style="font-weight: bold;">Password</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="passwd">
				<br>
				<label class="w3-text-dark-grey" style="font-weight: bold;">Password Check</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="passwdCheck">
				<br>
				<label class="w3-text-dark-grey" style="font-weight: bold;">NickName</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="nickname">
		 */
		userDTO newUser = new userDTO();
		System.out.println("___________________________________");
		System.out.println("registerProcAction>>> "+request.getParameter("email"));
		System.out.println("registerProcAction>>> "+request.getParameter("password"));
		System.out.println("registerProcAction>>> "+request.getParameter("nickname"));
		System.out.println("___________________________________");
		newUser.setEmail(request.getParameter("email"));
		newUser.setPassword(request.getParameter("password"));
		newUser.setNickname(request.getParameter("nickname"));
		
		System.out.println("registerProcAction>>> newUser : "+newUser.getEmail());
		System.out.println("registerProcAction>>> newUser : "+newUser.getPassword());
		System.out.println("registerProcAction>>> newUser : "+newUser.getNickname());
		
		userDAO dataMgr = new userDAO();
		boolean insertChecker = dataMgr.insertUserData(newUser);
		
		request.setAttribute("insertChecker", insertChecker);
		
		return "/mgr_account/registerProc.jsp";
	}

}
