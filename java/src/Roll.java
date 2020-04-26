//Adrien CATHEBRAS
// Classe : 2ESGIA1
// All Tests Passed

package src;

// Random file import and API to use the REGEXs
import Random.RNG;
import java.util.regex.*;

public class Roll {
    // Attributes we're going to use for the project
    private int Number_roll;
    private int Dice_value;
    private int Modification ;

    public enum RollType {
        // Very difficult to do. I failed. Sorry Mr. :/
        NORMAL,
        ADVANTAGE,
        DISADVANTAGE
    }

    public Roll(String formula) {
        // Compiled representation of the pattern that our formula must have to be valid in D&D.
        Pattern regex = Pattern.compile("([1-9][0-9]*)?d([1-9][0-9]*)([+-][1-9][0-9]*)?");
        //Look for our pattern in our handmade formula.
        Matcher match = regex.matcher(formula);

        if (match.matches()){
            //If it matches, it means that the formula is respected because it respects the associated part of REGEX.
            this.Number_roll = match.group(1) == null ? 1:Integer.parseInt(match.group(1));
            this.Dice_value = Integer.parseInt(match.group(2));
            this.Modification = match.group(3) != null ? Integer.parseInt(match.group(3)):0;
        } else {
            // If we don't match, then we set everything to -1 because the formula has an error.
            this.Number_roll = -1;
            this.Dice_value = -1;
            this.Modification = -1;
        }
    }

    // Implementation of the Roll constructor with our attributes.
    public Roll(int Dice_value, int Number_roll, int Modification) {
        // We create our instances that we will reuse afterwards.
        this.Number_roll = Number_roll;
        this.Dice_value = Dice_value;
        this.Modification = Modification ;
    }

    public int makeRoll(RollType rollType) {
        //A dice can't have a value of 0 or less, and you can't make 0 or negative rolls.
        if(Number_roll <=0 ||Dice_value <=0){
            return -1 ;
        }
        // We initialize result to 0,
        int result = 0;
        for( int i = 1 ;  i<=Number_roll ; i++){
            result += RNG.random(Dice_value)  ;
        }
        // Calculation of the final result, if it is less than 0, the final result is equal to 0.
        int finalResult = result + Modification;
        if(finalResult <0){
            finalResult =0 ;
        }
        //We return the final result.
        return finalResult ;
    }

}


