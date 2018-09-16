package actionSubSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CMD_Act;

public class postListAction  implements CMD_Act{

	@Override
	public String requestedProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		return "/subSection/postList.jsp";
	}

}
