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

    private static HashMap<String, List<String>> readTextFile(String filename)  {
        /* here's a suggestion */
        HashMap<String, List<String>> cityMap = new HashMap<>();
        try (Scanner filScan = new Scanner(new File(filename))) {
            while (filScan.hasNext()) {
                String line = filScan.nextLine();
                String[] parts = line.split(",");
                String key = parts[0];
                List<String> connects = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    connects.add(parts[i]);
                }
                cityMap.put(key,connects);
            }
        } catch (Exception e) {
            System.out.println("Cannot read file");
            System.exit(0);
        }

        return cityMap;
    }


}
