import java.util.*;
import java.io.*;

public class MainBFS {

    private Queue<String> q;

    public MainBFS() {
        q = new LinkedList<String>();
    }

    public static void main(String[] args) {
        String filename = args[0];

        HashMap<String,List<String>> citiesMap = readTextFile(filename);
    }

    private static HashMap<String, List<String>> readTextFile(String filename) {
        HashMap<String, List<String>> cityMap = new HashMap<>();
        BufferedReader br = new BufferedReader()

        return cityMap;
    }


}
