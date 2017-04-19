package kr.or.orange.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.orange.service.Board;
import kr.or.orange.service.BoardDao;

@SuppressWarnings("serial")
@WebServlet("/model2/BoardSearchServlet.do")
public class BoardSearchServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String so = request.getParameter("so");
		String sv = request.getParameter("sv");
		System.out.println("so :" +so +"sv :" + sv);
		BoardDao boarddao = new BoardDao();
		List<Board> searchlist = null;
		searchlist = boarddao.searchBoard(so, sv);
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			if(currentPage < 1){
				currentPage = 1;
	            }
		}
		
		int searchRowCount = searchlist.size();
		int pagePerRow = 10;
		List<Board> boardlist =boarddao.searchPageBoard(so, sv, currentPage, pagePerRow);
		
		int lastPage = searchRowCount/pagePerRow;
		if(searchRowCount%pagePerRow != 0) {
	        lastPage++;
	    }
		 
	    int countPage = 10;
	    int startPage = ((currentPage - 1)/10)*10+1;
	    int endPage = startPage + countPage-1;
	    int nextPage = ((currentPage - 1)/10)*10+11;
	    int previousPage = ((currentPage - 1)/10)*10-10+1;
	    
	    if(previousPage <= 0){
	    	previousPage = 1;
	    }
	    if(endPage > lastPage){
	    	endPage = lastPage;
	    }
	    if(nextPage > lastPage){
	    	nextPage = lastPage;
	    }
	    
		request.setAttribute("searchRowCount", searchRowCount);
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("previousPage", previousPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("so", so);
		request.setAttribute("sv", sv);

		RequestDispatcher rd = request.getRequestDispatcher("/board/board_searchlist.jsp");
		rd.forward(request, response);
	}
}
