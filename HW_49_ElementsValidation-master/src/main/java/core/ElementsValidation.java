package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;



public class ElementsValidation 
{

    public static   WebDriver driver ;
	
    public static String jsonPath =null;
    public static String url = null;
     
  
    public void readProperties (String file_path){
    	// String	file_path = "./src/test/resources/data/DataSource.properties";
    try {
	Properties property = new Properties();
	property.load(new FileInputStream(file_path));
	
	jsonPath = property.getProperty("jsonPath");		
	url = property.getProperty("url");
			
		} catch (FileNotFoundException e) {
			System.out.println("Method 'readProperties()' - BLOCK");
			System.out.println();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Method 'readProperties()' - BLOCK");
			System.out.println();
			System.out.println(e.getMessage());
		}	

}//END readProperties	

    
    public void before() {
 		driver = new FirefoxDriver();
 		driver.manage().window().maximize();
 		driver.get(url);		
 		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
 		 
 		 loadTestData(jsonPath);   //comment this if use loadTestDataLoop(jsonPath); 
 		//loadTestDataLoop(jsonPath);  //  ** Used only with test_Common_inLoop_JsonFile()
 	}
 
    public void after(){
		driver.quit();
	}

   /**
    //@Test  Used for lopping through all elements in JSONfile
    public void test_0101_dynText_qoute_isPresent (ElementsObjectModel mpe) {
  	//String   el_locator = elValid.dynText_qoute;
      Assert.assertEquals(this.element_isPresent(mpe.locator), (boolean)mpe.isPresent);
    }
      
    //@Test   
    public void test_0102_dynText_qoute_Validation (ElementsObjectModel mpe) {

  	  Assert.assertEquals((boolean)this.element_isDisplayed(mpe.locator), (boolean)mpe.isDisplayed);
  	  Assert.assertEquals((boolean)this.element_isEnabled(mpe.locator), (boolean)mpe.isEnabled);
  	  Assert.assertEquals((int)this.get_element_Location(mpe.locator).getX(), (int)mpe.location_X);
  	  Assert.assertEquals((int)this.get_element_Location(mpe.locator).getY(), (int)mpe.location_Y);
  	  Assert.assertEquals((int)this.get_element_Dimension(mpe.locator).getWidth(), (int)mpe.sizeWidth);
  	  Assert.assertEquals((int)this.get_element_Dimension(mpe.locator).getHeight(), (int)mpe.sizeHeight);
    }
    
    */
    //creating ArrayList collection where every element has data type custom object ElementsObjectModel
	
    
    public ArrayList<ElementsObjectModel> lampe = new ArrayList<ElementsObjectModel>();
    
    public void loadTestData(String path){
	     Long tmp = 0L;
	     JSONParser parser = new JSONParser();
	      int i = 0;
	      ElementsObjectModel mpe = null;
	    try {
	    	    	
	        //1.  
	    	
	        JSONArray a = (JSONArray) parser.parse(new FileReader(path));
	       
	        //2. every element of Array "a" is object "o" of Object type 
	        for (Object o : a)
	        {
	        	
	      	//3. initialization of class ElementsObjectModel inside method
	        	mpe = new ElementsObjectModel();   //inicialization of class
	      
	        //4. here is casting data from javas' datatype Object to JSONObject	
	        	JSONObject elements= (JSONObject) o;
	        	
            //5. Copy value from JSONObject to custom object   ElementsObjectModel
	            mpe.locator = (String) elements.get("locator");                    
	            mpe.isPresent = (Boolean) elements.get("isPresent");            
	            mpe.isDisplayed = (Boolean) elements.get("isDisplayed");         
	            mpe.isEnabled = (Boolean) elements.get("isEnabled");   
	            tmp = (Long)elements.get("location_X");  //casting Long to int 
	            mpe.location_X = tmp.intValue();   
	            tmp = (Long)elements.get("location_Y"); 
	            mpe.location_Y = tmp.intValue();      
	            tmp = (Long)elements.get("sizeWidth"); 
	            mpe.sizeWidth = tmp.intValue();   
	            tmp = (Long)elements.get("sizeHeight");
	            mpe.sizeHeight = tmp.intValue();

	            lampe.add(mpe);
	            
                System.out.println("collection element #" + i + ": " + lampe.get(i).locator);
                i++;
	        }


	    } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); System.out.println(mpe.locator);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); System.out.println(mpe.locator);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); System.out.println(mpe.locator);
        }

    
    }
    
    public  Dimension get_element_Dimension( String locator) {
        Dimension el_Size = driver.findElement(By.id(locator)).getSize();    
        return el_Size;
    }

    public  Point get_element_Location( String locator) {
            Point el_Location = driver.findElement(By.id(locator)).getLocation();
            return el_Location;

    }

    public  boolean element_isEnabled( String locator) {
        boolean el_isEnabled = driver.findElement(By.id(locator)).isEnabled();
        return el_isEnabled;
    }

    public  boolean element_isDisplayed(String locator ) {
            boolean el_isDisplayed = driver.findElement(By.id(locator)).isDisplayed();        
            return el_isDisplayed;
        }


    public  boolean element_isPresent( String locator) {
        boolean isPresent;

        try {
            isPresent= driver.findElements(By.id(locator)).size() >= 0;
            return isPresent;

        } catch ( NoSuchElementException e ) {
            isPresent= driver.findElements(By.id(locator)).size() > 0;
            
        }
        return isPresent;
    }

    


}
