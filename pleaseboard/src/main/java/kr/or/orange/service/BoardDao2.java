package kr.or.orange.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import kr.or.orange.service.Reply;

@Repository
public class BoardDao2 {
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
	
	
	//��� ��
	public int replayTotalBoard(int board_no){
		int row = 0;
		try {
			connection = getConnection();
			String sql = "SELECT count(*) FROM boardreply WHERE board_no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				row = rs.getInt(1);
			}
			return row;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {rs.close();} catch(Exception e){}
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		return row;
	}
	
	//��� ����Ʈ
	public ArrayList<Reply> replaySelectBoard(int board_no){
		try {
			connection = getConnection();
			String sql = "SELECT re_no, board_no, re_user, DATE_FORMAT(re_date, '%Y-%m-%d %h:%i:%s') re_date, re_parent, re_content FROM boardreply WHERE board_no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			ArrayList<Reply> replylist = new ArrayList<Reply>();
			while(rs.next()){
				Reply reply = new Reply();
				reply.setRe_no(rs.getInt("re_no"));
				reply.setRe_user(rs.getString("re_user"));
				reply.setRe_date(rs.getString("re_date"));
				reply.setRe_parent(rs.getInt("re_parent"));
				reply.setRe_content(rs.getString("re_content"));
				replylist.add(reply);
			}
			return replylist;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {rs.close();} catch(Exception e){}
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		return null;
	}
	
	
	//��� �Է�
	public void replyBoard(Reply re){
		try {
			connection = getConnection();
			String sql = "INSERT INTO boardreply(board_no, re_user, re_pw, re_date, re_content) VALUES ( ?, ?, ?, NOW(), ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, re.getBoard_no());
			pstmt.setString(2, re.getRe_user());
			pstmt.setString(3, re.getRe_pw());
			pstmt.setString(4, re.getRe_content());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
	}
	
	
	//�˻� ����Ʈ 
	public ArrayList<Board> searchPageBoard(String so, String sv, int currentPage, int pagePerRow){
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
		
	}
	
	//�Խù� �˻�
	public ArrayList<Board> searchBoard(String so, String sv){
		try {
			connection = getConnection();
			String sql ="SELECT board_no, board_title, board_user, board_date, board_count FROM uboard WHERE "+so+" LIKE '%"+sv+"%'";
			if(so.equals("board_hap")){
				sql ="SELECT * FROM uboard WHERE board_title LIKE '%"+sv+"%' || board_content LIKE '%"+sv+"%'";
			}
			pstmt = connection.prepareStatement(sql);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			ArrayList<Board> searchlist = new ArrayList<Board>();
			while(rs.next()){
				Board board = new Board();
				searchlist.add(board);
			}
			return searchlist;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {rs.close();} catch(Exception e){}
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		return null;
	}
	
	//�Խù� ����
	public int updateBoard(Board board){
		int result = 0;
		System.out.println(board);
		try {
			connection = getConnection();
			String sql = "UPDATE uboard SET board_title=?, board_content=? WHERE board_no=? && board_pw=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setInt(3, board.getBoard_no());
			pstmt.setString(4, board.getBoard_pw());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		
		return result;
	}
	
	
	//�Խù� ����
	public int deleteBoard(Board board){
		int row = 0;
		try {
			connection = getConnection();
			String sql = "DELETE FROM uboard WHERE board_no =? && board_pw =?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_no());
			pstmt.setString(2, board.getBoard_pw());
			row = pstmt.executeUpdate();
			return row;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {rs.close();} catch(Exception e){}
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		return row;
	}
	
	
	//��ȸ�� ����
	public void readCountBoard(int boardNo){
		try {
			connection = getConnection();
			String sql = "UPDATE uboard SET board_count=board_count+1 WHERE board_no = "+boardNo;
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
	}
	
	
	//�� ���� �Խ��� ��������
	public Board getBoard(int boardNo){
		Board board = new Board();
		try {
			connection = getConnection();
			String sql = "SELECT board_no, board_user, board_title, board_content, DATE_FORMAT(board_date, '%Y-%m-%d %h:%i:%s') board_date, board_count, board_file FROM uboard WHERE board_no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()){
				board.setBoard_no(rs.getInt("board_no"));
				board.setBoard_user(rs.getString("board_user"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_count(rs.getInt("board_count"));
				board.setBoard_file(rs.getString("board_file"));
			}
			return board;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {rs.close();} catch(Exception e){}
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		return board;
	}
	
	//�������������� ������ �� �ִ� �Խñ� �� 
	public ArrayList<Board> pagePerRowrBoard(int currentPage, int pagePerRow){
		
		try {
			connection = getConnection();
			String listSql = "SELECT board_no, board_title, board_user, DATE_FORMAT(board_date, '%Y-%m-%d %h:%i:%s') board_date, board_count, (SELECT count(*) FROM boardreply WHERE board_no = u.board_no) reply FROM uboard u ORDER BY board_no DESC LIMIT ?, ?";
			pstmt = connection.prepareStatement(listSql);
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
				board.setBoard_reply(rs.getInt("reply"));
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
	}
	
	//��ü �Խù� �� 
	public int totalBoard(){
		int row = 0;
		try {
			connection = getConnection();
			String sql = "SELECT COUNT(*) FROM uboard";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {rs.close();} catch(Exception e){}
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		return row;
	}
	
	//�Խ��� �Է�
	public int insertBoard(Board board){
		int result = 0;
		try {
			connection = getConnection();
			String sql = "INSERT INTO uboard(board_user, board_pw, board_title, board_content, board_date, board_file) VALUES(?, ?, ?, ?, NOW(), ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_user());
			pstmt.setString(2, board.getBoard_pw());
			pstmt.setString(3, board.getBoard_title());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertBoard Exception!!");
		} finally{
			try {pstmt.close();} catch(Exception e){}
		    try {connection.close();} catch(Exception e){}
		}
		
		return result;
	}
}
