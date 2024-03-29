import org.example.MyApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.MockScanner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyTest {
    private static final MyApp app = new MyApp();
    private final PrintStream originalOut = System.out;

    public static String run(String[] inputs) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        MockScanner sc = new MockScanner();
        sc.setInputs(inputs);
        app.run(sc);
        return outContent.toString().trim();
    }
    @Test
    @DisplayName("exit test")
    void test() throws IOException {
        String out = run(new String[]{"exit"});
//        assertTrue(out.contains("프로그램을 종료합니다."));
    }

    @Test
    @DisplayName("add test")
    void test2() throws IOException {
        String out = run(new String[]{"add", "aaa", "bbb"});
        assertThat(out).contains("제목 입력 : ").contains("내용 입력 : ").contains("게시물이 추가되었습니다.");
    }

    @Test
    @DisplayName("list test")
    void test3() throws IOException {
        String out = run(new String[]{"list"});
        assertThat(out)
                .doesNotContain("제목 입력: ")
                .doesNotContain("내용 입력: ");
    }

    @Test
    @DisplayName("list test2")
    void test4() throws IOException {
        String out = run(new String[]{"add", "aaa", "bbb", "list"});

        assertThat(out).contains("제목 입력 : ").contains("내용 입력 : ").contains("게시물이 추가되었습니다.");

        assertThat(out).contains("번호 : 1").contains("제목 : aaa");
    }

    @Test
    @DisplayName("list test3")
    void test5() throws IOException {
        String out = run(new String[]{"add", "aaa", "bbb", "add", "ccc", "ddd", "list"});

        assertThat(out).contains("제목 입력 : ").contains("내용 입력 : ").contains("게시물이 추가되었습니다.");

        assertThat(out).contains("번호 : 1").contains("제목 : aaa");
        assertThat(out).contains("번호 : 2").contains("제목 : ccc");

    }

    @Test
    @DisplayName("list test4")
    void test6() throws IOException {
        String out = run(new String[]{"add", "aaa", "bbb", "list"});

        assertThat(out).contains("제목 입력 : ").contains("내용 입력 : ").contains("게시물이 추가되었습니다.");

        assertThat(out).contains("번호 : 1").contains("제목 : aaa");
        assertThat(out).doesNotContain("번호 : 2").doesNotContain("제목 : ccc");

    }

    @Test
    @DisplayName("번호 추가")
    void test7() throws IOException {
        String out = run(new String[]{"add", "aaa", "bbb", "add", "ccc", "ddd", "list"});

        assertThat(out).contains("제목 입력 : ").contains("내용 입력 : ").contains("게시물이 추가되었습니다.");

        assertThat(out).contains("번호 : 1").contains("제목 : aaa");
        assertThat(out).contains("번호 : 2").contains("제목 : ccc");

        System.setOut(originalOut);
        System.out.println(out);
    }

    @Test
    @DisplayName("삭제. 삭제시 번호 유지")
    void test9() throws IOException {
        String out = run(new String[]{"add", "aaa", "bbb", "add", "ccc", "ddd", "add", "eee", "fff", "delete", "2", "list"});

        assertThat(out).contains("삭제할 게시물 번호 : ");
        assertThat(out).contains("번호 : 1").contains("제목 : aaa");
        assertThat(out).doesNotContain("번호 : 2").doesNotContain("제목 : ccc");
        assertThat(out).contains("번호 : 3").contains("제목 : eee");

        System.setOut(originalOut);
        System.out.println(out);
    }
}
