package kr.or.orange.service;

public class Board {
	private int board_no;
	private String board_user;
	private String board_pw;
	private String board_title;
	private String board_content;
	private String board_date;;
	private int board_count;
	private String board_file;
	private int board_reply;
	
	public int getBoard_reply() {
		return board_reply;
	}
	public void setBoard_reply(int board_reply) {
		this.board_reply = board_reply;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_user() {
		return board_user;
	}
	public void setBoard_user(String board_user) {
		this.board_user = board_user;
	}
	public String getBoard_pw() {
		return board_pw;
	}
	public void setBoard_pw(String board_pw) {
		this.board_pw = board_pw;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public int getBoard_count() {
		return board_count;
	}
	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}
	public String getBoard_file() {
		return board_file;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	
	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", board_user=" + board_user + ", board_pw=" + board_pw
				+ ", board_title=" + board_title + ", board_content=" + board_content + ", board_date=" + board_date
				+ ", board_count=" + board_count + ", board_file=" + board_file + "]";
	}
	
	
	
}
