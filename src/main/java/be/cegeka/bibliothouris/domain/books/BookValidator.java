package be.cegeka.bibliothouris.domain.books;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dieterp on 26/01/2017.
 */

@Named
public class BookValidator {

    public boolean isValid(String isbn)
    {
        // == 13 char
        // sum 1: All nr on a odd place  +=
        // sum 2: All even on even place  +=   * 3
        //  step 3 : sum   1 + 2
        // sum round to next 10
        // sum - sum(3) = last digit
        // check last digit code == last digit

        if (isbn.length() == 13)
        {
            int sum1=0;
            int sum2=0;
            for (int i = 0; i < isbn.length()-1; i++)
            {
                if(i%2==0) {
                    sum1+= (int) isbn.charAt(i) -48;

                } else {
                    sum2+= (int) isbn.charAt(i) -48;
                }
            }
            sum2*=3;
            double sumTotal = sum1 + sum2;
            double sumRoundUp = Math.ceil(sumTotal/10)*10;
            int sumFinal = (int)sumRoundUp-(int)sumTotal;

            if (((int) isbn.charAt(isbn.length()-1)-48) == sumFinal)
            {
                return true;
            }
        }
        return false;
    }

}
