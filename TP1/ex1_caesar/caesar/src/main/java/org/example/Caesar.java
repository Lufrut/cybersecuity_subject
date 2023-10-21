package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Caesar
{
    protected static final char[] alphabetUpper = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    protected static final  char[] alphabetDown = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    static char shiftLetter(char letter, int shift) {
        if (letter != ' ') {
            for (int k = 0; k < alphabetUpper.length; k++) {
                if (alphabetUpper[k] == letter) {
                    int newIndex = k + shift;
                    if (newIndex >= alphabetUpper.length) newIndex -= alphabetUpper.length;
                    return alphabetUpper[newIndex];
                } else if (alphabetDown[k] == letter) {
                    int newIndex = k + shift;
                    if (newIndex >= alphabetDown.length) newIndex -= alphabetDown.length;
                    return alphabetDown[newIndex];
                }
            }
        }
        return letter;
    }
    static void processCharArr(char[] charArr){
        WordStorageSingleton wordStorage = WordStorageSingleton.getInstance();
        String str = String.valueOf(charArr);
        String[] words = str.split(" ");
        List<String> out = Arrays.stream(words)
                .filter(pred -> (wordStorage.dictionary.contains(pred)))
                .toList();

        if((out.size() + 1)  == words.length) {
            for (String word :
                    words) {
                System.out.print(word + " ");
            }
        }
    }

    public static void main( String[] args ) {


        Scanner keyboard = new Scanner(System.in);
        String str = keyboard.nextLine();
        char[] charArr = str.toCharArray();
        for (int i = 1; i <= alphabetUpper.length; i++) {
            for (int j = 0; j < charArr.length; j++) {
                charArr[j] = shiftLetter(charArr[j], i);
            }
        processCharArr(charArr);

        }
    }
}
