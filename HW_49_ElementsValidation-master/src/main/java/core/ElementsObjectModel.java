package core;

public class ElementsObjectModel {
	
 public String  locator;
  
 public Boolean isPresent;
 public Boolean isDisplayed;
 public Boolean isEnabled;
   
 public int location_X;
 public int location_Y;
 public int sizeWidth;
 public int sizeHeight;

 public void printMpe() {
  System.out.println(locator + " " + isPresent + " " + isDisplayed + " " + isEnabled + " " + location_X + " " + location_Y + " " + sizeHeight + " " + sizeWidth);
 }

}
