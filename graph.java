 import java.util.*;
    public class graph {
   
        private int length = 0;
        private boolean two;
        static header head = null;
        private int counter = 0;
   
        public graph(){}
  
       // Helper function to return the head variable
       public static header getHead() {
           return head;
       }
  
       // Helper function to search for a node in the list
       public header get(String info){
           header temp = head;
           // Iterates through the list, comparing values
           // to find a match
           while (temp != null) {
               if (temp.info.equals(info)) {
                   return temp;
               }
               temp = temp.next;
           }
  
           // If node doesn't exist, creates a node and inserts
           header newNode = new header(info);
           newNode.next = head;
           head = newNode;
  
           return newNode;
       }
  
       // Function to add node into the graph
       public void add(String src, String connection, String sink) {
           // Creates the node to be added and its sink node
           header tempSrc = get(src);
           header tempSink = get(sink);
  
           // Creates the connection between the nodes
           edge tempEdge = new edge(connection, tempSink);
           // Checks if node has neighbors
           if(tempSrc.neighbor == null){
               tempSrc.neighbor = tempEdge;
           }
          else{
               tempEdge.next = tempSrc.neighbor;
               tempSrc.neighbor = tempEdge;
           }
       }
  
       // Function to handle the first required query
       public void query1(header a, header b){
  
           // Creates lists to store the paths found
           ArrayList<header> list = new ArrayList<>();
           ArrayList<String> list2 = new ArrayList<>();
           // If node doesn't exists, print error
          if(a == null || b == null){
               System.out.println("Node doesn't exist");
               return;
           }
           // Adds first element into vertex list
           list.add(a);
           // If path doesn't exist, print error
           if(a.neighbor== null){
               System.out.println("No path found");
               return;
           }
  
           // Adds first element into connection list
           list2.add(a.neighbor.connection);
  
           // Calls depth first search function
           dfs(a, b, list, list2);
  
           if(counter == 0){
               System.out.println("No path found");
           }
           counter = 0;
  
  
           list.clear();
  
       }
  
       // Depth first search function
       public void dfs(header a, header b, ArrayList<header> list, ArrayList<String> list2){
           header current = a;
           if(current == null){
               return;
           }
  
           // If node is desired end node, enter
           if(current == b){
               counter++;
              int i = 1;
              // For each node in the vertex list, access elements and process
              for (header head : list) {
                  String str = head.info;
                  // If query 2 is specified and length = desired edge length
                  // print out the path and the connection
                  if (two && list.size() == this.length + 1) {
                      if (str.equals(b.info)) {
                          System.out.print(str);
                          System.out.println();
                      } else {
                          System.out.print(str + " -- " + list2.get(i) + " -> ");
                      }
                      // If query 2 is specified but length is wrong, do nothing
                  } else if (two) {
                      continue;
                      // Query 1, checks if node is final node and prints
                  }  else {
                      if (str.equals(b.info)) {
                          System.out.print(str);
                          System.out.println();
                          // Else prints node and connection to next node
                      } else {
                         System.out.print(str + " -- " + list2.get(i) + " -> ");
                      }
                  }
                  i++;
              }

              //System.out.println();
          }
 
          // Checks if current has a connection
          // Updates edge and current
          if(current.neighbor != null){
             edge e = current.neighbor;
// Iterates through adjacency list
             while(e != null){
                 header temp = e.sink;
                 // Checks contents of lists and if not
                 // present, adds them
                 if(!list.contains(temp)){
                     list.add(temp);
                     list2.add(e.connection);

                     // Recursive call to dfs, updates temp
                     dfs(temp, b, list, list2);
                     // Cleans up final element of the lists
                     list.remove(list.size() - 1);
                     list2.remove(list2.size() - 1);
                 }
                 e = e.next;

             }
         }
     }
     // Function to handle the second required query
     public void query2(header a, header b, int length){
         // Sets boolean and global length variables
         this.length = length;
         two = true;
         // Creates lists for vertices and edges
         ArrayList<header> list = new ArrayList<>();
         ArrayList<String> list2 = new ArrayList<>();

         // Checks if either node is not present, prints error
         if(a == null || b == null){
             System.out.println("Node doesn't exist");
             return;
         }
         // Adds first element to vertext list
         list.add(a);
         if(a.neighbor== null){
             System.out.println("No path found");
             return;
         }
         // Adds first element to edge list
         list2.add(a.neighbor.connection);

         // Calls depth first search function
         dfs(a, b, list, list2);
        if(counter == 0){
            System.out.println("No path found");
         }
         counter = 0;

         list.clear();
         two = false;


     }
     // Function to handle the third required query
     public void query3(header a, header b){

         // Creates the vertex list and edge list
         ArrayList<header> list = new ArrayList<>();
         ArrayList<String> list2 = new ArrayList<>();

         // Creates a list of a list of vertices
         // and a list of a list of edges
         ArrayList<ArrayList<header>> allLists1 = new ArrayList<>();
         ArrayList<ArrayList<String>> allLists2 = new ArrayList<>();

         // If either vertex doesn't exist, print error
         if(a == null || b == null){
             System.out.println("Node doesn't exist");
             return;
         }

         // Adds the first element to the vertex list
         list.add(a);
         if(a.neighbor== null){
             System.out.println("No path found");
             return;
         }
         // Adds the first element to the edge list
         list2.add(a.neighbor.connection);

         // Calls the second depth first search function
         dfs2(a, b, list, list2, allLists1, allLists2);

         if(counter == 0){
             System.out.println("No path found");
         }
         counter = 0;

         // Calls printMin function
         printMin(b, allLists1, allLists2);
         list.clear();
         list2.clear();
         allLists1.clear();
         allLists2.clear();
     }

     // Second depth first search function to handle the third query
     public void dfs2(header a, header b, ArrayList<header> list,
     ArrayList<String> list2, ArrayList<ArrayList<header>> allLists1,
     ArrayList<ArrayList<String>> allLists2){
         header current = a;
         if(current == null){
             return;
         }

         // Checks if the last node is the current node
         if(current == b) {
             // Adds the edge list and the vertex list to
             // the list of list of edges and vertices, respectively
             counter++;
             allLists2.add(new ArrayList<>(list2));
             allLists1.add(new ArrayList<>(list));
             return;
         }

         // Checks if the vertex has a neighbor
         if(current.neighbor != null){
             edge e = current.neighbor;
             // Iterates through the adjacency list
             while(e != null){
                 header temp = e.sink;
                 // Checks if the list already contains the temp
                 // If not, adds to list of vertices and edges
                 if(!list.contains(temp)){
                     list.add(temp);
                     list2.add(e.connection);

                     // Recursive call to the second depth first search function
                     dfs2(temp, b, new ArrayList<>(list),
                     // Creates a new list of lists
                     new ArrayList<>(list2), allLists1, allLists2);
                     // Cleans up the ends of the list
                     list.remove(list.size() - 1);
                     list2.remove(list2.size() - 1);
                 }
                 e = e.next;

             }
         }
     }

     // Method to print the minimum list
     public void printMin(header b, ArrayList<ArrayList<header>> allList1,
     ArrayList<ArrayList<String>> allLists2){
         int min = 500000;
         // Iterates through each list in the list of lists of edges
         for (ArrayList<String> list : allLists2) {
             // Finds the minimum length path by comparing
             // lengths of edge lists
            if (list.size() < min) {
                 min = list.size();
           }
         }

         // Iterates through each list, printing if the size is less than min,
         // which is the shortest length path
         for (int j = 0; j < allList1.size(); j++) {
             if (allLists2.get(j).size() == min) {
                 // Iterates through and prints each vertex and edge
                 for (int i = 0, x = 1; i < allList1.get(j).size(); i++, x++) {
                     String str = allList1.get(j).get(i).info;
                     // Only prints the vertex if the last node
                     if (str.equals(b.info)) {
                         System.out.print(str);
                     }
                     else {
                         System.out.print(str + " -- " + allLists2.get(j).get(x) + " -> ");
                     }
                 }
                 System.out.println();
             }
         }
     }

     // Helper function to get the vertex
     public header getHeader(String a){
         header temp = head;
         // Iterates through vertex list to find the correct header
         while (temp != null) {
             if (temp.info.equals(a)) {
                 return temp;
             }
             temp = temp.next;
         }
         return temp;
     }


 }



