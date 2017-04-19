package kr.or.orange.service;

public class Reply {
	//re_no board_no re_user re_date re_parent re_content
	private int re_no;
	private int board_no;
	private String re_user;
	private String re_pw;
	private String re_date;
	private int re_parent;
	private String re_content;
	
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getRe_user() {
		return re_user;
	}
	public void setRe_user(String re_user) {
		this.re_user = re_user;
	}
	public String getRe_pw() {
		return re_pw;
	}
	public void setRe_pw(String re_pw) {
		this.re_pw = re_pw;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	public int getRe_parent() {
		return re_parent;
	}
	public void setRe_parent(int re_parent) {
		this.re_parent = re_parent;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	
	@Override
	public String toString() {
		return "Reply [re_no=" + re_no + ", board_no=" + board_no + ", re_user=" + re_user + ", re_pw=" + re_pw
				+ ", re_date=" + re_date + ", re_parent=" + re_parent + ", re_content=" + re_content + "]";
	}
	
	
}
