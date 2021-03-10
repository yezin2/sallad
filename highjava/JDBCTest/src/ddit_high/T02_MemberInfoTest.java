package ddit_high;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class T02_MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
				
					break;
				case 3 :  // 자료 수정
				
					break;
				case 4 :  // 전체 자료 출력
					displayAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	//전체 회원을 출력하는 메서드
	private void displayAll() {
		System.out.println();
		System.out.println("================================================");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("================================================");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB에 접속 (Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "pc17";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from mymember");
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				System.out.println(memId + "\t" + memName + "\t"
									+ memTel + "\t" + memAddr + "\t");
			}
			System.out.println("================================================");
			System.out.println("출력작업 끝...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	//회원을 추가하기 위한 메서드
	private void insertMember() {
		boolean chk = false;
		String memId = null;
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.println("회원 ID >> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == true) {
				System.out.println("회원ID가" + memId + "인 회원이 이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
			}
		} while (chk == true);
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원 전회번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력버퍼 비우기
		System.out.println("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
						
			// 2. DB에 접속 (Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "pc17";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr)"
					+ " values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 추가 작업 성공");
			} else {
				System.out.println(memId + "회원 추가 작업 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	/*
	 * 회원 ID를 이용하여 회원이 있는지 알려주는 메서드
	 * @param memId
	 * @return 존재하면 true, 없으면 false
	 */
	private boolean checkMember(String memId) {
		boolean chk = false;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
						
			// 2. DB에 접속 (Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "pc17";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}finally {
			disConnect();
		}
		return chk;
	}
	
	//자원 반납
	private void disConnect() {
		if(rs != null) try {rs.close();} catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
		if(conn != null) try {conn.close();} catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
	}

	public static void main(String[] args) {
		T02_MemberInfoTest memObj = new T02_MemberInfoTest();
		memObj.start();
	}

}






