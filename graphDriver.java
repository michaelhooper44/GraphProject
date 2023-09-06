import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Driver class
public class graphDriver {
    //driver
     public static void main(String args []) {
        graph newGraph = new graph();
         // Initializes variables
          String src = null;
          String connection = null;
          String sink = null;
          int counter = 0;
          int quit = 0;
          String command;
          int length;
          Scanner keyboard = new Scanner(System.in);
          header temp1;
          header temp2;
  
          String a;
          String b;
           // Tries to read file specified and parse it
         try{
              // Grabs file path
              File file = new File(args[0]);
              String filePath = file.getAbsolutePath();
               File file1 = new File(filePath);
               Scanner scanner = new Scanner(file1);
  
               // Inserts nodes into a graph
               while (scanner.hasNextLine()) {
                   String str2 = scanner.nextLine();
                   Scanner scan2 = new Scanner(str2);
  
                   while(scan2.hasNext()) {
                       if(counter == 0){
                           src = scan2.next();
                      }
                       else if(counter == 1){
                           connection = scan2.next();
                       }
                       else if(counter == 2){
                           sink = scan2.next();
                       }
                       counter++;
                   }
                   scan2.close();
                   counter = 0;
                   // Inserts each vertex, edge, and sink node
                   newGraph.add(src, connection, sink);
               }
  
               scanner.close();
           }
           catch(FileNotFoundException e){
               System.out.println("File not found " + args[0]);
           }
  
           // Command-line interface loop
           while(quit != 1){
  
               // Grabs vertex values from standard input
               System.out.print("enter a starting vertex:  ");
               a = keyboard.nextLine();
               System.out.print("enter a ending vertex:  ");
               b = keyboard.nextLine();
               // Temp variables to be passed to query functions
               temp1 = newGraph.getHeader(a);
               temp2 = newGraph.getHeader(b);
  
               // Prints error if nodes are null
               if(temp1 == null || temp2 == null){
                   System.out.println("Node doesn't exist");
               }
  
               // Asks user for query number
               else{
                   System.out.println("Enter 1 to print all paths from A to B");
                   System.out.println("Enter 2 to print paths of specified length from A to B");
                   System.out.println("Enter 3 to print all paths of minimum length from A to B");
                   System.out.println("Enter quit to exit the program");
                   System.out.print("Enter a query: ");
                   command = keyboard.nextLine();
  
                   // Handles query 1
                   if(command.equals("1")){
                       newGraph.query1(temp1, temp2);
                  }
                   // Handles query 2
                   else if(command.equals("2")){
                       System.out.print("given length: ");
                      length = keyboard.nextInt();
                      newGraph.query2(temp1, temp2, length);
                      keyboard.nextLine();
 
                  }
                  // Handles query 3
                  else if(command.equals("3")){
 
                      newGraph.query3(temp1, temp2);
 
                  }
                  // Quits program
                  else if(command.equals("quit")){
                      quit = 1;
                  }
              }
 
 
 
 
 
          }
 
          keyboard.close();
      }
 
 
  }

