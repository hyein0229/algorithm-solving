package baekjoon;

import java.io.*;
/*
문제: 2174 로봇 시뮬레이션
난이도: Gold 5
알고리즘: 구현, 시뮬레이션
풀이 방법: 문제를 꼼꼼하게 읽지 않아 삽질을 했다. 현재 문제에선 x가 열이되고 y가 행이 되며
        y좌표는 아래쪽부터 커지므로 북쪽으로 갈땐 증가하고 남쪽으로 갈때 감소한다.
        상하좌우 움직일 때 북쪽, 남쪽 이동 시 이를 고려하지 않아 계속 틀렸다.
        문제를 꼼꼼하게 읽자
 */
public class _2174 {

    static class Robot {
        int x, y, d;

        public Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static int A, B;
    static Robot[] robots;
    static int[][] board;
    static boolean crash = false;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1}; // 동 남 서 북

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        A = Integer.parseInt(line[0]);
        B = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        robots = new Robot[N+1];
        board = new int[B+1][A+1];

        // 로봇 입력
        for(int i=1; i<=N; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int d;
            switch (line[2]) {
                case "E":
                    d = 0;
                    break;
                case "S":
                    d = 1;
                    break;
                case "W":
                    d = 2;
                    break;
                default:
                    d = 3;
            }
            robots[i] = new Robot(x, y, d);
            board[y][x] = i;
        }

        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int num = Integer.parseInt(line[0]);
            String command = line[1];
            int times = Integer.parseInt(line[2]);
            if(!simulation(num, command, times)) {
                break;
            }
        }
        if(!crash) {
            System.out.println("OK");
        }
    }

    public static boolean simulation(int num, String command, int times) {
        Robot robot = robots[num];
        for(int i=0; i<times; i++) {
            if(command.equals("F")) {
                int nx = robot.x + dx[robot.d];
                int ny = robot.y + dy[robot.d];
                // 로봇이 벽에 충돌하는 경우
                if(nx < 1 || nx > A || ny < 1 || ny > B) {
                    crash = true;
                    System.out.println("Robot " + num + " crashes into the wall");
                    return false;
                }
                // 로봇이 있다면 충돌
                if(board[ny][nx] > 0) {
                    crash = true;
                    System.out.println("Robot " + num + " crashes into robot " + board[ny][nx]);
                    return false;
                }
                // 로봇 이동시키기
                board[robot.y][robot.x] = 0;
                board[ny][nx] = num;
                robot.x = nx;
                robot.y = ny;
            } else if(command.equals("L")) {
                robot.d = (robot.d == 0) ? 3: robot.d - 1;
            } else {
                robot.d = (robot.d + 1) % 4;
            }
        }
        return true;
    }
}
