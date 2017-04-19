package kr.or.orange.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.orange.service.BoardDao;
import kr.or.orange.service.Reply;

@SuppressWarnings("serial")
@WebServlet("/model2/BoardReplyServlet.do")
public class BoardReplyServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost BoardReplyServlet");
		request.setCharacterEncoding("euc-kr");
		Reply re = new Reply();
		int boardNo =  Integer.parseInt(request.getParameter("board_no"));
		re.setBoard_no(boardNo);
		re.setRe_user(request.getParameter("re_user"));
		re.setRe_pw(request.getParameter("re_pasword"));
		re.setRe_content(request.getParameter("re_content"));
		System.out.println(re.toString());
		
		BoardDao boarddao = new BoardDao();
		boarddao.replyBoard(re);
		
		request.setAttribute("boardNo", boardNo);
		response.sendRedirect(request.getContextPath()+"/model2/BoardDetailsServlet.do?boardNo="+boardNo);
	}

}
