package kr.or.orange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.orange.service.Board;
import kr.or.orange.service.BoardDao;
import kr.or.orange.service.Reply;

//SpringMVC - @Controller는 서블릿을 상속받지 않아도 서블릿을 대신할 수 있는 클래스로 만들어준다.
@Controller
public class BoardController {
	@Autowired //Spring - @Autowired 스플링실행시 @Repository된 객체의 데이터타입을 찾아준다. 해당 데이터타입과 일치하면 생성된 객체의 참조값을 대입한다.
	private BoardDao boarddao;
	
	//개인 포트폴리오
	@RequestMapping(value="/work", method = RequestMethod.GET)
	public String work(){
		return "work";
	}
	
	
	//게시판 검색
	@RequestMapping(value="/boardSearch" , method = {RequestMethod.GET, RequestMethod.POST})
	public String BoardSearch(Model model
			, @RequestParam("so") String so
			, @RequestParam("sv") String sv
			, @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage){
		
		List<Board> searchlist = boarddao.searchBoard(so, sv);
		if(currentPage < 1){
			currentPage = 1;
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
	    
	    model.addAttribute("searchRowCount", searchRowCount);
	    model.addAttribute("boardlist", boardlist);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("nextPage", nextPage);
	    model.addAttribute("previousPage", previousPage);
	    model.addAttribute("lastPage", lastPage);
	    model.addAttribute("so", so);
	    model.addAttribute("sv", sv);
		
		return "board_searchlist";
	}
	
	//게시판 댓글
	@RequestMapping(value="/boardReply", method = RequestMethod.POST)
	public String boardReply(Reply reply){
		boarddao.replyBoard(reply);
		return "redirect:/boardView?boardNo="+reply.getBoard_no();
	}
	
	//게시판 삭제
	@RequestMapping(value="/boardDelete", method = RequestMethod.POST)
	public String boardDelete(Board board){
		boarddao.deleteBoard(board);
		return "redirect:/boardList";
	}
	
	
	//게시판 삭제폼
	@RequestMapping(value="/boardDelete", method = RequestMethod.GET)
	public String boardDelete(Model model 
							, @RequestParam(value ="boardNo", required =false, defaultValue="0") int boardNo){
		model.addAttribute("boardNo", boardNo);

		return "board_delete_form";
	}
	
	
	//게시판 수정처리
	@RequestMapping(value="/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(Board board){
		boarddao.updateBoard(board);
		return "redirect:/boardView?boardNo="+board.getBoard_no();
	}
	
	//게시판 수정폼
	@RequestMapping(value="/boardUpdate", method = RequestMethod.GET)
	public String boardUpdate(Model model
			, @RequestParam(value = "boardNo", required=false, defaultValue="0") int boardNo){
		if(boardNo == 0){
			return "redirect:/boardList";
		}
		Board board = boarddao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "board_update_form";
	}
	
	//게시판 상세보기
	@RequestMapping(value="/boardView", method = RequestMethod.GET)
	public String boardView(Model model
							,@RequestParam(value = "boardNo", required=false, defaultValue="0") int boardNo){
		boarddao.readCountBoard(boardNo);
		Board board = boarddao.getBoard(boardNo);
		int totalReply = boarddao.replayTotalBoard(boardNo);
		List<Reply> replylist = boarddao.replaySelectBoard(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("totalReply", totalReply);
		model.addAttribute("replylist", replylist);
		return "board_details";
	}
	
	
	
	//SpringMVC - @RequestMapping 는 value값에 해당 메서드의 경로를 설정해주고 method는 전송방식을 설정할 수 있다.
	@RequestMapping(value ="/boardAdd", method = RequestMethod.GET)
	public String boardAdd(){
		System.out.println("boardAdd 요청");
		return "boardAdd";
	}
	
	//게시판 입력
	@RequestMapping(value ="/boardAdd", method = RequestMethod.POST)
	                       //currentPage 깂이 없으면 required=false 조건을 만족해 1로 초기화 값이 있으면 해당 값이 셋팅
	public String boardAdd(Board board){ //매개변수의 데이터타입으로 인스턴스화하고 인스턴스변수에 post요청한 값을 셋팅한다.(name과 해당 인스터스 필드명이 같아야 하고 setter getter메서드가 선언되있어야 한다.)
		boarddao.insertBoard(board);
		
		return "redirect:/boardList"; //SpringMVC - redirect요청을 해야할 경우 redirect:라고 명시해줘야한다.
	}
	
	//게시판 리스트                                                //스태틱 상수
	@RequestMapping(value ="/boardList", method = RequestMethod.GET)
	public String BoardList(@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage, Model model){
		System.out.println("/boardList 요청");

		
		int totalRowCount = 0;
		totalRowCount = boarddao.totalBoard();
		
		int pagePerRow = 10;
		List<Board> boardlist = boarddao.pagePerRowrBoard(currentPage, pagePerRow);
	
		int lastPage = totalRowCount/pagePerRow;
		if(totalRowCount%pagePerRow != 0) {
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
		
		model.addAttribute("totalRowCount", totalRowCount);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("previousPage", previousPage);
		model.addAttribute("lastPage", lastPage);
		
		return "board_list";
	}
}
