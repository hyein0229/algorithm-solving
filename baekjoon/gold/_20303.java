package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/*
문제: 20303 할로윈의 양아치
난이도: Gold 3
알고리즘: 냅색, 유니온-파인드
풀이 방법: 한 아이의 사탕을 뺏으면 그의 친구의 친구까지 모두 사탕을 뺏는다
        => 유니온-파인드로 연결된 아이들을 모두 연결하여 루트만 저장
        루트인 아이들을 target으로 정하고 해당 아이의 사탕을 뺏었을 때 뺏을 수 있는 모든 사탕의 총합과
        친구들의 수를 카운트하여 배열에 저장
        그 다음, dp 냅색 알고리즘을 사용하여 최대 K-1까지 아이들을 울렸을 때 뺏을 수 있는 최대 사탕의 수 구하기
 */
public class _20303 {

    static int N, M, K;
    static int[] child;
    static int[] totalCandy;
    static int[] parent;
    static int[] cnt;
    static List<Integer> target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);
        child = new int[N+1];
        totalCandy = new int[N+1];
        parent = new int[N+1];
        cnt = new int[N+1];
        target = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }

        // 아이들이 가지고 있는 사탕의 개수 입력
        line = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            child[i] = Integer.parseInt(line[i-1]);
        }

        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            union(a, b); // 친구 관계
        }

        for(int i=1; i<=N; i++) {
            find(i);
        }

        for(int i=1; i<=N; i++) {
            if(totalCandy[parent[i]] == 0) {
                target.add(parent[i]);
            }
            totalCandy[parent[i]] += child[i];
            cnt[parent[i]]++;
        }

        int[][] dp = new int[target.size()+1][K];
        for(int i=1; i<target.size()+1; i++){
            for(int j=0; j<K; j++) {
                int num = target.get(i-1); // 뺏을 대상
                if(j-cnt[num] >= 0) { // K명 미만이어야 함
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cnt[num]] + totalCandy[num]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[target.size()][K-1]);
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
