package week6.greedy.gayeong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sports {

    public int solution(int n, int[] lost, int[] reserve) {

        Arrays.sort(lost);
        Arrays.sort(reserve);
        ArrayList<Integer> lostList = new ArrayList<>();
        ArrayList<Integer> reserveList = new ArrayList<>();

        //1. 도난 당했으면서, 여분도 없는 학생
        for (int student : lost) {
            if (!contains(reserve, student)) {
                lostList.add(student);
            }
        }
        //2. 도난 당했으나 여분이 있는 학생은 reserve에서 빼기
        for (int student : reserve) {
            if (!contains(lost, student)) {
                reserveList.add(student);
            }
        }

        int answer = n - lostList.size();

        for (int student : lostList) {
            int borrow = -1;
            for (int clothes : reserveList) {
                if (Math.abs(clothes - student) == 1) {
                    borrow = clothes;
                    break;
                }
            }

            if (borrow != -1) {
                answer++;
                reserveList.remove(Integer.valueOf(borrow));
            }
        }

        return answer;
    }

    private boolean contains(int[] arr, int value) {
        for (int i : arr) {
            if (value == i) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int lostCount = sc.nextInt();
        int[] lost = new int[lostCount];
        for (int i = 0; i < lostCount; i++) {
            lost[i] = sc.nextInt();
        }

        int reserveCount = sc.nextInt();
        int[] reserve = new int[reserveCount];
        for (int i = 0; i < reserveCount; i++) {
            reserve[i] = sc.nextInt();
        }

        sc.close();

        Sports sports = new Sports();
        int result = sports.solution(n, lost, reserve);
        System.out.println(result);
    }
}
