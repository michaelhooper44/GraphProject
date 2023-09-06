 /**
   * Represents a vertex in the graph.
   */
  public class header {
      //node
      header next;
      String info;
      edge neighbor;
 
      // Constructor for header class
      // Initializes info, next, and neighbor
     public header(String info){
         this.info = info;
         this.next = null;
         this.neighbor = null;
     }
 }
