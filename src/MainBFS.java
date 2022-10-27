import java.util.*;
import java.io.*;

public class MainBFS {

    private Queue<String> q;

    public MainBFS() {
        q = new LinkedList<String>();
    }

    public static void main(String[] args) {
        String filename = "MyLocations.txt";

        HashMap<String,List<String>> citiesMap = readTextFile(filename);
        //System.out.println(citiesMap);
        //startCity, destinationCity, citiesMap -- arguments to bfs function, return = list of strings (startCity, destination, connections between)
        String startCity = citiesMap.keySet().toArray()[0].toString();
        String destinationCity = citiesMap.keySet().toArray()[1].toString();
        ArrayList<String> visited = new ArrayList<>();
        bfs(startCity, destinationCity, citiesMap, visited);
    }

    private static void bfs(String startCity, String destinationCity, HashMap<String, List<String>> graph, ArrayList<String> visited) {
        visited.add(startCity);
        ArrayList<String> path = new ArrayList<>();
        path.add(startCity);
        ArrayDeque<ArrayList<String>> q_paths = new ArrayDeque<>();
        q_paths.add(path);
        while(!q_paths.isEmpty()) {
            ArrayList<String> partialPath = q_paths.removeFirst();
            String lastCity = partialPath.get(partialPath.size()-1);
            for (int i = 0; i < graph.get(lastCity).size(); i++) {
                ArrayList<String> extended_path = new ArrayList<>(partialPath);
                String nextCity = graph.get(lastCity).get(i);
                if(!partialPath.contains(nextCity)) {
                    extended_path.add(nextCity);
                    if(nextCity.equals(destinationCity)) {
                        System.out.println(extended_path);
                    } else {
                        q_paths.add(extended_path);
                    }
                }
            }
        }
    }

    private static HashMap<String, List<String>> readTextFile(String filename)  {
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
