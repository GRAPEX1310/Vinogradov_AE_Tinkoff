package edu.hw1;

public class Task7 {

    private Task7() {
    }

    public static int rotateRight(int givenNumber, int shift) {
        if (shift < 0 || givenNumber < 0) {
            return givenNumber;
        }

        char[] binNumberArray = Integer.toBinaryString(givenNumber).toCharArray();

        int shiftMin = shift % binNumberArray.length;

        char[] resultNumber = new char[binNumberArray.length];

        for (int index = 0; index < binNumberArray.length; index++) {
            int newIndexPosition = index + shiftMin;

            if (newIndexPosition >= binNumberArray.length) {
                newIndexPosition -= binNumberArray.length;
            }

            resultNumber[newIndexPosition] = binNumberArray[index];
        }
        return Integer.parseInt(new String(resultNumber), 2);
    }

    public static int rotateLeft(int givenNumber, int shift) {
        if (shift < 0 || givenNumber < 0) {
            return givenNumber;
        }

        char[] binNumberArray = Integer.toBinaryString(givenNumber).toCharArray();

        int newShift = binNumberArray.length - shift % binNumberArray.length;

        return rotateRight(givenNumber, newShift);
    }
}
