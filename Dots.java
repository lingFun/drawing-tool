//Ling Fang
//CSC221 Assignment 5
//Dots.java
package application;

//Maintains a List<Dot>
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Dots {
	
	@XmlElement( name = "Dot")
	private List<Dot> dots = new ArrayList<>(); // stores Dots
	
	// returns the List<Dots>
	public List<Dot> getDots() {return dots;}

}
