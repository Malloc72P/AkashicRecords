package actionSubSection;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import dbConnector.postDAO;
public class recentPostsAction implements CMD_Act{

	@Override
	public String requestedProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("ListAction Model called");
		int pageSize = 5;// numPerPage->페이지당 보여주는 게시물수
		int blockSize = 5;// pagePerBlock->블럭당 보여주는 페이지수
		// 1 2 3 4 5 6 7 8 9 10 [다음 10]
		// 작성날짜, 시분까지
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력
		String pageNum = request.getParameter("pageNum");// null->1
		System.out.println("pageNum From RecentPostsAction >>> "+pageNum);
		if (pageNum == null) {// 맨 처음 실행한것이라면
			pageNum = "1";// default
		}
		else if(pageNum.equals("")){
			pageNum = "1";// default
		}
		// "1"->1
		System.out.println("pageNum From RecentPostsAction >>> "+pageNum);
		int currentPage = Integer.parseInt(pageNum);// nowPage(현재페이지)
		
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;// 1*10=10,2*10=20,3*10=30(레코드갯수 X)
		int count = 0;// 총레코드수
		int number = 0;// beginPerPage->페이지별 시작하는 맨 처음에 나오는 게시물번호
		List articleList = null;// 화면에 출력할 레코드 전체

		postDAO dbPro = new postDAO();
		
		
		count = dbPro.getArticleCount();
		if (count > 0) {// 화면에 보여줄 레코드가 한개라도 존재한다면
			articleList = dbPro.getArticles(startRow, pageSize);// 10개씩 (endRow X)
		}
		else {
			articleList = Collections.EMPTY_LIST;//해당 리스트가 비어있다는 것을 의미하는 상수
		}
		//____________________________________________________________
		System.out.println("count : "+count);
		// 122-(1-1)*10=122,121,120,119
		// 122-(2-1)*10=110,109,108,,,
		number = count - (currentPage - 1) * pageSize;
		System.out.println("페이지별 number=>" + number);
		

		
		//_____________________________________________________________
	    //페이징처리
		int startPage = 0;
        int endPage   = 0;//1+10-1=10
        int pageCount = 0;
	    if(count > 0){//레코드가 한개이상 존재한다면
	    	//1.총페이지수 구하기
	    	//                    122/10=12.2+1.0=13.2=13
	    	pageCount=count/pageSize+(count%pageSize==0?0:1);
	        System.out.println("pageCount=>"+pageCount);
	        //2.시작페이지,끝페이지 
	        //                         1-((1-1)%10)
	        startPage=currentPage-((currentPage-1)%blockSize);
	        endPage=startPage+blockSize-1;//1+10-1=10
	        System.out.println("startPage="+startPage+",endPage="+endPage);
	        //블럭별로 구분해서 링크걸어서 출력
	        if(endPage > pageCount) endPage=pageCount;//마지막페이지=총페이지수
	        //3-1) 이전블럭(11페이지 이상)->if(11 > 10)
	    }
	    //_____________________________________________________________
	    //이렇게 처리 과정을 수행하고, 뷰에서 필요로 하는 변수값들을 뷰와 공유해야 한다.
		//이는 request객체를 이용해서 메모리에 데이터를 올리는 방식으로 공유한다.
		//뷰에서는 여러 방법으로 메모리에 접근해서 데이터를 가져오면 된다.
		//ex) EL태그
	    
	    
		request.setAttribute("count"       , count       );
		request.setAttribute("articleList" , articleList );
		request.setAttribute("number"      , number      );
		request.setAttribute("currentPage" , currentPage );
		request.setAttribute("pageNum"     , pageNum     );
		request.setAttribute("sdf"         , sdf         );
		request.setAttribute("pageSize"    , pageSize    );
		request.setAttribute("blockSize"   , blockSize   );
		request.setAttribute("startPage"   , startPage   );
		request.setAttribute("endPage"     , endPage     );
		request.setAttribute("pageCount"   , pageCount   );
		return "/subSection/recentPosts.jsp";
		
	}

}
