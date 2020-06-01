package io.github.jaccastonguay.detailedtimer;

public class Helper {

    public static String ConvertToMinuteFormat(int seconds){

        int minutes = (int) Math.floor((seconds/60.0));
        int remainingSecond = seconds % 60;
        String stringSeconds = Integer.toString(remainingSecond);
        if(stringSeconds.equals("0")){
            stringSeconds="00";
        }
        return minutes + ":" + stringSeconds;
    }
}
