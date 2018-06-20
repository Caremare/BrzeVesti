
package framework;


public class Helper {

    public static CharSequence[] getRandomText;
    
    public static String getRandomText() {
        
        int random = (int) (Math.random() * 250 + 1);
        String randomText = "MR" + random;
        return randomText;
        
        //return "MR" + (int) (Math.random() * 250 + 1);
    }
    
    
    
    
}
