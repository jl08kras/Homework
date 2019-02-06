import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * This class has methods that simulate the allscrabblewords.com website
 *
 * @author Julia Krasinski
 * @version 1/28/2019
 */
public class Unscrambler
{
    private static String word;
    private static Scanner scan;
    private static ArrayList<String> list;
    private static File newFile;
    /**
     * Constructor for objects of class Unscrambler
     * @param file, takes in the name of the file that is used throughout the class
     */
    public Unscrambler(String file) throws FileNotFoundException
    {
        newFile = new File(file);

    }
    
    /**
     * This method looks through a file and determines all the words in that file that are 
     * contained in the target word
     * @param String target, word that is checked by the method
     */
    public static void findWords(String target) throws FileNotFoundException{
        //reads file through the scanner
        scan = new Scanner(newFile);
        
        //creates new arraylist 
        ArrayList<String> list = new ArrayList<String>();
        int count;
        while(scan.hasNextLine()){
            word = scan.nextLine();
            if(contains(target, word)){
                list.add(word.toLowerCase());
            }
        }

        int length = target.length();

        for(int i = length; i >= 2; i--){
            count = 0;
            System.out.println(i + " letter words made by unscrambling the letters in " + target );
            
            //This loop goes through all the strings in the arraylist of words contained
            //in the target word
            for(String a : list){
                //If the length of word in the arraylist is equal to i it prints the 
                //word, as long as the line does not go over 40 characters
                if(a.length() == i) {
                    if(count > (40 -a.length())){                        
                        System.out.print("\n");
                        System.out.print(a + " ");
                        count = 0;
                        count += (a.length() + 1);
                    }
                    else{
                        System.out.print(a + " ");
                        count += (a.length() + 1);
                    }
                }

            }
            //decrements length, and continues going through the loop
            length--;
            System.out.println("\n");
        }
    }

    public static void main(String[]args) throws FileNotFoundException{
        Unscrambler test = new Unscrambler("words.txt");
        test.findWords("state");
        test.findWords("abernathy");
        test.findWords("journalizing");
        test.findWords("crackerjacks");
    }

    /**
     * This method takes in two string variables and determines whether or not
     * the letters in the second are all contained in the first
     * @param two string variables
     * @return a boolean statement showing whether or not the target is contained
     * in the text
     */
    public static boolean contains(String text, String target)
    {
        // Converting both Strings to lower case because this method is not case sensitive
        text = text.toLowerCase();
        target = target.toLowerCase();

        //Creating arraylists to hold the letters from the words for later comparisons
        ArrayList<Character> text1 = new ArrayList<Character>();
        ArrayList<Character> target1 = new ArrayList<Character>();

        // Converting the two Strings into arrays that hold the characters making up the string
        char[] text2 = text.toCharArray();
        char[] target2 = target.toCharArray();

        int textLength = text.length()-1;
        int targetLength = target.length()-1;

        int count = 0;

        if(targetLength == 0) {
            return true;
        }
        else if(textLength == 0 || targetLength > textLength){
            return false;
        }

        for (char x : target2){
            target1.add(x);
        }

        for (char y : text2){
            text1.add(y);
        }

        for (Character x : target1){
            for (Character y : text1){
                //If the characters match that letter is then removed from the ArrayList so that it's
                //not counted twice
                if (x == y){
                    text1.remove(y);
                    count++;
                    break;
                }
            }
        }

        if (count == target.length()){
            return true;
        }
        else{
            return false;
        }
    }
}

