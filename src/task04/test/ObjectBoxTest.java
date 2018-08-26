package task04.test;

import task04.main.ObjectBox;

import java.util.List;

public class ObjectBoxTest {
    public static void main(String[] args) {
        Integer[] arg = {5, 10, 15};
        ObjectBox box = new ObjectBox(arg);
        assert(30 == box.summator());

        List<Integer> list = box.splitter(5);
        assert (1 == list.get(0));
        assert (2 == list.get(1));
        assert (3 == list.get(2));
    }

}
