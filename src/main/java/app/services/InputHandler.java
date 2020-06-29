package app.services;

public class InputHandler {

    public static boolean verifyNumberOfQs(String input){
        try {
            int amount = Integer.parseInt(input);
            if(amount > 0 && amount <= SqlCaller.getDBSize()){
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
