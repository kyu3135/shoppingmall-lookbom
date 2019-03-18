package notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/ndetail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NoticeDetailServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNum = Integer.parseInt(request.getParameter("nnum"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		NoticeService nservice = new NoticeService();
		
		nservice.addReadCount(noticeNum);
		
		Notice notice = nservice.selectNotice(noticeNum);
		
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		if(notice != null) {
			view = request.getRequestDispatcher("views/notice/noticeListView.jsp");
			request.setAttribute("notice", notice);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.print("alert('번 글 조회실패!');");
			out.println("history.back(-1)");
			out.println("</script>");
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
