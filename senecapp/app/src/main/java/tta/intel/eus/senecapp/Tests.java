package tta.intel.eus.senecapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 28/01/17.
 */
public class Tests {
    private int total;
    private List<Test> test = new ArrayList<Test>();

    public Tests() {
    }

    public Tests(int total, List<Test> test) {
        this.total = total;
        this.test = test;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Test> getTest() {
        return test;
    }

    public void setTest(List<Test> test) {
        this.test = test;
    }

    public static class Test {
        private int correct;
        private String question;
        private List<String> resp;

        public Test() {
        }

        public Test(int correct, String question, List<String> resp) {
            this.correct = correct;
            this.question = question;
            this.resp = resp;
        }

        public int getCorrect() {
            return correct;
        }

        public void setCorrect(int correct) {
            this.correct = correct;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<String> getResp() {
            return resp;
        }

        public void setResp(List<String> resp) {
            this.resp = resp;
        }
    }
}
