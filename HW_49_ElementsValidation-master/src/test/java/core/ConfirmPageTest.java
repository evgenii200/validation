package core;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConfirmPageTest {

	public  ElementsValidation elValid = new ElementsValidation ();

    public  ConfirmPageDataManager cpDaMa = new ConfirmPageDataManager();

    ElementsObjectModel mpe = new ElementsObjectModel();

    @BeforeClass
    public void beforeClass() throws Exception{

  //      elValid.readProperties("./src/test/resources/data/DataSource.properties");

   cpDaMa.readData();

     // String fullTestPath = cpDaMa.jsonPath + "//" + cpDaMa.jsonFile;


        cpDaMa.before();
       

    }

    @AfterClass
    public void afterClass() {
    cpDaMa.after();
       // elValid.after();
    }

    //Looping through all elements in JSONfile

 @Test
 public void test_Common_inLoop_JsonFile () {

     for (ElementsObjectModel mpe : cpDaMa.alCPE2) {
     //  System.out.println("collection element #" + elValid.lampe.indexOf((Object)mpe) + " with locator=" + mpe.locator);
         cpDaMa.test_0101_dynText_qoute_isPresent(mpe);
         cpDaMa.test_0102_dynText_qoute_Validation (mpe);

 }
 }


}
