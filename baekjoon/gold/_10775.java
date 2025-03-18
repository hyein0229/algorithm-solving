package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 문제: 10775 공항
 * 난이도: Gold 2
 * 알고리즘: 그리디, 유니온-파인드
 * 풀이 방법: 그리디 => 최대한 g번 게이트에 도킹하기
 * 		 만약 도킹이 이미 된 게이트라면? 유니온-파인드를 통해 다음 가야 할 게이트를 저장, 경로 압축
 */
public class _10775 {
	
	static int G;
	static int P;
	static int[] parent;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    G = Integer.parseInt(br.readLine());
	    P = Integer.parseInt(br.readLine());
	    parent = new int[G+1];
	    ans = 0;
	    
	    for(int i=1; i<=G; i++) {
	    	parent[i] = i;
	    }
	    
	    for(int i=0; i<P; i++) {
	    	int g = Integer.parseInt(br.readLine());
	    	int targetGate = find(g);
	    	
	    	if(targetGate == 0) break;
    		ans++;
    		union(targetGate, targetGate-1);
	    }
	    System.out.println(ans);
	}
	
	public static int find(int v) {
		if(parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return;
		parent[a] = b;
	}
}
