
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TrainSchedule {

    public static List<Integer> initTrain() {
        System.out.println("请输入火车节数:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n <= 0) {
            System.out.println("Error Input!");
            System.exit(0);
        }
        List<Integer> list = new ArrayList<>();
        System.out.println("请输入各个车厢编号:");
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        return list;
    }

    //进行火车调度的排序
    public static List<Integer> getOrder(List<Integer> origin) {
        List<Integer> res = new ArrayList<>();

        //创建三个工作栈,进行火车调度
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> s3 = new Stack<>();

        for (int i = 0; i < origin.size(); i++) {
            s1.push(origin.get(i));
        }

        //火车调度排序的过程
        while (s2.size() != origin.size()) {
            if (s2.empty()) {
                s2.push(s1.pop());
            } else if (s3.empty()) {
                while (!s1.empty() && !s2.empty() && s1.peek() >= s2.peek()) {
                    s3.push(s2.pop());
                }
                s2.push(s1.pop());
                while (!s1.empty() && s1.peek() < s2.peek()) {
                    s2.push(s1.pop());
                }
            } else {
                while (!s3.empty() && s3.peek() >= s2.peek()) {
                    s1.push(s2.pop());
                }
                s2.push(s3.pop());
                while (!s3.empty() && s3.peek() < s2.peek()) {
                    System.out.println();
                    s2.push(s3.pop());
                }
            }
        }

        //将结果出栈，加入res中
        while (!s2.empty()) {
            res.add(s2.pop());
        }
        return res;
    }


    //输出原始序列
    public static void printOrigin(List<Integer> list) {
        System.out.println("列车进入调度站顺序为:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
    //输出排序序列
    public static void printOrder(List<Integer> list) {
        System.out.println("列车开出调度站顺序为:");
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> origin= initTrain();
        List<Integer> res = getOrder(origin);
        printOrigin(origin);
        printOrder(res);
    }
}
