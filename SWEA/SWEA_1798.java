package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 1798. 범준이의 제주도 여행 계획
난이도: D5
알고리즘: dfs + 백트래킹 이용
풀이 방법: dfs 를 사용하여 모든 방문 순서에 대해 완전 탐색을 이용
        i=1 부터 N까지 탐색, 관광포인트면 이동 소요시간, 놀이 시간, 만족도를 더하고 방문 체크 후 재귀 호출
        day < m 까진 관광 후 호텔로 이동해야 함. 호텔은 중복 방문이 가능하므로 방문 체크를 하지 않음
        재귀 호출로 넘어온 cur 이 호텔이면 day+1 로 다음 일자 재귀 호출하고 이때, 이동 소요시간은 0으로 초기화
        이때, 공항은 마지막날에만 방문 가능하므로 A일 때는 가지 않도록 하고, 따로 day = m 일 때
        현재까지의 경로에서 공항까지 이동했을 때 540분을 넘지 않으면 가도록 처리해주고 정답 갱신
 */
public class SWEA_1798 {

    static int N, M;
    static int[][] dist;
    static Map<Integer, int[]> touristPoint;
    static char[] arr;
    static int airport;
    static boolean[] visited;
    static int[] route;
    static int[] ansRoute;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] line;

        for(int test_case=1; test_case<=T; test_case++) {
            line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]); // 지점 개수
            M = Integer.parseInt(line[1]); // 휴가 기간
            dist = new int[N+1][N+1];
            arr = new char[N+1];
            touristPoint = new HashMap<>();
            visited = new boolean[N+1];
            route = new int[N+1];
            ans = 0;
            ansRoute = new int[N+1];

            // 지점 간의 이동 소요 시간 정보 입력
            for(int i=1; i<N; i++) {
                line = br.readLine().split(" ");
                int k=0;
                for(int j=i+1; j<=N; j++) {
                    dist[i][j] = Integer.parseInt(line[k++]);
                    dist[j][i] = dist[i][j];
                }
            }

            for(int i=1; i<N; i++) {
                dist[N][i] = dist[i][N];
            }

            // i번째 지점에 대한 정보 입력
            for(int i=1; i<=N; i++) {
                line = br.readLine().split(" ");
                arr[i] = line[0].charAt(0);
                if(line[0].equals("A")) {
                    airport = i;
                } if(line[0].equals("P")) { // 관광포인트
                    int t = Integer.parseInt(line[1]); // 놀이 소요 시간
                    int s = Integer.parseInt(line[2]); // 만족도
                    touristPoint.put(i, new int[]{t, s});
                }
            }

            move(airport, 0, 0, 0, 1); // 시작점, 방문 순서, 이동 소요 시간, 현재 여행 일자

            if(ans == 0) {
                System.out.println("#" + test_case + " " + 0);
            } else {
                // 경로 출력
                System.out.print("#" + test_case + " " + ans + " ");
                for(int i=0; i<ansRoute.length; i++) {
                    System.out.print(ansRoute[i] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void move(int cur, int seq, int totalTime, int satisfy, int day) {

        // 마지막 M일 차 여행인 경우
        if(day == M) {
            // 공항으로 이동하였을 때 9시간을 넘는지 확인하고 넘으면 불가
            if(totalTime + dist[cur][airport] >= 540) return;

            // 최대 만족도 정답 갱신
            if(ans < satisfy) {
                ans = satisfy;
                ansRoute = new int[seq+1]; // 경로 저장
                for(int i=0; i<seq; i++) {
                    ansRoute[i] = route[i];
                }
                ansRoute[seq] = airport;  // 마지막은 공항 도착
            }
        }

        // 모든 지점을 다 돈 경우 더 이상 탐색안해도 안해도 되므로 탈출
        if(seq == N-1) return;

        // 관광 후 호텔에 돌아왔으면 다음 일자 시작
        if(arr[cur] == 'H' && totalTime > 0 && totalTime <= 540) {
            // 그 다음 일자 여행 시작, 이동 소요 시간은 초기화
            move(cur, seq, 0, satisfy, day+1);
            return;
        }

        // 9시간 이상 소요되면 안되므로 바로 탈출
        if(totalTime >= 540) {
            return;
        }

        // 다음 이동할 지점 선택하기
        for(int i=1; i<=N; i++) {
            // 자기 자신이거나 공항이면 x
            // 공항은 항상 마지막에 돌아가야 함
            if(cur == i || arr[i] == 'A') continue;

            // 방문하지 않은 관광포인트인 경우
            if(arr[i] == 'P' && !visited[i]) {
                visited[i] = true;
                route[seq] = i;
                move(i, seq+1, totalTime + dist[cur][i] + touristPoint.get(i)[0], satisfy + touristPoint.get(i)[1], day);
                visited[i] = false;
                route[seq] = 0;
            }

            // 호텔인 경우 이동 (마지막 날이면 호텔로 돌아갈 필요 없으므로 day < M 일때만 이동)
            // 호텔은 중복이 가능하므로 방문 체크 x
            if(day < M && arr[i] == 'H') {
                route[seq] = i;
                move(i, seq+1, totalTime + dist[cur][i], satisfy, day);
                route[seq] = 0;
            }
        }
    }
}
