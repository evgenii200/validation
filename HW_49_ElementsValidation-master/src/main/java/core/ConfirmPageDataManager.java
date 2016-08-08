package core;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ConfirmPageDataManager {


    public   ArrayList<ElementsObjectModel> alCPE = null;
   public ArrayList<ElementsObjectModel> alCPE2 = null;

    ElementsValidation elValid = new ElementsValidation();
            //= new ElementsValidation ();

    public final String[] el_locator = {"id_f_title",    "id_f_label_fn_conf",     "id_fname_conf",  "id_f_label_ln_conf",
                                        "id_lname_conf", "id_f_label_email_conf",  "id_email_conf",  "id_f_label_phone_conf",
                                        "id_phone_conf", "id_f_label_gender_conf", "id_gender_conf", "id_f_label_state_conf",
                                        "id_state_conf", "id_f_label_terms_conf",  "id_terms_conf",  "id_back_button",
                                        "copyright"};


    String fname = "Alex";
    String lname = "Moore";
    String email = "alexmoore@gmail.com";
    String phone = "415 555-1212";

    public String jsonPath = "/Users/anna/Java_workspace/HW_49_ElementsValidation/src/test/resources/data/";
    public String jsonFile = "ConfPage.json";




    public static void   getConfirmationPage (String fname, String lname, String email, String phone, WebDriver driver) {
        driver.findElement(By.id("id_fname")).sendKeys(fname);
        driver.findElement(By.id("id_lname")).sendKeys(lname);
        driver.findElement(By.id("id_email")).sendKeys(email);
        driver.findElement(By.id("id_phone")).sendKeys(phone);
        driver.findElement(By.id("id_g_radio_01")).click();
        driver.findElement(By.id("id_checkbox")).click();

            //Select option from drop-down list   https://loadfocus.com/blog/2016/06/13/how-to-select-a-dropdown-in-selenium-webdriver-using-java/
        Select dropdown = new Select(driver.findElement(By.id("id_state")));
            //  dropdown.selectByVisibleText("Italy");
            //  dropdown.selectByValue("option2");
        dropdown.selectByIndex(2);

        driver.findElement(By.id("id_submit_button")).click();


    }


    WebDriver driver;


    public void getPrimaryUrl (){
        driver = new FirefoxDriver();

        elValid = new ElementsValidation ();

        // driver.get(elValid.url);

        driver.get("http://learn2test.net/qa/apps/sign_up/v1/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    
    public void before(){
        getPrimaryUrl();
        getConfirmationPage(fname, lname, email, phone, driver);
        loadTestDataLoop(fullTestPath);
    }

    
    public void after(){
		driver.quit();
	}
    
    
    public void readData(){
        getPrimaryUrl();

        getConfirmationPage (fname, lname, email, phone, driver);

      //  driver.manage().window().maximize();
      //  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        ElementsObjectModel cpe ;

        alCPE = new ArrayList<ElementsObjectModel>();

        JsonGenerator jg = openJsonGenerator(jsonFile,jsonPath);

        jg.writeStartArray();

        for (int i = 0; i < el_locator.length; i++) {

        	cpe = readElementProperty(driver, el_locator[i]); //for each web-element creating single instance of  Object
            alCPE.add(cpe);// adding Object as element to the ArrayList
            cpe.printMpe();   //printing out data of each Object

            addJsonBlock(jg, cpe);

        }
        jg.writeEnd(); // end of Json array
        jg.close();    // close Json generator

        driver.quit();
        //driver.close();
        

    }

   // String fullTestPath = jsonPath + "//" + jsonFile;
    String fullTestPath = "/Users/anna/Java_workspace/HW_49_ElementsValidation/src/test/resources/data/ConfPage.json";

 

    public void loadTestDataLoop(String path){
        Long tmp = 0L;
        alCPE2 = new ArrayList<ElementsObjectModel>();
        JSONParser parser = new JSONParser();
        int i = 0;
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(path));
            for (Object o : a)
            {
                ElementsObjectModel mpe = new ElementsObjectModel();
                JSONObject elements= (JSONObject) o;

                mpe.locator = (String) elements.get("locator");
                mpe.isPresent = (Boolean) elements.get("isPresent");
                mpe.isDisplayed = (Boolean) elements.get("isDisplayed");
                mpe.isEnabled = (Boolean) elements.get("isEnabled");
                tmp = (Long)elements.get("location_X");
                mpe.location_X = tmp.intValue();
                tmp = (Long)elements.get("location_Y");
                mpe.location_Y = tmp.intValue();
                tmp = (Long)elements.get("sizeWidth");
                mpe.sizeWidth = tmp.intValue();
                tmp = (Long)elements.get("sizeHeight");
                mpe.sizeHeight = tmp.intValue();

                alCPE2.add(mpe);
                System.out.println("collection element #" + i + ": " + alCPE2.get(i).locator + "  Height  " +alCPE2.get(i).sizeHeight);
                i++;
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    ElementsObjectModel mpe = new ElementsObjectModel();

    
    
    //@Test  Used for lopping through all elements in JSONfile
    public void test_0101_dynText_qoute_isPresent (ElementsObjectModel mpe) {
  	
        Assert.assertEquals(this.el_isPresent(mpe.locator), (boolean)mpe.isPresent);  
     //   Assert.assertEquals(elValid.element_isPresent(mpe.locator), (boolean)mpe.isPresent);
    }

       
    //@Test
    public void test_0102_dynText_qoute_Validation (ElementsObjectModel mpe) {

        Assert.assertEquals(this.element_isDisplayed(mpe.locator), (boolean)mpe.isDisplayed);
        Assert.assertEquals(this.element_isEnabled(mpe.locator), (boolean)mpe.isEnabled);
        Assert.assertEquals(this.get_element_Location(mpe.locator).getX(), (int)mpe.location_X);
        Assert.assertEquals(this.get_element_Location(mpe.locator).getY(), (int)mpe.location_Y);
        Assert.assertEquals(this.get_element_Dimension(mpe.locator).getWidth(), (int)mpe.sizeWidth);
        Assert.assertEquals(this.get_element_Dimension(mpe.locator).getHeight(), (int)mpe.sizeHeight);
    }




    /**
     *  This method using the "id_element" as locator  creates instance of
     *  object  ElementsObjectModel for each web-element in process of generating JSONfile 
     *
     * */
    public ElementsObjectModel readElementProperty (WebDriver driver, String locator) {
        ElementsObjectModel cpe = new ElementsObjectModel();
        try {

            cpe.locator = locator;

            cpe.isPresent = driver.findElements(By.xpath(locator)).size() >= 0;


            cpe.isDisplayed =  driver.findElement(By.id(locator)).isDisplayed();


            cpe.isEnabled =  driver.findElement(By.id(locator)).isEnabled();


            Point Location = driver.findElement(By.id(locator)).getLocation();

            cpe.location_X = Location.getX();

            cpe.location_Y = Location.getY();

            Dimension el_Size = driver.findElement(By.id(locator)).getSize();
            cpe.sizeWidth = el_Size.getWidth();

            cpe.sizeHeight = el_Size.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cpe;
    }


    public JsonGenerator openJsonGenerator(String file, String path) {
        JsonGenerator jg = null;
        try {
            if ((file == null) || (path == null)) {return null;}
            String fullPath = path + "//" + file;
            jg = Json.createGenerator(new FileWriter(fullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jg;
     }


    /**
     * *
     * This method creates the  single json data block from instance of ConfirmationPageElements class
     *
     * @param adj
     * @param mpe
     */
    public void addJsonBlock(JsonGenerator adj, ElementsObjectModel cpe ) {

        try {
            //StringWriter w = new StringWriter();
            adj.writeStartObject();

            adj.write("locator",cpe.locator);
            adj.write("isPresent",cpe.isPresent);
            adj.write("isDisplayed",cpe.isDisplayed);
            adj.write("isEnabled",cpe.isEnabled);
            adj.write("location_X",cpe.location_X);
            adj.write("location_Y",cpe.location_Y);
            adj.write("sizeWidth",cpe.sizeWidth);
            adj.write("sizeHeight",cpe.sizeHeight);
            adj.writeEnd();

        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public boolean el_isPresent(String locator){
    	
    	 boolean isPresent;

         try {
             isPresent= driver.findElements(By.id(locator)).size() >= 0;
             return isPresent;

         } catch ( NoSuchElementException e ) {
             isPresent= driver.findElements(By.id(locator)).size() > 0;
             
         }
    	return isPresent;
    };
    
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
    
    
}


