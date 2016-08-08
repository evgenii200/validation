package core;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ElementsValidationTest {

	      public  ElementsValidation elValid = new ElementsValidation ();
 
  @BeforeClass
  public void beforeClass() throws Exception{
	  elValid.readProperties("./src/test/resources/data/DataSource.properties");
	  elValid.before();
	  

  }

  @AfterClass
  public void afterClass() {
	  elValid.after();
  }

  //Looping through all elements in JSONfile
/**  
  @Test 
  public void test_Common_inLoop_JsonFile () {
	  for (ElementsObjectModel mpe : elValid.lampe) {
		//  System.out.println("collection element #" + elValid.lampe.indexOf((Object)mpe) + " with locator=" + mpe.locator);
		  elValid.test_0101_dynText_qoute_isPresent(mpe);
		  elValid.test_0102_dynText_qoute_Validation (mpe);
	  }
  }
 
 */
  
  
  
  @Test
  public void test_0101_1st_element_isPresent () {
      try {
          ElementsObjectModel mpe = elValid.lampe.get(0);
          Assert.assertEquals((boolean) elValid.element_isPresent(elValid.lampe.get(3).locator), (boolean) mpe.isPresent);

      } catch(Exception e) {
          e.printStackTrace();
      }
  }


  @Test
  public void test_0102_1st_element_Validation () {
      try {
         
          ElementsObjectModel mpe = elValid.lampe.get(0);
          Assert.assertEquals((boolean) elValid.element_isDisplayed(mpe.locator), (boolean) mpe.isDisplayed);
          Assert.assertEquals((boolean) elValid.element_isEnabled(mpe.locator), (boolean) mpe.isEnabled);
          Assert.assertEquals((int) elValid.get_element_Location(mpe.locator).getX(), (int) mpe.location_X);
          Assert.assertEquals((int) elValid.get_element_Location(mpe.locator).getY(), (int) mpe.location_Y);
          Assert.assertEquals((int) elValid.get_element_Dimension(mpe.locator).getWidth(), (int) mpe.sizeWidth);
          Assert.assertEquals((int) elValid.get_element_Dimension(mpe.locator).getHeight(), (int) mpe.sizeHeight);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  
  @Test
  public void test_0201_2nd_element_isPresent () {
      try {

          ElementsObjectModel mpe = elValid.lampe.get(1);
          Assert.assertEquals((boolean) elValid.element_isPresent(elValid.lampe.get(1).locator), (boolean) mpe.isPresent);
       //   System.out.println("index 1 locator: " + mpe.locator + " isPresent:" + mpe.isPresent.toString());
      } catch(Exception e) {
          e.printStackTrace();
      }
  }


  @Test
  public void test_0202_2nd_element_Validation () {
      try {

          ElementsObjectModel mpe = elValid.lampe.get(1);
          Assert.assertEquals((boolean) elValid.element_isDisplayed(mpe.locator), (boolean) mpe.isDisplayed);
          Assert.assertEquals((boolean) elValid.element_isEnabled(mpe.locator), (boolean) mpe.isEnabled);
          Assert.assertEquals((int) elValid.get_element_Location(mpe.locator).getX(), (int) mpe.location_X);
          Assert.assertEquals((int) elValid.get_element_Location(mpe.locator).getY(), (int) mpe.location_Y);
          Assert.assertEquals((int) elValid.get_element_Dimension(mpe.locator).getWidth(), (int) mpe.sizeWidth);
          Assert.assertEquals((int) elValid.get_element_Dimension(mpe.locator).getHeight(), (int) mpe.sizeHeight);
          System.out.println("index 1 locator: " + mpe.locator + " location_X:" + mpe.location_X);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  
  @Test
  public void test_0301_3rd_element_isPresent () {
      try {
          ElementsObjectModel mpe = elValid.lampe.get(3);
          Assert.assertEquals((boolean) elValid.element_isPresent(elValid.lampe.get(3).locator), (boolean) mpe.isPresent);

      } catch(Exception e) {
          e.printStackTrace();
      }
  }


  @Test
  public void test_0302_3rd_element_Validation () {
      try {

          ElementsObjectModel mpe = elValid.lampe.get(3);
          Assert.assertEquals((boolean) elValid.element_isDisplayed(mpe.locator), (boolean) mpe.isDisplayed);
          Assert.assertEquals((boolean) elValid.element_isEnabled(mpe.locator), (boolean) mpe.isEnabled);
          Assert.assertEquals((int) elValid.get_element_Location(mpe.locator).getX(), (int) mpe.location_X);
          Assert.assertEquals((int) elValid.get_element_Location(mpe.locator).getY(), (int) mpe.location_Y);
          Assert.assertEquals((int) elValid.get_element_Dimension(mpe.locator).getWidth(), (int) mpe.sizeWidth);
          Assert.assertEquals((int) elValid.get_element_Dimension(mpe.locator).getHeight(), (int) mpe.sizeHeight);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
}
