package tta.intel.eus.senecapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 26/01/17.
 */
public class Parejas {
    private int total;
    private List<Pareja> board = new ArrayList<Pareja>();

    public Parejas() {
    }

    public Parejas(int total, List<Pareja> board) {
        this.total = total;
        this.board = board;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Pareja> getBoard() {
        return board;
    }

    public void setBoard(List<Pareja> board) {
        this.board = board;
    }

    public static class Pareja implements Serializable {
        private List<String> array;
        private List<String> pair1;
        private List<String> pair2;
        private List<String> pair3;

        public Pareja() {
        }

        public Pareja(List<String> array, List<String> pair1, List<String> pair2, List<String> pair3) {
            this.array = array;
            this.pair1 = pair1;
            this.pair2 = pair2;
            this.pair3 = pair3;
        }

        public List<String> getArray() {
            return array;
        }

        public void setArray(List<String> array) {
            this.array = array;
        }

        public List<String> getPair1() {
            return pair1;
        }

        public void setPair1(List<String> pair1) {
            this.pair1 = pair1;
        }

        public List<String> getPair2() {
            return pair2;
        }

        public void setPair2(List<String> pair2) {
            this.pair2 = pair2;
        }

        public List<String> getPair3() {
            return pair3;
        }

        public void setPair3(List<String> pair3) {
            this.pair3 = pair3;
        }
    }
}
