package co.sidhant.allThe;


import com.gtranslate.Audio;
import com.gtranslate.Language;

import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;

public class namesOfGod
{
    public static void main(String[] args) throws InterruptedException, IOException, JavaLayerException {
        Digit smallest, biggest;

        smallest = new Digit();
        biggest = smallest;

        long digitCount = 1;

        Audio audio = Audio.getInstance();
        InputStream sound = audio.getAudio("one", Language.ENGLISH);
        Digit curDigit = smallest;
        String curNum;
        long triplets;
        //Rolls the numbers up to value
        int value = 143;
        long startTime;
        long avgTime = 0;
        // this value should be 1000000000000000L; but that would take 6000 years before it would even get to start reading the number
        long exponent = 10000000;
        long oldExp = exponent;
        while(exponent != 0)
        {
            startTime = System.nanoTime();
            while(value!= 0)
            {
                smallest.upvote();
                while(biggest.left != null)
                {
                    biggest = biggest.left;
                    digitCount++;
                }
                value--;
            }
            value = 143;
            exponent--;
            avgTime += System.nanoTime() - startTime;
        }

        System.out.println("done rolling " + digitCount * 3);
        avgTime /= oldExp;
        System.out.println("average time was " + avgTime);

        while(true)
        {
            smallest.upvote();
            while(biggest.left != null)
            {
                biggest = biggest.left;
                digitCount++;
            }

            curDigit = biggest;
            triplets = digitCount - 1;
            while(triplets != -1)
            {
                curNum = Short.toString(curDigit.digit);

                if(curNum.equals("999"))
                {
                    curNum = "nine hundred ninety nine";
                }
                else if(curNum.equals("0"))
                    curNum = "";

                if(!curNum.equals(""))
                {
                    //TODO: fix cast
                    curNum += getAdjective((int)triplets);
                    if(curDigit == smallest)
                        curNum = "and " + curNum;
                    sound = audio.getAudio(curNum, Language.ENGLISH);
                    audio.play(sound);
                    sound.close();
                }
                triplets--;
                curDigit = curDigit.right;
            }
            Thread.currentThread().sleep(1000);
        }
    }

    private static String getAdjective(int triplets)
    {
        String result = "";
        switch (triplets)
        {
            case 1:
                result = "thousand";
                break;
            case 2:
                result = "million";
                break;
            case 3:
                result = "billion";
                break;
            case 4:
                result = "trillion";
                break;
            case 5:
                result = "quadrillion";
                break;
            case 6:
                result = "Quintillion";
                break;
            case 7:
                result = "Sextillion";
                break;
            case 8:
                result = "Septillion";
                break;
            case 9:
                result = "Octillion";
                break;
            case 10:
                result = "Nonillion";
                break;
            case 11:
                result = "Decillion";
                break;
            case 12:
                result = "Undecillion";
                break;
            case 13:
                result = "Duodecillion";
                break;
            case 14:
                result = "Tredecillion";
                break;
            case 15:
                result = "Quattuordecillion";
                break;
            case 16:
                result = "Quindecillion";
                break;
            case 17:
                result = "Sexdecillion";
                break;
            case 18:
                result = "Septendecillion";
                break;
            case 19:
                result = "Octodecillion";
                break;
            case 20:
                result = "Novemdecillion";
                break;
            case 21:
                result = "Vigintillion";
                break;
            case 101:
                result = "centillion";
                break;
            //TODO: numbers that aren't on this list
        }
        return result;
    }

    public static class Digit
    {
        public short digit;
        public Digit right;
        public Digit left;

        public Digit(Digit right)
        {
            digit = 1;
            this.right = right;
        }

        public Digit()
        {
            digit = 0;
        }

        public void upvote()
        {
            digit++;
            if(digit == 1000)
            {
                digit = 0;
                if(left != null)
                {
                    left.upvote();
                }
                else
                {
                    left = new Digit(this);
                }
            }
        }
    }
}