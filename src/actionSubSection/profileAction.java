package actionSubSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.snmp.Timestamp;

import action.CMD_Act;
import constSet.MainConst;

public class profileAction implements CMD_Act{

	@Override
	public String requestedProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		return "/subSection/profile.jsp";
	}
	
}
