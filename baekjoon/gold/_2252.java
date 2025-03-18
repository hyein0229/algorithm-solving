import java.io.*;
import java.util.*;
/*
 * 문제: 2252 줄 세우기
 * 난이도: Gold3
 * 알고리즘: 위상 정렬
 * 풀이 방법: 위상 정렬을 사용한다. 무조건 A는 B 앞에 와야 하므로 인접리스트를 만들어 
 * 			ad[A].add(B) 하여 a -> b 인 학생 번호를 모두 저장한다.
 * 			진입 차수가 0인 학생부터 큐에 넣은 후, 하나씩 꺼내어 인접한 학생의 진입 차수를 -1 하고,
 * 			다시 진입 차수가 0이 된 학생들을 큐에 넣는 것을 큐가 빌 때까지 반복한다.
 */
public class _2252 {
	
	static int N, M;
	static ArrayList[] adList;
	static int[] students;
	static Queue<Integer> q;
	static List<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); // 학생의 수
		M = Integer.parseInt(line[1]); // 키 비교 횟수
		q = new LinkedList<>();
		students = new int[N+1];
		adList = new ArrayList[N+1];
		list = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			adList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			// a 가 b 를 앞선다.
			// a -> b 
			students[b]++;
			adList[a].add(b);
		}
		
		// 진입 차수가 0인 학생 번호 삽입
		for(int i=1; i<=N; i++) {
			if(students[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			list.add(cur);
			
			// 자신보다 큰 학생들의 진입 차수 1 감소
			for(int i=0; i<adList[cur].size(); i++) {
				int num = (int) adList[cur].get(i);
				if(students[num] > 0) {
					students[num]--;
					if(students[num] == 0)  // 진입 차수가 0이 된 학생을 큐에 넣기
						q.add(num);
				}
			}
		}
		
		if(q.isEmpty()) {
			for(int i=0; i<list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
	}
}
