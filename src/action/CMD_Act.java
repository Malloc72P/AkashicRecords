package action;

import javax.servlet.http.*;

public interface CMD_Act {
	
	public String requestedProcess(HttpServletRequest request,HttpServletResponse response) throws Throwable;
}
