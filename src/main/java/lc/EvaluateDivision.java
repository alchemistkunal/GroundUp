package lc;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 */
public class EvaluateDivision {

    class Edge{
        String fromVertexLabel;

        String toVertexLabel;
        double value;

        public Edge(String fromVertexLabel, String toVertexLabel, double value) {
            this.fromVertexLabel = fromVertexLabel;
            this.toVertexLabel = toVertexLabel;
            this.value = value;
        }

        public String getToVertexLabel() {
            return toVertexLabel;
        }

        public String getFromVertexLabel() {
            return fromVertexLabel;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "fromVertexLabel='" + fromVertexLabel + '\'' +
                    ", toVertexLabel='" + toVertexLabel + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
    class Vertex {
        String label;
        Set<Edge> connectedEdges = new HashSet<>();
        Vertex(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public Set<Edge> getConnectedEdges() {
            return connectedEdges;
        }
        public void addEdge(String fromLabel, String toLabel, double edgeWeight){
            getConnectedEdges().add(new Edge(fromLabel, toLabel, edgeWeight));
        }

        // equals and hashCode

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return getLabel().equals(vertex.getLabel());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getLabel());
        }
    }
    class Graph {
        private Map<Vertex, List<Vertex>> adjVertices;
        private Map<String, Vertex> vertices = new HashMap<>();
        // standard constructor, getters, setters
        void addVertex(String label) {
            vertices.putIfAbsent(label,new Vertex(label));
        }

        void addEdge(String label1, String label2, double edgeWeight, boolean isBiDirectionalGraph) {
            if(!vertices.containsKey(label1)) addVertex(label1);
            if(!vertices.containsKey(label2)) addVertex(label2);
            System.out.println(String.format("adding edge from %s to %s with value %s", label1, label2, edgeWeight));
            vertices.get(label1).addEdge(label1, label2, edgeWeight);
            if(isBiDirectionalGraph) {
                System.out.println(String.format("adding edge from %s to %s with value %s", label2, label1, 1 / edgeWeight));
                vertices.get(label2).addEdge(label2, label1, 1 / edgeWeight);
            }
        }
        Set<Edge> getAdjVertices(String label) {
            return vertices.get(label).getConnectedEdges();
        }


        private boolean recurFindWeight(Edge currentVertexPointer, Set<Edge> visited, String toLabel, List<Double> pathWeight){
//            if (stack.isEmpty()) {
//                return false;
//            }
//            Edge currentVertexPointer = stack.pop();
            if(vertices.get(currentVertexPointer.getToVertexLabel()).getLabel().equals(toLabel)){
                pathWeight.add(currentVertexPointer.getValue());
                System.out.println(String.format("found edge to %s with value %s", toLabel, currentVertexPointer.getValue()));
                return true;
            }
            if (!visited.contains(currentVertexPointer)) {
                visited.add(currentVertexPointer);
                for (Edge v : this.getAdjVertices(currentVertexPointer.getToVertexLabel())) {
                    if(recurFindWeight(v, visited, toLabel, pathWeight)){
                        System.out.println(currentVertexPointer.toString());
                        pathWeight.add(currentVertexPointer.getValue());
                        return true;
                    }
                }
            }
           return false;
        }
        List<Double> depthFirstTraversal(String fromLabel, String toLabel) {
            List<Double> weight = new ArrayList<>();

            if(!vertices.containsKey(fromLabel)){
                return weight;
            }
            if(fromLabel.equals(toLabel)){
                weight.add(1.0);
                return weight;
            }
            Set<Edge> visited = new LinkedHashSet<>();

            for (Edge v : vertices.get(fromLabel).getConnectedEdges()) {
                if(recurFindWeight(v, visited, toLabel, weight)){
                    break;
                }
            }


            return weight;
        }
    }


    private double[] solveQuery(Graph graph, List<List<String>> queries){
        double[] solution = new double[queries.size()];
        Arrays.fill(solution, -1.0);
        for(int i = 0 ; i < queries.size() ; i++){
            List<Double> pathValues = graph.depthFirstTraversal(queries.get(i).get(0), queries.get(i).get(1));
            if(!pathValues.isEmpty())
                solution[i] = pathValues.stream().reduce(1d, (a, b) -> a * b);
        }
        return solution;
    }
    private Graph createGraph(List<List<String>> equations, double[] values){
        Graph graph = new Graph();
        for(int i = 0 ; i < equations.size() ; i++) {
            graph.addEdge(equations.get(i).get(0), equations.get(i).get(1), values[i], true);
        }
        return graph;
    }


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Graph graph = createGraph(equations, values);

        return solveQuery(graph, queries);
    }

    public List<List<String>> twoDArrayToList(String[][] twoDArray) {
        List<List<String>> list = new ArrayList<>();
        for (String[] array : twoDArray) {
            list.add(Arrays.asList(array));
        }
        return list;
    }

    public static final List<ImmutableTriple<String[][], double[], String[][]>> INPUT_DATA =
                Collections.unmodifiableList(new ArrayList<>(){{
                    add(new ImmutableTriple<>(
                            new String[][]{{"a","b"},{"b","c"}},
                            new double[]{2.0,3.0},
                            new String[][]{{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}}
                            )
                    );  //Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]

                    add(new ImmutableTriple<>(
                                    new String[][]{{"a","b"},{"b","c"},{"bc","cd"}},
                                    new double[]{1.5,2.5,5.0},
                                    new String[][]{{"a","c"},{"c","b"},{"bc","cd"},{"cd","bc"}}
                            )
                    );  //Output: [3.75000,0.40000,5.00000,0.20000]

                    add(new ImmutableTriple<>(
                                    new String[][]{{"a","b"}},
                                    new double[]{0.5},
                                    new String[][]{{"a","b"},{"b","a"},{"a","c"},{"x","y"}}
                            )
                    );  //Output: [0.50000,2.00000,-1.00000,-1.00000]

                    add(new ImmutableTriple<>(
                                    new String[][]{{"a","b"},{"b","c"}},
                                    new double[]{2.0,3.0},
                                    new String[][]{{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}}
                            )
                    );  // Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
                }});

    public static void main(String[] args) {
        EvaluateDivision ed = new EvaluateDivision();

        for (var rowData : INPUT_DATA.subList(0,4)) {
            var result = ed.calcEquation(ed.twoDArrayToList(rowData.getLeft()), rowData.getMiddle(), ed.twoDArrayToList(rowData.getRight()));
            System.out.println(String.format("For Input v1: %s :: result is: %s ", Arrays.deepToString(rowData.getRight()),  Arrays.toString(result)));
        }

    }

}
