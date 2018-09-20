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
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		System.out.println("___________________________________________");
		System.out.println("profileAction.requestedProcess >>> 메서드 호출됨");
		System.out.println("timeStamp.getDateTime >>> "+ timeStamp.getDateTime());
		System.out.println("timeStamp >>> "+ timeStamp.getDate());
		System.out.println("formattedDate >>> "+MainConst.getFormatedDate());
		System.out.println("___________________________________________");
		
		request.setAttribute("profilePagedate", MainConst.getFormatedDate());
		
		return "/subSection/profile.jsp";
	}
	
}
