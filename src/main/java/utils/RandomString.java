/*
    Returns a pseudorandom string of a determinate length
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class RandomString {
    private final ArrayList<String> letterArray = new ArrayList<>(Arrays.asList(
        "q","e","r","t","y","u","i","o","p",
        "a","s","d","f","g","h","j","k","l",
        "z","x","c","v","b","n","m",
        
        "Q","E","R","T","Y","U","I","O","P",
        "A","S","D","F","G","H","J","K","L",
        "Z","X","C","V","B","N","M"
        )
    );
    
    private final ArrayList<String> numberArray = new ArrayList<>(Arrays.asList(
            "0","1","2","3","4","5","6","7","8","9"
        )
    );
    
    private final ArrayList<String> symbolArray = new ArrayList<>(Arrays.asList(
            "`","#","%","&","(",")","+",
            "~","^","&","_",
            "[","]","'",".","/","{","}","|","<",
            ">"
        )
    );
    
    public String getRandomString(int returnType, int length) {
        ArrayList<String> tempArray = new ArrayList<>();
        
        switch (returnType) {
            //Letters
            case 0:
                tempArray = letterArray;
                break;
            //Numbers
            case 1:
                tempArray = numberArray;
                break;
            //Letters and numbers
            case 2:
                tempArray = letterArray;
                tempArray.addAll(numberArray);
                break;
             //Symbols
            case 3:
                tempArray = symbolArray;
                break;
            //All
            case 4:
                tempArray = letterArray;
                tempArray.addAll(numberArray);
                tempArray.addAll(symbolArray);
                break;
        }
        
        String returnString = "";
        Random rnd = new Random();
        
        for (int i = 0; i < length; i++) {
            returnString += tempArray.get(rnd.nextInt(tempArray.size()));
        }
        
        return returnString;
    }
}
