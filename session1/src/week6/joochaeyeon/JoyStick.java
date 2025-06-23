package week6.joochaeyeon;

public class JoyStick {
    public int joyStick(String name) {
        int answer = 0;
        int len = name.length();
        int minMove = len-1;

        for(int i=0;i<len;i++){ //상하 이동
            char c = name.charAt(i);
            int move = Math.min(c-'A', 'Z'-c+1);
            answer+=move;
        }


        for(int i=0; i<len;i++){ //좌우 이동
            int next = i+1;

            while(next < len && name.charAt(next) == 'A'){
                next++;
            }

            int move = i * 2 + len - next;
            minMove = Math.min(minMove, move);

            move =i + (len - next)*2;
            minMove = Math.min(minMove, move);
        }
        answer+=minMove;

        return answer;
    }
}