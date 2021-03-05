package ddit_high;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T09_MapTest {
	public static void main(String[] args) {
		/*
		 * Map  >> key값과 value값을 한 쌍으로 관리하는 객체
		 * 		>> key값은 중복을 허용하지 않고 순서 없음(set특징)
		 * 		>> value값은 중복을 허용
		 */
		Map<String, String> map = new HashMap<String, String>();
		
		//자료추가 >> put(key값, value값);
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1111-1111");
		
		System.out.println("map >> " + map);
		
		//자료 수정 	>> 데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장됨
		//			>> put(수정할 key값, 새로운 value값)
		map.put("addr", "서울");
		System.out.println("map >> " + map);
		
		//자료삭제 >> remove(삭제할 key값)
		map.remove("name");
		System.out.println("map >> " + map);
		
		//자료 읽기 >> get(key)
		System.out.println("addr >> " + map.get("addr"));
		System.out.println("=================================");
		
		//key값들을 읽어와 자료를 출력하는 방법
		
		//방법1. keySet()메서드 이용
		//		keySet()메서드 >> Map의 Key값들만 읽어와 Set형으로 반환
		Set<String> keySet = map.keySet();
		
		System.out.println("Iterator를 이용한 방법");
		
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("=================================");
		
		//방법2. Set형의 데이터를 '향상된 for문'으로 처리해도 된다.
		System.out.println("향상된 for문으로 처리");
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("=================================");
		
		//방법3. value값만 읽어와 출력 >> values()메서드 이용
		System.out.println("values()메서드 이용");
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("=================================");
		
		//방법4. Map에는 Entry라는 내부 클래스가 만들어져 있다. 
		//		이 EntryClass에는 key와 value라는 멤버변수로 구성되어있다.
		//		Map에서 이 EntryClass들을 Set형식으로 저장하여 관리함
		
		//엔트리 객체전체를 가져오기(가져온 에트리들을 셋형식으로 되어있음)
		//>> 엔트리셋()메서드를 이용해 가져온다
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		//가져온 엔트리객체들을 순서대로 처리하기 위해 iterator로 변환
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
	}
}
