package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.io.*;
import action.*;
import constSet.*;
/**
 * Servlet implementation class MainCtrl
 */
@WebServlet("/MainCtrl")
public class MainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map commandMap = new HashMap();
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext application = config.getServletContext();
		
		MainConst.SET_SERVER_FILESYSTEM_PATH(application.getRealPath(""));
		System.out.println("MainCTRL >>> "+MainConst.SERVER_FILESYSTEM_PATH);
		// 경로에 맞는 Cmd_Proc.properties파일을 불러옴
		//	init의 param인 ServletConfig config는 web.xml을 가져왔었는데(MK6 이전)
		//  하나의 파일에서 관리하고 싶어서 const/mainConst.class에서 전부 가져오기로 했다 (그래야 관리하기 편하니까)
		//String props = config.getInitParameter("Cmd_Proc");
		// web.xml에서 param-name이 Cmd_Proc인 것을 찾아서, param-value를 불러온다.
		System.out.println("불러온경로=" + MainConst.MAIN_ACT_PROPERTY_ABSOLUTE_PATH);

		// 명령어와 처리클래스의 매핑정보를 저장할
		// Properties객체 생성
		Properties cmd_Proc = new Properties();
		FileInputStream in_File = null;// 파일불러올때

		try {
			// CMD_Proc.properties파일의 내용을 읽어옴 -> 각 페이지에 맵핑돼있는 명령처리클래스의 이름이 해당 프로퍼티 파일 안에 있음.
			in_File = new FileInputStream(MainConst.MAIN_ACT_PROPERTY_ABSOLUTE_PATH);

			// 파일의 정보를 Properties에 저장
			cmd_Proc.load(in_File);//주 메모리에 프로퍼티 파일 적재.

		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if (in_File != null)
				try {
					in_File.close();
				} catch (IOException ex) {
				}
		}

		// 프로퍼티 파일을 이터레이터로 순회한다
		// 순회하면서 나온 데이터는 다음과 같다
		// key : 페이지명 -> value : 페이지의 명령처리클래스 이름
		Iterator iterator_propertyFile = cmd_Proc.keySet().iterator();//순회를 위한 이터레이터 생성

		while (iterator_propertyFile.hasNext()) {//이터레이터가 읽을 데이터가 존재한다면, 반복문의 내용을 실행한다.
			// 요청한 명령어를 구하기위해
			String command = (String) iterator_propertyFile.next();//데이터를 next()메서드로 읽는다
			System.out.println("command=" + command);//읽은 데이터에 대한 정보
			// 요청한 명령어(키)에 해당하는 클래스명을 구함
			String className = cmd_Proc.getProperty(command);//읽은 데이터의 value를 리턴 -> 명령처리클래스의 이름, String className에 저장
			System.out.println("className=" + className);//명령처리클래스의 이름

			try {
				// 그 클래스의 객체를 얻어오기위해 메모리에 로드
				Class commandClass = Class.forName(className);//위에서  프로퍼티 파일로부터 읽은 데이터를 토대로, 명령처리클래스의 클래스파일을 찾는다
				System.out.println("commandClass=" + commandClass);
				Object commandInstance = commandClass.newInstance();//찾은 클래스파일로 객체를 생성한다.
				System.out.println("commandInstance=" + commandInstance);

				// Map객체 commandMap에 저장
				commandMap.put(command, commandInstance);//명령처리클래스맵에 값을 아래와 같이 넣는다
														 // key : 페이지의 이름 -> value : key에 저장된 페이지에 해당하는 명령처리클래스의 객체(인스턴스)
				
				System.out.println("commandMap=" + commandMap);

			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		} // while
		//while루프 탈출 시, 프로퍼티 파일에 있는 모든 명령처리클래스의 인스턴스를 가지고 있는 명령처리클래스맵이 생성된다.
		//앞으로 이 명령처리클래스맵에서 필요한 명령처리클래스의 인스턴스를 호출해서 사용하게 된다. 호출하기 위해서는 명령처리클래스에 대응하는 페이지의 이름을 key로 사용해야 한다.
		//모든 명령처리클래스는 CMD_Act(명령처리 인터페이스)인터페이스를 구현하였기 때문에, 따로 인스턴스를 체크하지 않아도 필요한 기능을 수행 할 수 있다(다형성을 이용.)
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("___________________________________________");
		System.out.println("get Method called");
		System.out.println("___________________________________________");
		try {
			requestToProcessor(request,response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("___________________________________________");
		System.out.println("post Method called");
		System.out.println("___________________________________________");
		try {
			requestToProcessor(request,response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void requestToProcessor(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String view = "";
		CMD_Act myCommand = null;
		try {
			System.out.println("\n________________________________");
			// 1. request.getRequestURI로 프로젝트상의 패스로 변환
			// ex) 1번이 목표로 하는 상태 : board2/list.do
			System.out.println("request.getRequestURI로 프로젝트상의 패스로 변환");
			String newCommand = request.getRequestURI();//newCommand에는, 원격지에서 요청한 페이지의 정보를 저장받을 변수이다.
														//따라서, getRequestURI로 구한 주소값에서 프로젝트명을 제거해야 한다.
			System.out.println("request.getRequestURI() : " + newCommand);

			// 2. request.getContextPath를 이용해서 패스 상 프로젝트 이름 제거
			// ex) 2. 의 상태 : list.do
			System.out.println("2. request.getContextPath를 이용해서 패스상 프로젝트 이름 제거");
			System.out.println("request.getContextPath() : " + request.getContextPath());
			if (newCommand.indexOf(request.getContextPath()) == 0) {
				newCommand = newCommand.substring(request.getContextPath().length());
				System.out.println("2. 상태의 newCommand : " + newCommand);
			}
			//
			myCommand = (CMD_Act) commandMap.get(newCommand);
			System.out.println("myCommand : " + myCommand);
			view = myCommand.requestedProcess(request, response);
			System.out.println("view : " + view);

			System.out.println("________________________________\n");
		} catch (Exception err) {
			err.printStackTrace();
		}
		RequestDispatcher myDispatcher = request.getRequestDispatcher(view);
		myDispatcher.forward(request, response);//데이터 공유를 위해서 포워딩 사용

	}
}

