package baekjoon;

import java.io.*;
import java.util.*;

/*
문제: 16235 나무 재테크
난이도: Gold 3
알고리즘: 구현, 자료 구조, 시뮬레이션
풀이 방법: 문제 따라 구현만 하는 되는 문제라 알고리즘 자체엔 어려움은 없었고 시간 제한을 위해 자료 구조 사용을 신경써주면 되었다.
        나이 어린 순으로 양분을 먹어야 하므로 새로운 나무들을 추가해줄 때 맨 앞에 추가해주면 되는 데, 이때 리스트는 O(N) 이므로 덱을 사용하여 O(1) 이 걸리도록 한다.
        remove 메소드를 사용하면 무조건 O(N)이 걸리므로 시간 초과가 일어나 remove 를 사용하지 않고 아예 새로운 리스트를 선언하여 죽은 나무를 넣어주어 구분할 수 있도록 하였다.
 */
public class _16235 {

    public static class Tree {
        int x;
        int y;
        int z; // 나이

        public Tree(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        // 나이 1 증가
        public void increase() {
            this.z += 1;
        }
    }

    static int N, M, K;
    static int[][] A; // 각 칸에 추가될 양분
    static int[][] board; // 땅 상태
    static Deque<Tree> trees = new LinkedList<>();
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);
        A = new int[N][N];
        board = new int[N][N];

        // 처음 양분을 5로 초기화
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                board[i][j] = 5;
            }
        }

        // A배열 입력
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                A[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 나무 정보 입력
        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0])-1;
            int y = Integer.parseInt(line[1])-1;
            int z = Integer.parseInt(line[2]);
            trees.add(new Tree(x, y, z));
        }

        // K년 동안의 상태 갱신
        int t = 0;
        while(t < K) {
            // 봄, 여름, 양분 먹기
            eat();
            // 가을, 번식
            reproduce();
            // 겨울, 로봇
            addNutrient();
            t++;
        }
        System.out.println(trees.size());
    }

    public static void eat() {
        Deque<Tree> next = new LinkedList<>(); // 살아 있는 나무
        List<Tree> dead = new ArrayList<>(); // 죽은 나무
        // 자신의 나이만큼 양분 먹고 나이 1 증가하기
        for(Tree tree : trees) {
            // 양분이 나이 이상이면 양분을 나이만큼 양분 먹음
            if(board[tree.x][tree.y] >= tree.z) {
                board[tree.x][tree.y] -= tree.z; // 나이만큼 감소
                tree.increase(); // 나이 증가
                next.addLast(tree);
            } else {
                dead.add(tree);
            }
        }

        // 죽은 나무의 나이를 2로 나눈 만큼 해당 칸의 양분 추가
        for(int i=0; i<dead.size(); i++) {
            Tree tree = dead.get(i);
            board[tree.x][tree.y] += tree.z / 2;
        }
        trees = next; // 살아 있는 나무들을 갱신
    }

    // 번식
    public static void reproduce() {
        List<Tree> newTrees = new ArrayList<>(); // 새로 생기는 나무
        for(Tree tree : trees) {
            int age = tree.z; // 나이
            int x = tree.x;
            int y = tree.y;
            if(age > 0 && age % 5 == 0) { // 5의 배수이면
                for(int j=0; j<8; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue;
                    }
                    newTrees.add(new Tree(nx, ny, 1));
                }
            }
        }

        for(int i=0; i<newTrees.size(); i++) {
            trees.addFirst(newTrees.get(i));
        }
    }

    // 로봇이 땅에 양분 추가
    public static void addNutrient() {
        // 양분 추가
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                board[i][j] += A[i][j];
            }
        }
    }
}
