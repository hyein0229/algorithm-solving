package baekjoon;

import java.io.*;
/*
문제: 2590 색종이
난이도: Gold 4
알고리즘: 구현, 그리디
풀이 방법: 직접 그려서 규칙을 찾았다.
        1. 6번 색종이 -> 한 판의 크기이므로 6번의 갯수만큼 판 추가
        2. 5번 색종이 -> 한 판당 하나만 사용하고 나머지는 1번 색종이로 채움
        3. 4번 색종이 -> 한 판당 하나만 사용 가능, 나머지 2번 색종이(최대 5개) 채운 후 1번으로 채움
        4. 3번 색종이 -> 최대 4개 사용 가능,
                        1개 사용 시 2번은 최대 5개
                        2개 사용 시 2번은 최대 3개
                        3개 사용 시 2번은 최대 1개
                        그리고 남은 칸은 1번으로 채움
        5. 2번 색종이 -> 최대 9개 사용 후, 나머지 1번으로 채우기
        6. 1번을 판의 크기 36으로 나눈 값의 ceil 값을 추가
 */
public class _2590 {
    static int[] paper = new int[7];
    static int ans = 0;
    static int emptyCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 각 종류 별로 장수 입력
        for(int i=1; i<=6; i++) {
            int num = Integer.parseInt(br.readLine());
            paper[i] = num;
        }

        // 6번 색종이 사용
        if(paper[6] > 0) {
            ans += paper[6]; // 6번 색종이는 한 판을 혼자 다 사용
        }

        // 5번 색종이 사용
        if(paper[5] > 0) {
            ans += paper[5];
            paper[1] = Math.max(0, paper[1] - (11 * paper[5])); // 1번 색종이의 사용 개수
        }

        while(paper[4] > 0) {
            ans++; // 한 판 추가
            paper[4]--;
            emptyCnt = 20; // 남은 칸 수
            emptyCnt -= 4 * Math.min(5, paper[2]); // 2번 색종이 최대 5개까지 가능
            paper[2] = Math.max(0, paper[2] - 5);
            paper[1] = Math.max(0, paper[1] - emptyCnt); // 남은 칸에 1번 사용
        }

        while(paper[3] > 0) {
            ans++;
            emptyCnt = 36 - 9 * Math.min(4, paper[3]); // 3번 색종이 최대 4개 사용
            paper[3] = Math.max(0, paper[3] - 4);
            if (emptyCnt / 9 == 1) {
                emptyCnt -= 4 * Math.min(1, paper[2]);
                paper[2] = Math.max(0, paper[2] - 1); // 2번 색종이 사용
            } else if(emptyCnt / 9 == 2) {
                emptyCnt -= 4 * Math.min(3, paper[2]);
                paper[2] = Math.max(0, paper[2] - 3);
            } else if(emptyCnt / 9 == 3) {
                emptyCnt -= 4 * Math.min(5, paper[2]);
                paper[2] = Math.max(0, paper[2] - 5);
            }
            paper[1] = Math.max(0, paper[1] - emptyCnt); // 남은 칸에 1번 사용
        }

        while(paper[2] > 0) {
            ans++;
            emptyCnt = 36 - 4 * Math.min(9, paper[2]);
            paper[2] = Math.max(0, paper[2] - 9);
            paper[1] = Math.max(0, paper[1] - emptyCnt);
        }

        ans += Math.ceil(paper[1] / (double)36);
        System.out.println(ans);
    }
}
