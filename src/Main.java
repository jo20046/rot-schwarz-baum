import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("data-small.csv")));
        Tree tree = new Tree();
        for (String line; (line = bufferedReader.readLine()) != null; ) {
            Node newNode = new Node(line);
            tree.insert(newNode);
        }
        bufferedReader.close();


//
//        tree.fixColors();
//        tree.print(TraversalEnum.PREORDER);
//        System.out.println();
//        tree = tree.rotateCounterClockwise();
//        tree.print(TraversalEnum.PREORDER);
        tree = tree.rotateCounterClockwise();
        tree.display();
    }
}
