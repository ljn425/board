package kr.or.orange.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConnection() throws Exception{
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/injava?useUnicode=true&characterEncoding=UTF-8";
		String dbUser = "root";
		String dbPw = "java0000";
		
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		
		return connection;
	}
	
	/*public ArrayList<Board> searchPageBoard(String so, String sv, int currentPage, int pagePerRow){
		try {
			connection = getConnection();
			String sql ="SELECT board_no, board_title, board_user, DATE_FORMAT(board_date, '%Y-%m-%d %h:%i:%s') board_date, board_count FROM uboard WHERE "+so+" LIKE '%"+sv+"%' ORDER BY board_no DESC LIMIT ?, ?";
			if(so.equals("board_hap")){
				sql ="SELECT * FROM uboard WHERE board_title LIKE '%"+sv+"%' || board_content LIKE '%"+sv+"%' ORDER BY board_no DESC LIMIT ?, ?";
			}
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, (currentPage-1)*pagePerRow);
			pstmt.setInt(2, pagePerRow);
			rs = pstmt.executeQuery();
			ArrayList<Board> boardlist = new ArrayList<Board>();
			while(rs.next()){
				Board board = new Board();
				board.setBoard_no(rs.getInt("board_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_user(rs.getString("board_user"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_count(rs.getInt("board_count"));
				boardlist.add(board);
			}
			return boardlist;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {rs.close();} catch(Exception e){}
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		return null;
		
	}*/
	
	public List<Board> searchPageBoard(String so, String sv, int currentPage, int pagePerRow){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("so", so);
		map.put("sv", sv);
		map.put("currentPage", currentPage);
		map.put("pagePerRow", pagePerRow);
		return sqlSessionTemplate.selectList("kr.or.orange.service.BoardMapper.searchPageBoard", map);
	}
	
	public List<Board> searchBoard(String so, String sv){
		Map<String, String> map = new HashMap<String, String>();
		map.put("so", so);
		map.put("sv", sv);
		List<Board> boardList = null;
		boardList = sqlSessionTemplate.selectList("kr.or.orange.service.BoardMapper.searchBoard", map);
		if(so.equals("board_hap"))
			boardList = sqlSessionTemplate.selectList("kr.or.orange.service.BoardMapper.searchBoardHap", map);
		return boardList;
	}
	
	public int replayTotalBoard(int board_no){
		return sqlSessionTemplate.selectOne("kr.or.orange.service.BoardMapper.replayTotalBoard", board_no);
	}
	
	public List<Reply> replaySelectBoard(int board_no){
		return sqlSessionTemplate.selectList("kr.or.orange.service.BoardMapper.replaySelectBoard", board_no);
	}
	
	public void replyBoard(Reply re){
		sqlSessionTemplate.insert("kr.or.orange.service.BoardMapper.replyBoard", re);
	}
	
	public int updateBoard(Board board){
		return sqlSessionTemplate.update("kr.or.orange.service.BoardMapper.updateBoard", board);
	}
	
	public int deleteBoard(Board board){
		return sqlSessionTemplate.delete("kr.or.orange.service.BoardMapper.deleteBoard", board);
	}
	
	public void readCountBoard(int boardNo){
		sqlSessionTemplate.update("kr.or.orange.service.BoardMapper.readCountBoard", boardNo);
	}
	
	public List<Board> pagePerRowrBoard(int currentPage, int pagePerRow){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", (currentPage-1)*pagePerRow);
		map.put("pagePerRow", pagePerRow);
		return sqlSessionTemplate.selectList("kr.or.orange.service.BoardMapper.pagePerRowrBoard", map);
	}
	
	public int totalBoard(){
		int row = sqlSessionTemplate.selectOne("kr.or.orange.service.BoardMapper.totalBoard");
		return row;
	}
	
	public Board getBoard(int boardNo){
		return sqlSessionTemplate.selectOne("kr.or.orange.service.BoardMapper.getBoard", boardNo);
	}
	
	public int insertBoard(Board board){
		return sqlSessionTemplate.insert("kr.or.orange.service.BoardMapper.insertBoard", board);
	}
}
