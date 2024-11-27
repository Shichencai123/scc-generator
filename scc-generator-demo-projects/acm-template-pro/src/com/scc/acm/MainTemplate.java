package com.scc.acm;

import java.util.Scanner;

/**
 * className:  MainTemplate
 * Package:  com.scc.acm
 * Description: ACM 输入模板（多数之和）
 *
 * @Date: 2024/11/1 22:20
 * @Author: scc
 */
public class MainTemplate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            //读取输入元素个数
            int n = sc.nextInt();

            //读取数据
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            //处理问题逻辑，根据需要进行输�?
            //示例：计算数组元素的�?
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }

            System.out.println("sum" + sum);
        }
    }
}
