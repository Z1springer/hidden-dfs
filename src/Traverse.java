import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Traverse {
  public static void main(String[] args) {
    // Create Person objects with empty confidant sets.
    Person alice = new Person("Alice", 30, new HashSet<>());
    Person bob = new Person("Bob", 28, new HashSet<>());
    Person charlie = new Person("Charlie", 32, new HashSet<>());
    Person diana = new Person("Diana", 27, new HashSet<>());
    Person eve = new Person("Eve", 35, new HashSet<>());
    Person frank = new Person("Frank", 29, new HashSet<>());
    Person grace = new Person("Grace", 33, new HashSet<>());
    Person henry = new Person("Henry", 31, new HashSet<>());
    Person irene = new Person("Irene", 26, new HashSet<>());
    Person jack = new Person("Jack", 40, new HashSet<>());

    // Set up confidant relationships.
    // Note: Relationships are directional and a person is not their own confidant.
    alice.getConfidants().addAll(Arrays.asList(bob, charlie, diana, frank));
    bob.getConfidants().addAll(Arrays.asList(alice, eve));
    charlie.getConfidants().addAll(Arrays.asList(bob, frank));
    diana.getConfidants().addAll(Arrays.asList(charlie, grace, eve));
    eve.getConfidants().addAll(Arrays.asList(frank, irene));
    frank.getConfidants().addAll(Arrays.asList(bob, grace));
    grace.getConfidants().add(henry);
    henry.getConfidants().addAll(Arrays.asList(alice, diana));
    irene.getConfidants().addAll(Arrays.asList(jack, diana));
    jack.getConfidants().addAll(Arrays.asList(charlie, bob));

    Map<Integer, Set<Integer>> graph = new HashMap<>();
    graph.put(3, new HashSet<>(Arrays.asList(7, 34)));
    graph.put(7, new HashSet<>(Arrays.asList(12, 45, 34, 56)));
    graph.put(12, new HashSet<>(Arrays.asList(7, 56, 78)));
    graph.put(34, new HashSet<>(Arrays.asList(34, 91)));
    graph.put(56, new HashSet<>(Arrays.asList(78)));
    graph.put(78, new HashSet<>(Arrays.asList(91)));
    graph.put(91, new HashSet<>(Arrays.asList(56)));
    graph.put(45, new HashSet<>(Arrays.asList(23)));
    graph.put(23, new HashSet<>());
    graph.put(67, new HashSet<>(Arrays.asList(91)));

    // See below site for visualization of this graph
    // https://auberonedu.github.io/graph-explore/graph_site/viz.html
    Vertex<Integer> v3 = new Vertex<>(3);
    Vertex<Integer> v7 = new Vertex<>(7);
    Vertex<Integer> v12 = new Vertex<>(12);
    Vertex<Integer> v34 = new Vertex<>(34);
    Vertex<Integer> v56 = new Vertex<>(56);
    Vertex<Integer> v78 = new Vertex<>(78);
    Vertex<Integer> v91 = new Vertex<>(91);
    Vertex<Integer> v45 = new Vertex<>(45);
    Vertex<Integer> v23 = new Vertex<>(23);
    Vertex<Integer> v67 = new Vertex<>(67);

    v3.neighbors = new ArrayList<>(List.of(v7, v34));
    v7.neighbors = new ArrayList<>(List.of(v12, v45, v34, v56));
    v12.neighbors = new ArrayList<>(List.of(v7, v56, v78));
    v34.neighbors = new ArrayList<>(List.of(v34, v91));
    v56.neighbors = new ArrayList<>(List.of(v78));
    v78.neighbors = new ArrayList<>(List.of(v91));
    v91.neighbors = new ArrayList<>(List.of(v56));
    v45.neighbors = new ArrayList<>(List.of(v23));
    v23.neighbors = new ArrayList<>(List.of());
    v67.neighbors = new ArrayList<>(List.of(v91));

    // Example of Method Overloading
    printVertices(v7);
    System.out.println("----------------");
    printVertices(graph, 3);
    System.out.println("----------------");
    printGossipers(diana);
  }

  // Nearly identical to the dfs using Vertex
  public static void printGossipers(Person person) {
    Set<Person> visited = new HashSet<>();
    printGossipersHelper(person, visited);
  }

  public static void printGossipersHelper(Person person, Set<Person> visited) {
    if (person == null)
      return;
    if (visited.contains(person))
      return;
    System.out.println(person.getName());
    visited.add(person);
    for (Person confidant : person.getConfidants()) {
      printGossipersHelper(confidant, visited);
    }
  }

  public static void printVertices(Map<Integer, Set<Integer>> graph, int start) {
    if (!graph.containsKey(start))
      return;
    // Create a set to represent the previously visited node
    Set<Integer> visited = new HashSet<>();
    // Recursivelly Depth First Search
    printVerticesHelper(graph, start, visited);
  }

  public static void printVerticesHelper(Map<Integer, Set<Integer>> graph, int vertex, Set<Integer> visited) {
    if (!graph.containsKey(vertex))
      return;
    if (visited.contains(vertex))
      return;
    System.out.println(vertex);
    visited.add(vertex);
    if (graph.get(vertex) == null)
      return;
    for (int neighbor : graph.get(vertex)) {
      printVerticesHelper(graph, neighbor, visited);
    }
  }

  public static <T> void printVertices(Vertex<T> vertex) {
    Set<Vertex<T>> nodes = new HashSet<>();
    printVerticesHelper(vertex, nodes);
  }

  public static <T> void printVerticesHelper(Vertex<T> vertex, Set<Vertex<T>> set) {
    if (vertex == null)
      return;
    if (set.contains(vertex))
      return;
    System.out.println(vertex.data);
    set.add(vertex);
    if (vertex.neighbors != null) {
      for (Vertex<T> neighbor : vertex.neighbors) {
        printVerticesHelper(neighbor, set);
      }
    }
  }

}
