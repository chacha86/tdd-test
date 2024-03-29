package org.example;

import util.MockScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class MyApp {
    public MyApp() {

    }
    public void run(MockScanner sc) {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> contents = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        int latestId = 1;

        while(true) {
            String cmd = sc.nextLine();
            if(cmd == null || cmd.equals("exit")) {
                return;
            }

            if (cmd.equals("add")) {

                System.out.println("제목 입력 : ");
                String title = sc.nextLine();
                System.out.println("내용 입력 : ");
                String content = sc.nextLine();

                titles.add(title);
                contents.add(content);
                ids.add(latestId++);

                System.out.println("게시물이 추가되었습니다.");

            } else if (cmd.equals("list")) {

                for (int i = 0; i < titles.size(); i++) {
                    System.out.println("번호 : " + ids.get(i));
                    System.out.println("제목 : " + titles.get(i));
                }
            } else if(cmd.equals("delete")) {
                System.out.println("삭제할 게시물 번호 : ");
                int num = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < ids.size(); i++) {
                    if (ids.get(i) == num) {
                        ids.remove(i);
                        titles.remove(i);
                        contents.remove(i);
                        break;
                    }
                }
            }
        }

    }
}
