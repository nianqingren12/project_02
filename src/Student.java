import java.util.Scanner;

class GradeManager {
    private float[] grades;
    private int count;

    public GradeManager(int size) {
        grades = new float[size];
        count = 0;
    }

    public void addGrade(float grade) {
        if (count < grades.length) {
            grades[count++] = grade;
            System.out.println("成绩添加成功！");
        } else {
            System.out.println("成绩已存满！");
        }
    }

    public void showAllGrades() {
        if (count == 0) {
            System.out.println("暂无成绩记录");
            return;
        }

        System.out.println("\n===== 成绩列表 =====");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + grades[i]);
        }
        System.out.println("==================");
    }
}

class UserAuth {
    private String password;

    public UserAuth(String password) {
        this.password = password;
    }

    public boolean login(String inputPassword) {
        return password.equals(inputPassword);
    }
}

public class Student {

    public static void main(String[] args) {
        // 系统设置
        String correctUsername = "赵昌昊";
        String correctPassword = "2024061142";
        float[] grades = new float[500];  // 用来存成绩
        int gradeCount = 0;  // 记录已有成绩数量

        // 显示欢迎界面
        System.out.println("*****************");
        System.out.println("* 学生成绩管理系统 *");
        System.out.println("******************");

        // 用户登录
        Scanner input = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String username = input.nextLine();
        System.out.print("请输入密码：");
        String password = input.nextLine();

        // 检查登录
        if(!username.equals(correctUsername)) {
            System.out.println("用户名错误！");
            return;
        }
        if(!password.equals(correctPassword)) {
            System.out.println("密码错误！");
            return;
        }

        System.out.println("登录成功！");

        // 主菜单循环
        while(true) {
            // 显示菜单
            System.out.println("\n====== 主菜单 ======");
            System.out.println("1. 添加成绩");
            System.out.println("2. 插入成绩");
            System.out.println("3. 查找成绩");
            System.out.println("4. 排序成绩");
            System.out.println("5. 删除成绩");
            System.out.println("6. 显示所有成绩");
            System.out.println("0. 退出系统");
            System.out.print("请选择操作：");

            // 检查输入是否为整数
            while (!input.hasNextInt()) {
                System.out.println("错误：请输入数字！");
                input.nextLine(); // 清空输入缓冲区
                System.out.print("请选择操作：");
            }
            int choice = input.nextInt();
            input.nextLine(); // 清除缓冲区残留的换行符

            switch(choice) {
                case 1:  // 添加成绩
                    System.out.print("请输入要添加的成绩：");
                    while (!input.hasNextFloat()) {
                        System.out.println("错误：请输入数字！");
                        input.nextLine(); // 清空输入缓冲区
                        System.out.print("请输入要添加的成绩：");
                    }
                    float newGrade = input.nextFloat();
                    input.nextLine(); // 清除缓冲区残留的换行符
                    grades[gradeCount] = newGrade;
                    gradeCount++;
                    System.out.println("添加成功！");
                    break;

                case 2:  // 插入成绩
                    System.out.print("请输入要插入的位置(0-" + gradeCount + ")：");
                    while (!input.hasNextInt()) {
                        System.out.println("错误：请输入数字！");
                        input.nextLine(); // 清空输入缓冲区
                        System.out.print("请输入要插入的位置(0-" + gradeCount + ")：");
                    }
                    int pos = input.nextInt();
                    input.nextLine(); // 清除缓冲区残留的换行符
                    if(pos < 0 || pos > gradeCount) {
                        System.out.println("位置无效！");
                        break;
                    }

                    System.out.print("请输入要插入的成绩：");
                    while (!input.hasNextFloat()) {
                        System.out.println("错误：请输入数字！");
                        input.nextLine(); // 清空输入缓冲区
                        System.out.print("请输入要插入的成绩：");
                    }
                    newGrade = input.nextFloat();
                    input.nextLine(); // 清除缓冲区残留的换行符

                    // 把后面的成绩往后移
                    for(int i = gradeCount; i > pos; i--) {
                        grades[i] = grades[i-1];
                    }

                    grades[pos] = newGrade;
                    gradeCount++;
                    System.out.println("插入成功！");
                    break;

                case 3:  // 查找成绩
                    System.out.print("请输入要查找的成绩：");
                    while (!input.hasNextFloat()) {
                        System.out.println("错误：请输入数字！");
                        input.nextLine(); // 清空输入缓冲区
                        System.out.print("请输入要查找的成绩：");
                    }
                    float searchGrade = input.nextFloat();
                    input.nextLine(); // 清除缓冲区残留的换行符
                    boolean found = false;

                    for(int i = 0; i < gradeCount; i++) {
                        if(grades[i] == searchGrade) {
                            System.out.println("找到成绩，位置：" + i);
                            found = true;
                        }
                    }

                    if(!found) {
                        System.out.println("没有找到这个成绩！");
                    }
                    break;

                case 4:  // 排序成绩 - 使用简单的冒泡排序
                    for(int i = 0; i < gradeCount-1; i++) {
                        for(int j = 0; j < gradeCount-i-1; j++) {
                            if(grades[j] > grades[j+1]) {
                                // 交换成绩
                                float temp = grades[j];
                                grades[j] = grades[j+1];
                                grades[j+1] = temp;
                            }
                        }
                    }
                    System.out.println("成绩排序完成！");
                    break;

                case 5:  // 删除成绩
                    System.out.print("请输入要删除的成绩：");
                    while (!input.hasNextFloat()) {
                        System.out.println("错误：请输入数字！");
                        input.nextLine(); // 清空输入缓冲区
                        System.out.print("请输入要删除的成绩：");
                    }
                    float deleteGrade = input.nextFloat();
                    input.nextLine(); // 清除缓冲区残留的换行符
                    int deleteCount = 0;

                    for(int i = 0; i < gradeCount; i++) {
                        if(grades[i] == deleteGrade) {
                            // 把后面的成绩往前移
                            for(int j = i; j < gradeCount-1; j++) {
                                grades[j] = grades[j+1];
                            }
                            gradeCount--;
                            deleteCount++;
                            i--;  // 因为移了一个，所以要检查当前位置的新成绩
                        }
                    }

                    if(deleteCount > 0) {
                        System.out.println("成功删除了 " + deleteCount + " 个成绩");
                    } else {
                        System.out.println("没有找到这个成绩！");
                    }
                    break;

                case 6:  // 显示所有成绩
                    if(gradeCount == 0) {
                        System.out.println("还没有任何成绩记录！");
                    } else {
                        System.out.println("所有成绩：");
                        for(int i = 0; i < gradeCount; i++) {
                            System.out.println((i+1) + ". " + grades[i]);
                        }
                    }
                    break;

                case 0:  // 退出系统
                    System.out.println("谢谢使用，再见！");
                    return;

                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
}