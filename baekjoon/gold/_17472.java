import java.io.*;
import java.util.*;
/*
 * 문제: 17472 다리 만들기2
 * 난이도: Gold1
 * 알고리즘: bfs + dfs + mst
 * 풀이 방법: 1. 각 섬에 대해 넘버링 진행 (bfs 사용), list[i] => i번 섬을 이루는 블록을 삽입
 * 			2. 모든 섬에 대해 각 블록에서 직선으로 만들 수 있는 간선(다리)를 탐색하여 pq에 삽입
 * 				pq 는 다리의 길이(가중치)를 오름차순으로 정렬
 * 			3. kruskal 알고리즘 사용하여 mst 찾기
 * 				=> 최종적으로 다리 가중치 합이 0이거나 다리의 수가 전체 섬의 수 - 1이 아니면
 * 					모든 섬 연결이 불가능한 것이므로 -1 리턴
 */
public class _17472 {

	static class Block {
		int x, y;
	
	    public Block(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
	
	static class Mst_Block implements Comparable<Mst_Block>{
		int b1, b2;
		int len;
		
		public Mst_Block(int b1, int b2, int len) {
			this.b1 = b1;
			this.b2 = b2;
			this.len = len;
		}
		
		@Override
		public int compareTo(Mst_Block o) {
			return this.len - o.len; // 길이 오름차순
		}
	}

	static int N, M;
	static int[][] board;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int ans;
	static boolean[][] visited;
	static ArrayList<Block>[] list;
	static PriorityQueue<Mst_Block> pq;
	static int[] parent;
	public static void main(String[] args) throws Exception {
	    // TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String[] line = br.readLine().split(" ");
	    N = Integer.parseInt(line[0]); // 격자판의 크기
	    M = Integer.parseInt(line[1]);
        board = new int[N][M];
        ans = Integer.MAX_VALUE;

        // 격차판 상태 입력
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(line[j]); // 1은 육지
            }
        }
        
        // 섬마다 숫자 부여 bfs
        list = new ArrayList[7];
        visited = new boolean[N][M];
        int num = 1;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(!visited[i][j] && board[i][j] == 1) {
        			list[num] = new ArrayList<>();
        			bfs(i, j, num);
        			num++;
        		}
        	}
        }
        
        // 각 섬을 연결할 수 있는 방법을 모두 구함
        pq = new PriorityQueue<>(); // 간선 가중치(길이) 오름차순 정렬 삽입
        for(int i=1; i<num; i++) {
        	for(int j=0; j<list[i].size(); j++) {
        		Block b = list[i].get(j);
        		for(int k=0; k<4; k++) {
        			findBridge(b.x, b.y, i, k, -1);
        		}
        	}
        }
        System.out.println(kruskal(num));
	}
	
	public static void bfs(int x, int y, int num) {
		Queue<Block> q = new LinkedList<>();
        q.add(new Block(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Block cur = q.poll();
            
            board[cur.x][cur.y] = num;
            list[num].add(new Block(cur.x, cur.y));

            for(int k=0; k<4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                // 이어진 블럭
                if(board[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Block(nx, ny));
                }
            }
        }
	}
	
	public static void findBridge(int x, int y, int num, int dir, int len) {
	    // 다리 만들기
		if(board[x][y] > 0 && board[x][y] != num) {
	    	if(len >= 2) pq.offer(new Mst_Block(num, board[x][y], len));
	    	return;
	    }
		
		int nx = x + dx[dir];
	    int ny = y + dy[dir];
	    if(nx < 0 || nx >= N || ny < 0 || ny >= M) return;
	    if(board[nx][ny] == num) return;
	    findBridge(nx, ny, num, dir, len+1);
	}
	
	public static int kruskal(int num) {
		parent = new int[num];
		for(int i=1; i<num; i++) {
			parent[i] = i;
		}
		
		boolean[] link = new boolean[num];
		int result = 0;
		int bridge = 0;
		while(!pq.isEmpty()) {
			Mst_Block cur = pq.poll();
			int p1 = find(cur.b1);
			int p2 = find(cur.b2);
			
			// 부모 노드가 다르면 합치기 (연결)
			if(p1 != p2) {
				union(p1, p2);
				link[cur.b1] = true;
				link[cur.b2] = true;
				result += cur.len;
				bridge++; // 다리 개수 카운트 
			}
		}
		
		if(result == 0) return -1;
		if(bridge != num-2) return -1; // 전체 노드의 개수 - 1 이여야 함
		return result;
	}
	
	public static int find(int node) {
		if(parent[node] == node) return node;
		else return parent[node] = find(parent[node]);
	}
	
	public static void union(int n1, int n2) {
		parent[n1] = n2;
	}
}
