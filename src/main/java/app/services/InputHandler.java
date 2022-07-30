package app.services;

public class InputHandler {

    public static boolean verifyNumberOfQs(String input, int maximum){
        try {
            int amount = Integer.parseInt(input);
            if(amount > 0 && amount <= maximum){
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nfe){
//            System.out.println("not a number");
            return false;
        }
    }

}
