package manager;

import java.awt.CardLayout;

public class CreditCardValidation {
	private  String cardType;

	private boolean isNull(String s){
		if (s.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
    public boolean isValid(long number, String s) {
         
        int total = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);

        if(!prefixMatched(number, 1))
        {
        	return false;
        }
        if(isNull(cardType) || !cardType.equalsIgnoreCase(s)){
        	return false;
        }
        if ((total % 10 == 0) && (prefixMatched(number, 1) == true) && (getSize(number)>=13 ) && (getSize(number)<=16 )) {
            
        	return true;
            
        } else {
            return false;
        }
    }

    private int getDigit(int number) {

        if (number <= 9) {
            return number;
        } else {
            int firstDigit = number % 10;
            int secondDigit = (int) (number / 10);

            return firstDigit + secondDigit;
        }
    }
    private int sumOfOddPlace(long number) {
        int result = 0;

        while (number > 0) {
            result += (int) (number % 10);
            number = number / 100;
        }

        return result;
    }

    private int sumOfDoubleEvenPlace(long number) {

        int result = 0;
        long temp = 0;

        while (number > 0) {
            temp = number % 100;
            result += getDigit((int) (temp / 10) * 2);
            number = number / 100;
        }

        return result;
    }

    private boolean prefixMatched(long number, int d) {
        System.out.println("Inside Match");
        System.out.println("Number is " + number);
        System.out.println(getPrefix(number,d));
        if ((getPrefix(number, d) == 4)
                || (getPrefix(number, d) == 5)
                || (getPrefix(number, d) == 3)) {

            if (getPrefix(number, d) == 4) {
            	cardType = "Visa";
                System.out.println("\nVisa Card ");
            } else if (getPrefix(number, d) == 5) {
            	cardType = "MasterCard";
                System.out.println("\nMaster Card ");
            } else if (getPrefix(number, d) == 3) {
            	cardType="American Express";
                System.out.println("\nAmerican Express Card ");
            }

            return true;

        } else {

            return false;

        }
    }

    private int getSize(long d) {

        int count = 0;

        while (d > 0) {
            d = d / 10;

            count++;
        }

        return count;

    }

    private long getPrefix(long number, int k) {

        if (getSize(number) < k) {
            return number;
        } else {

            int size = (int) getSize(number);

            for (int i = 0; i < (size - k); i++) {
                number = number / 10;
            }

            return number;

        }

    }
}