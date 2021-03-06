import java.util.*;
import java.io.*;
class Element {
  String name;
  int price;
//Creating a constructor inorder to initialize the state of an object
  public Element(String name, int price) {
    this.name = name;
    this.price = price;
  }
// toString method to return the input string in the format name:price
  public String toString() { 
      return this.name + ": " + this.price;
  }
}
//Main class
public class Main {
  public static void main(String[] args) throws Exception {
    FileInputStream fis=new FileInputStream("sample_input.txt");       
    Scanner sc=new Scanner(fis);
    int employee_count = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();
 // Creating an ArrayList  of Element class and named it as goodies_items
    ArrayList<Element> goodies_items = new ArrayList<Element>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Element(current[0], Integer.parseInt(current[1])));
    }
    sc.close();
    // sorting 
    Collections.sort(goodies_items, new Comparator<Element>(){
    //compare function to return the difference between the price of any two goodies
      public int compare(Element a, Element b) { 
        return a.price - b.price; 
      } 
    });
// finding minimum difference and assigning it to a variable minimum_diff
    int minimum_diff = goodies_items.get(goodies_items.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()- employee_count+1;i++) {
// finding actual difference 
      int diff = goodies_items.get(employee_count+i-1).price-goodies_items.get(i).price;
//comparing both minimum difference and difference
      if(diff<=minimum_diff) {
// if the actual difference is less than or equal to minimum difference then set minimum difference as actual difference
        minimum_diff = diff;
        min_index = i;
      }
    }
    
    
// process of writing to a output file 
    FileWriter fw = new FileWriter("sample_output.txt");
    fw.write("The goodies selected for distribution are:\n");
    for(int i=min_index;i<min_index + employee_count; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + minimum_diff);
// closing the output file 
	  fw.close();
  }
}
