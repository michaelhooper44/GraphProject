 /**
    * Represents a edge in the adjacency list.
    */
   public class edge {
        String connection;
        edge next;
        header sink;
  
       // Constructor for an edge
      // Initializes next, connection, and sink
       public edge(String connection, header sink) {
          this.next = null;
          this.connection = connection;
          this.sink = sink;
       }
  }
