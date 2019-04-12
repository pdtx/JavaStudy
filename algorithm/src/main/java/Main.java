import java.util.Scanner;


public class Main {

    int answer = -1;
    public  int ans(char [] pos) {
         back(1,pos,0);
        return answer;
    }

    private  void back(int index,char [] pos,int times) {
        if (isCom(pos) || index >= 10){
            if (answer == -1 )
                answer = times;
            else {
                if (answer < times)
                    answer = times;
            }
            return ;
        }

        for (int i = index;i <= 9; i++) {
            if (pos[i] == 'U') {
                change(i ,pos ,'D');
                back(i + 1,pos,times + 1);
                change(i,pos,'U');
            }
        }
    }

    private   boolean  isCom(char [] pos) {
        for (int i =1;i<=9;i++){
            if (pos[i] == 'U')
                return false;
        }
        return true;
    }

    private void change(int p ,char[] pos, char ch) {
        if (getShang(p) != -1)
            pos[getShang(p)] = ch;

        if (getXia(p) != -1)
            pos[getXia(p)] = ch;

        if (getZuo(p) != -1)
            pos[getZuo(p)] = ch;

        if (getYou(p) != -1)
            pos[getYou(p)] = ch;
    }

    private int getShang(int pos) {
        if (pos <= 3 )
            return -1;
        else
            return pos - 3;
    }

    private int getXia(int pos) {
        if (pos >= 7 )
            return -1;
        else
            return pos + 3;
    }

    private int getZuo(int pos) {
        if (pos % 3 == 1)
            return -1;
        else
            return pos - 1;
    }

    private int getYou(int pos) {
        if (pos % 3 == 0)
            return -1;
        else
            return pos + 1;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] POS = new char[10];
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        String line3 = sc.nextLine();
        POS[0] = '0';
        int i = 1;
        for (char ch : line1.toCharArray())
            POS[i++] = ch;
        for (char ch : line2.toCharArray())
            POS[i++] = ch;
        for (char ch : line3.toCharArray())
            POS[i++] = ch;

        System.out.println(new Main().ans(POS));
    }
}