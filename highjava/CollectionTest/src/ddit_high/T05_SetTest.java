package ddit_high;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//HashSet 예제
public class T05_SetTest {
	public static void main(String[] args) {
		Set hs1 = new HashSet();
		
		//set에 데이터를 추가할 때도 add()메서드 사용
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set데이터 : " + hs1);
		System.out.println();
		
		//Set은 데이터의 순서가 업고, 중복을 허용 안함
		// 그래서 이미 있는 데이터를 add하면 false를 반환하고, 데이터는 추가X
		
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		//Set데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 해당자료를 삭제한 후 새로운 데이터를 추가해야함
		
		//삭제하는 메서드
		// 1) clear() >> Set 데이터 전체 삭제
		// 2) remove(삭제할 자료) >> 해당자료 삭제
		
		// ex) 'FF'를 'EE'로 수정
		hs1.remove("FF");
		System.out.println("삭제 후 Set 데이터 : " + hs1);
		System.out.println();
		

		hs1.add("EE");
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		//hs1.clear(); //전체자료 삭제
		//System.out.println("clear 후 set 데이터");
		
		System.out.println("set의 자료 개수 : " + hs1.size());
		System.out.println();
		
		//Set은 데이터의 순서가 없기때문에 List처럼 인덱스로 데이터를 하나씩 불러올 수 없다.
		//그래서 데이터를 하나씩 얻기위해서는 Iterator로 변환해야한다.
		//Set의 데이터를 Iterator로 변환하기 >> Set의 iterator() 메서드를 호출하면 된다.
		//iterator : 반복자
		Iterator it = hs1.iterator();
		
		//데이터의 개수만큼 반복
		//hasNext()메서드 >> 포인터 다음 위치에 데이터가 있으면 true,
		//				   없으면 false를 반환한다.
		
		while(it.hasNext()) { // 다음자료가 있는지 검사
			//next()메서드 >> 포인터를 다음 자료 위치로 이동하고, 이동한 위치의 자료를 반환한다.
			System.out.println(it.hasNext());
		}
		
		//1~100사이의 중복되지 않는 정수 5개 만들기
		HashSet<Integer> intRnd = new HashSet<Integer>();
		
		while(intRnd.size() < 5) {
			int num = (int)(Math.random()*100 +1);
			intRnd.add(num);
		}
		
		System.out.println("만들어진 난수들 : " + intRnd);
		
		//Collection 유형의 객체들은 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		//다른 종류의 객체를 생성할 때 생성자에 변경할 데이터를 넣어주면 된다.
		
		List<Integer> intRndList = new ArrayList<Integer>(intRnd);
		System.out.println("List 자료 출력.....");
		
		for(int i=0; i<intRndList.size(); i++) {
			System.out.println(intRndList.get(i));
		}
		
		for(Integer num : intRndList) {
			System.out.print(num + " ");
		}
	}
}
