package scotlandyard;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ScotlandYardGraphReader extends
        GraphReader<Integer, Route> {

  @Override
  public Graph<Integer, Route> readGraph(String filename) throws IOException {
    File file = new File(filename);
    Scanner in = new Scanner(file);

    Graph<Integer, Route> graph = new Graph<Integer, Route>();

    String[] topLine = in.nextLine().split(" ");
    int numberOfNodes = Integer.parseInt(topLine[0]);
    int numberOfEdges = Integer.parseInt(topLine[1]);

    for (int i = 0; i < numberOfNodes; i++) {
      String line = in.nextLine();
      if (line.isEmpty()) {
        continue;
      }
      graph.add(getNode(line));
    }

    for (int i = 0; i < numberOfEdges; i++) {
      String line = in.nextLine();
      if (line.isEmpty()) {
        continue;
      }
      graph.add(getEdge(line));
    }
    return graph;
  }

  private Edge<Integer, Route> getEdge(String line) {
    String[] parts = line.split(" ");

    Route data = null;

    try {
      data = Route.valueOf(parts[2]);

    }
    catch (IllegalArgumentException e) {
      System.err.println("Error in graph. Cannot convert " + parts[2] + " to RouteType");
      System.exit(1);
    }

    Edge<Integer, Route> edge = new Edge<Integer, Route>
            (getIdFromName(parts[0])
                    , getIdFromName(parts[1])
                    , data);
    return edge;

  }

  private Node<Integer> getNode(String line) {
    int nodeNumber = getIdFromName(line);
    Node<Integer> node = new Node<Integer>(nodeNumber);
    return node;
  }

  private int getIdFromName(String name) {
    return Integer.parseInt(name);
  }


}
/*Hello Dr. Hollis,

Sorry for bothering you. I am Mudit Gupta, a CS undergrad at Bristol. I was in your 

tutorial group. I hope you remember me. How are you ? I guess it has been a while 

since we met. Our first year is over and we have also got our results. 

Actually, I wanted some advice from you. For the past few months I have 

read and heard about the year abroad program where the third year of the course is 

spent at another university. I just wanted to ask if this program is really worth it 

taking exposure and employment opportunities into consideration. What do 

you think would be the pros and cons of spending third year at another university?

Also do you have any idea of the sort of grades you need to get into a good place

I know I am asking a lot of questions but I thought you would be the best person 

to turn to for such advice.

I hope to see you soon 
*/
