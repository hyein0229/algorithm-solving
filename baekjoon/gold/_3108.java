package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
문제: 3108 로고
난이도: Gold 2
알고리즘: dfs
풀이 방법: 서로 겹치는 직사각형은 선 한번에 그릴 수 있으므로 겹치는 직사각형 묶음을 하나의 집합으로 해주면 된다.
        먼저, 직사각형을 모두 입력받고 서로 겹치는 지 체크하여 인접리스트를 만들어준다.
        겹치는 조건은 아래 조건 2개를 모두 체크하여 모두 해당되지 않으면 겹치는 직사각형으로 판단한다.
        1. y 좌표, x 좌표를 비교하여 서로 접점이 없는지 확인
        2. 한 직사각형이 다른 직사각형을 내부에 포함하고 있는지 확인
        위 조건에 해당되지 않으면 인접 리스트에 해당 직사각형 번호를 담아준다.
        그 다음, dfs 를 수행하면서 직사각형 집합의 개수가 몇개인지 판단한다. 연결된 직사각형 집합의 개수가 정답이다.
        주의할 점은 거북이 시작 위치가 (0, 0) 이므로 첫번째 직사각형을 (0, 0, 0, 0) 으로 가정하고 포함하여 dfs를 수행해주어야 한다.
 */
public class _3108 {

    static int N;
    static int[][] points;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new int[N+1][4];
        visited = new boolean[N+1];
        graph = new ArrayList[N+1];
        String[] line;

        for(int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++) {
            line = br.readLine().split(" ");
            points[i][0] = Integer.parseInt(line[0]);
            points[i][1] = Integer.parseInt(line[1]);
            points[i][2] = Integer.parseInt(line[2]);
            points[i][3] = Integer.parseInt(line[3]);
        }

        // 겹치는(인접한) 직사각형 구하기
        for(int i=0; i<=N; i++) {
            for(int j=i+1; j<=N; j++) {
                if(isOverlap(i, j)) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        dfs(0);
        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                dfs(i);
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static void dfs(int i) {
        visited[i] = true;
        for(int next : graph[i]) {
            if(visited[next]) continue;
            dfs(next);
        }
    }

    private static boolean isOverlap(int i, int j) {
        if(points[i][3] < points[j][1] || points[i][1] > points[j][3]) { // y 좌표 비교
            return false;
        }
        if(points[i][2] < points[j][0] || points[i][0] > points[j][2]) { // x 좌표 비교
            return false;
        }
        // 한 직사각형이 다른 것을 내포하는지 확인
        if((points[i][0] < points[j][0] && points[i][2] > points[j][2])
                && (points[i][1] < points[j][1] && points[i][3] > points[j][3])) {
            return false;
        }
        if((points[j][0] < points[i][0] && points[j][2] > points[i][2])
                && (points[j][1] < points[i][1] && points[j][3] > points[i][3])) {
            return false;
        }
        return true;
    }
}
