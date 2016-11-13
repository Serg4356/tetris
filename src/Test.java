/**
 * Created by Пользователь on 29.09.2016.
 */
public class Test {
    static int[][][] figure = {
            {{1, 2, 3, 4},
             {4, 5, 6, 5},
             {7, 8, 9, 5}
            },
            {{31, 32, 33},
            {34, 35, 36,4,5,6,55,4,4,3},
            {37, 38, 39}
            }
    };
    static int[][] testarr = {{1,1,1,1,1},{1,1,1,1,1}};
    public static void main(String[] args) {

            testarr[0][1] = 2;
            System.out.println(Integer.toString(testarr[0][1]));


    }
}
