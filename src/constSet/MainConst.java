package constSet;

public class MainConst {
	
	//***PROJECT NAME
	public static final String PROJECT_NAME = "/AkashicRecords";
	
	//***SERVER URL
	public static final String MAIN_SERVER_PROTOCOL  = "http://";
	public static final String MAIN_SERVER_URL       = "localhost:8090";
	public static final String MAIN_PAGE             = "/mainPage.do";
	public static final String MAIN_PROJECT_URL      = MAIN_SERVER_PROTOCOL+MAIN_SERVER_URL+PROJECT_NAME+MAIN_PAGE;
	
    //***PROPERTY FILE RELATIVE PATH FROM SERVER_FILESYSTEM_PATH
	public static final String MAIN_DB_PROPERTY_PATH  = "/WEB-INF/db_properties";
	public static final String MAIN_ACT_PROPERTY_PATH = "/WEB-INF/Cmd_Proc.properties";
	
	//***PROPERTY FILE ABSOLUTE PATH
	public static  String SERVER_FILESYSTEM_PATH          = "";
	public static  String MAIN_DB_PROPERTY_ABSOLUTE_PATH  = "";
	public static  String MAIN_ACT_PROPERTY_ABSOLUTE_PATH = ""; 

	//***CSS DATA
	public static final String THEME_COLOR_MAIN      = "w3-white";
	public static final String THEME_COLOR_TEXT      = "w3-black";
	public static final String THEME_COLOR_ERR       = "w3-text-red";
	public static final String THEME_COLOR_SUB       = "w3-white";
	public static final String THEME_COLOR_BG        = "background: white";
	/*public static final String THEME_COLOR_BG = "background: #ffe9fb; background: -moz-linear-gradient(45deg,  #ffe9fb 0%,#e4fef6 100%); background: -webkit-linear-gradient(45deg,  #ffe9fb 0%,#e4fef6 100%); background: linear-gradient(45deg,  #ffe9fb 0%,#e4fef6 100%);";*/
	//public static final String THEME_COLOR_BG   = "background: #9999a4; background: -moz-linear-gradient(to right,  #9999a4 0%,#60606b 100%); background: -webkit-linear-gradient(to right,  #9999a4 0%,#60606b 100%); background: linear-gradient(to right,  #9999a4 0%,#60606b 100%);";
	public static void SET_SERVER_FILESYSTEM_PATH(String server_Path) {
		MainConst.SERVER_FILESYSTEM_PATH = server_Path;
		MainConst.MAIN_DB_PROPERTY_ABSOLUTE_PATH = MainConst.SERVER_FILESYSTEM_PATH + MainConst.MAIN_DB_PROPERTY_PATH;
		MainConst.MAIN_ACT_PROPERTY_ABSOLUTE_PATH = MainConst.SERVER_FILESYSTEM_PATH + MainConst.MAIN_ACT_PROPERTY_PATH;
	}
}

/*
 *  brite bg
 *  >>> public static final String THEME_COLOR_BG = "background: #ffe9fb; background: -moz-linear-gradient(45deg,  #ffe9fb 0%,#e4fef6 100%); background: -webkit-linear-gradient(45deg,  #ffe9fb 0%,#e4fef6 100%); background: linear-gradient(45deg,  #ffe9fb 0%,#e4fef6 100%);";
 *  dark bg
 *  >>> public static final String THEME_COLOR_BG = "background: #9999a4; background: -moz-linear-gradient(to right,  #9999a4 0%,#60606b 100%); background: -webkit-linear-gradient(to right,  #9999a4 0%,#60606b 100%); background: linear-gradient(to right,  #9999a4 0%,#60606b 100%);";
 * */
