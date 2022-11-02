import java.util.*;
import java.io.*;

public class MainBFS {

    private Queue<String> q;

    public MainBFS() {
        q = new LinkedList<String>();
    }

    public static void main(String[] args) {
        String filename = "MyLocations.txt";

        HashMap<String, List<String>> citiesMap = readTextFile(filename);
        System.out.println(citiesMap);
        //startCity, destinationCity, citiesMap -- arguments to bfs function, return = list of strings (startCity, destination, connections between)
        Scanner user = new Scanner(System.in);
        System.out.println("Choose a city to start from: ");
        String startCity = user.nextLine();
        System.out.println("Choose a city to go to: ");
        String destinationCity = user.nextLine();
        List<String> solution = bfs(startCity, destinationCity, citiesMap);
        System.out.println("The path is: " + solution);
    }

    private static List<String> bfs(String startCity, String destinationCity, HashMap<String, List<String>> graph) {
        ArrayList<String> path = new ArrayList<>();
        path.add(startCity);
        ArrayDeque<ArrayList<String>> q_paths = new ArrayDeque<>();
        q_paths.add(path);
        while (!q_paths.isEmpty()) {
            ArrayList<String> partialPath = q_paths.removeFirst();
            String lastCity = partialPath.get(partialPath.size() - 1);
            /*
            Some of the links are 'one-way' connections, for example, in MyLocations.txt
            Memphis connects to Clarksville, but there's no entry at all for Clarksville --
            that's why it's crashing.
            Try this: retrieve the list of connections, only execute the for-loop
            if the list is not null
            (rl)
             */
            List<String> connections = graph.get(lastCity);
            if (connections != null) {
                for (int i = 0; i < connections.size(); i++) {
                    ArrayList<String> extended_path = new ArrayList<>(partialPath);
                    String nextCity = graph.get(lastCity).get(i);
                    if (!partialPath.contains(nextCity)) {
                        extended_path.add(nextCity);
                        if (nextCity.equals(destinationCity)) {
                            return extended_path;
                        } else {
                            q_paths.add(extended_path);
                        }
                    }
                }

            }
        } //end of while loop
        return null;
    }

    private static HashMap<String, List<String>> readTextFile(String filename) {
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
                cityMap.put(key, connects);
            }
        } catch (Exception e) {
            System.out.println("Cannot read file");
            System.exit(0);
        }

        return cityMap;
    }


}
