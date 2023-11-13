package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문제: 1992 쿼드트리
난이도: Silver 1
알고리즘: 분할정복, 재귀
풀이 방법: 현재 사이즈의 영역을 모두 탐색하여 모두 같은 숫자인지 아닌지를 확인하고 모두 같으면 압축하고 아니면 분할한다.
        분할 시엔 사이즈를 절반으로 잘라 4개의 영역으로 분할하여 각 영역이 다시 압축이 가능한지 확인하고, 압축이 안된다면 압축이 가능할 때까지 분할을 계속 진행한다.
        분할하여 구한 각 영역의 압축 결과를 합쳐 최종 결과를 구한다.
 */
public class _1992 {

    static int[][] graph;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 영상의 크기

        // 영상의 각 점 입력
        graph = new int[n][n];
        for(int i=0; i<n; i++) {
            String row = br.readLine();
            for(int j=0; j<n; j++) {
                graph[i][j] = row.charAt(j) - '0';
            }
        }

        quadTree(n, 0, 0);
        System.out.println(result);
    }

    public static void quadTree(int size, int x, int y) {

        // 0 또는 1로 한번에 압축할 수 있는지 확인
        if(compress(x, y, size)) {
            result.append(graph[x][y]);
            return;
        }

        // 압축이 안되는 경우 사이즈를 절반으로 나누어 압축
        int m = size / 2;
        result.append("(");

        quadTree(m, x, y); // 왼쪽 위
        quadTree(m, x, y+m); // 오른쪽 위
        quadTree(m, x+m, y); // 왼쪽 아래
        quadTree(m, x+m, y+m); // 오른쪽 아래

        result.append(")");
    }

    // 압축이 가능한지 확인하는 함수
    public static boolean compress(int x, int y, int size) {
        int cur = graph[x][y]; // 현재 값, 0 또는 1

        // 해당 공간의 문자가 모두 0이나 1로 채워져 있는지 확인
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if(cur != graph[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
