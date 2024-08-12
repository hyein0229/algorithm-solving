import java.util.*;
/*
문제: 5653 [모의 sw 역량테스트] 줄기세포배양
알고리즘: 구현
풀이 방법: 해당 칸에 이미 줄기 세포가 있다면 번식이 불가능 하므로 체크를 해주어야 한다. Pos 라는 클래스로 생성하여 x, y 좌표 데이터를
        가지도록 하고 Object 의 equals() 를 오버라이드하여 Set 에서 중복 제거 시 비교에 사용될 수 있도록 하였다.
        이렇게 하면 따로 좌표 기록을 위한 2차원 배열 생성을 안해도 되며, 좌표에 대한 처리가 불필요하여 실수가 줄어들 수 있다.
        줄기 세포가 하나의 셀에 동시 번식하려는 경우 생명력 x가 높은 줄기 세포가 차지하게 되므로 PriorityQueue 를 사용하여
        생명력이 높은 순대로 정렬되도록 하였다. 이때 Cell 클래스가 Comparable 을 implements 하여 재정의하여 생명력 내림차순으로 정렬되도록 하였다.
        현재 상태의 pq 에서 하나씩 셀을 poll 하면서, 비활성화 상태인지, 활성화 상태인지에 따른 처리를 해주고 현재 세포로부터 새롭게 번식된 줄기 세포와
        죽지 않은 세포를 다음 상태의 pq 에 삽입하여 갱신할 수 있도록 하였다.
 */
public class SWEA_5653 {

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Cell implements Comparable<Cell> {
        int X; // 생명력
        int breedTime; // 번식된 시점
        Pos pos; // 줄기 세포 위치
        boolean active; // 활성화, 비활성화 상태

        public Cell(int X, Pos pos, int breedTime) {
            this.X = X;
            this.pos = pos;
            this.breedTime = breedTime;
            this.active = false;
        }

        @Override
        public int compareTo(Cell o) {
            return o.X - this.X; // 생명력 내림차순 정렬
        }
    }

    static int N, M;
    static int K;
    static PriorityQueue<Cell> prev;
    static Set<Pos> exist;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int cellCnt; // 정답 개수
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=1; t<=T; t++) {
            N = sc.nextInt(); // 세로 크기
            M = sc.nextInt(); // 가로 크기
            K = sc.nextInt(); // 배양 시간
            prev = new PriorityQueue<>();
            exist = new HashSet<>();
            cellCnt = 0;

            // 초기 줄기 세포 상태 입력
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    int v = sc.nextInt();
                    if(v != 0) { // 줄기 세포가 존재한다면
                        Pos pos = new Pos(i, j);
                        prev.add(new Cell(v, pos, 0));
                        exist.add(pos);
                        cellCnt++;
                    }
                }
            }

            // K시간 동안 줄기 세포 번식
            for(int i=1; i<=K; i++) {
                breed(i);
            }
            System.out.println("#" + t + " " + cellCnt);
        }
    }

    public static void breed(int curTime) {
        PriorityQueue<Cell> after = new PriorityQueue<>();
        while(!prev.isEmpty()) {
            Cell cur = prev.poll();
            int X = cur.X;
            Pos curPos = cur.pos;
            int breedTime = cur.breedTime;

            // 비활성화 상태면
            if(!cur.active) {
                if(X == curTime - breedTime) { // X 시간이 지났다면 활성화
                    cur.active = true;
                }
                after.add(cur);
                continue;
            }
            // 상하좌우 번식
            for(int k=0; k<4; k++) {
                Pos nPos = new Pos(curPos.x + dx[k], curPos.y + dy[k]);
                // 줄기 세포가 존재하지 않은 곳이면 번식
                if(!exist.contains(nPos)) {
                    after.add(new Cell(X, nPos, curTime));
                    exist.add(nPos);
                    cellCnt++;
                }
            }

            // 활성화 이후 x 시간이 지나면 죽은 상태가 됨
            if((curTime - cur.breedTime) == 2 * X) {
                cellCnt--;
            } else { // 아직 살아 있는 줄기 세포면 다시 삽입
                after.add(cur);
            }
        }
        prev = after; // 전체 그리드 상태 갱신
    }
}
