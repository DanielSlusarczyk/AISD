package pl.edu.pw.ee;

class LongestCommonSubsequence {
    private String leftStr;
    private String topStr;
    private int[][] array;
    private char[][] arrayToDisplay;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        validateInput(topStr, leftStr);
        this.leftStr = leftStr;
        this.topStr = topStr;
        fillArray();
    }

    public String findLCS() {
        String longestSub = "";
        if (leftStr.length() == 0 || topStr.length() == 0) {
            return longestSub;
        }
        int actualRowIndex = array.length - 1;
        int actualColIndex = array[0].length - 1;

        while (actualColIndex >= 0 && actualRowIndex >= 0) {
            if (Character.compare(leftStr.charAt(actualRowIndex), topStr.charAt(actualColIndex)) == 0) {
                longestSub = longestSub + leftStr.charAt(actualRowIndex);
                actualColIndex--;
                actualRowIndex--;
            } else if (getValueAbove(actualRowIndex, actualColIndex) >= getValueOnLeft(actualRowIndex,
                    actualColIndex)) {
                actualRowIndex--;
            } else {
                actualColIndex--;
            }
        }

        char[] longestSubArray = longestSub.toCharArray();
        int left = 0;
        int right = longestSubArray.length - 1;
        while (left < right) {
            char temp = longestSubArray[left];
            longestSubArray[left] = longestSubArray[right];
            longestSubArray[right] = temp;
            left++;
            right--;
        }
        return new String(longestSubArray);
    }

    public void display() {
        prepareDisplayedArray();

        for (char[] rowArray : arrayToDisplay) {
            for (char cell : rowArray) {
                System.out.print(cell);
            }
        }
    }

    public char[][] getArrayToDisplay() {
        prepareDisplayedArray();
        return arrayToDisplay;
    }

    private void fillArray() {
        array = new int[leftStr.length()][topStr.length()];
        for (int row = 0; row < leftStr.length(); row++) {
            for (int column = 0; column < topStr.length(); column++) {
                if (Character.compare(leftStr.charAt(row), topStr.charAt(column)) == 0) {
                    array[row][column] = getValueDiagonally(row, column) + 1;
                } else if (getValueAbove(row, column) >= getValueOnLeft(row, column)) {
                    array[row][column] = getValueAbove(row, column);
                } else {
                    array[row][column] = getValueOnLeft(row, column);
                }
            }
        }
    }

    private void prepareDisplayedArray() {
        int spacePerNumber = 0;
        if (array.length != 0 && array[0].length != 0) {
            spacePerNumber = String.valueOf(array[array.length - 1][array[0].length - 1]).length();
        }
        this.arrayToDisplay = new char[2 * leftStr.length() + 3][(spacePerNumber + 2) * (topStr.length() + 1) + 5];

        setSpaces();
        setAxes(spacePerNumber);
        setNumbers(spacePerNumber);
        delineatePath(spacePerNumber);
    }

    private void setSpaces() {
        for (int row = 0; row < arrayToDisplay.length; row++) {
            for (int column = 0; column < arrayToDisplay[0].length; column++) {
                if (column == arrayToDisplay[0].length - 1) {
                    arrayToDisplay[row][arrayToDisplay[0].length - 1] = '\n';
                } else {
                    arrayToDisplay[row][column] = ' ';
                }
            }
        }
    }

    private void setAxes(int spacePerNumber) {
        arrayToDisplay[2][4] = '0';
        for (int column = 8; column < arrayToDisplay[0].length - 1; column = column + spacePerNumber + 2) {
            int actualColumn = (column - 8) / (spacePerNumber + 2);
            if (!setSpecialSign(0, column, topStr.charAt(actualColumn))) {
                arrayToDisplay[0][column] = topStr.charAt(actualColumn);
            }
            arrayToDisplay[2][column] = '0';
        }
        for (int row = 4; row < arrayToDisplay.length; row = row + 2) {
            int actualRow = (row - 4) / 2;
            if (!setSpecialSign(row, 0, leftStr.charAt(actualRow))) {
                arrayToDisplay[row][0] = leftStr.charAt(actualRow);
            }
            arrayToDisplay[row][4] = '0';
        }
    }

    private boolean setSpecialSign(int rowIndex, int columnIndex, char sign) {
        if (sign == '\n') {
            arrayToDisplay[rowIndex][columnIndex] = '\\';
            arrayToDisplay[rowIndex][columnIndex + 1] = 'n';
            return true;
        } else if (sign == '\t') {
            arrayToDisplay[rowIndex][columnIndex] = '\\';
            arrayToDisplay[rowIndex][columnIndex + 1] = 't';
            return true;
        } else if (sign == '\r') {
            arrayToDisplay[rowIndex][columnIndex] = '\\';
            arrayToDisplay[rowIndex][columnIndex + 1] = 'r';
            return true;
        } else if (sign == ' ') {
            arrayToDisplay[rowIndex][columnIndex] = '\\';
            arrayToDisplay[rowIndex][columnIndex + 1] = 's';
            return true;
        }
        return false;
    }

    private void setNumbers(int spacePerNumber) {
        for (int row = 4; row < arrayToDisplay.length; row = row + 2) {
            for (int column = 8; column < arrayToDisplay[0].length - 1; column = column + 2 + spacePerNumber) {
                int actualRow = (row - 4) / 2;
                int actualColumn = (column - 8) / (spacePerNumber + 2);
                String number = String.valueOf(array[actualRow][actualColumn]);
                for (int i = 0; i < number.length(); i++) {
                    arrayToDisplay[row][column + i] = number.charAt(i);
                }
            }
        }
    }

    private void delineatePath(int spacePerNumber) {
        if (array.length != 0 && array[0].length != 0) {
            int rowIndexPosition = array.length - 1;
            int columnIndexPosition = array[0].length - 1;
            while (rowIndexPosition >= 0 && columnIndexPosition >= 0) {
                int correspondingRow = 4 + rowIndexPosition * 2;
                int correspondingColumn = 8 + columnIndexPosition * (spacePerNumber + 2);
                if (Character.compare(leftStr.charAt(rowIndexPosition), topStr.charAt(columnIndexPosition)) == 0) {
                    arrayToDisplay[correspondingRow - 1][correspondingColumn - 2] = '\\';
                    rowIndexPosition--;
                    columnIndexPosition--;
                } else if (getValueAbove(rowIndexPosition, columnIndexPosition) >= getValueOnLeft(rowIndexPosition,
                        columnIndexPosition)) {
                    arrayToDisplay[correspondingRow - 1][correspondingColumn] = '^';
                    rowIndexPosition--;
                } else {
                    arrayToDisplay[correspondingRow][correspondingColumn - 2] = '<';
                    columnIndexPosition--;
                }
            }
        }
    }

    private int getValueAbove(int row, int column) {
        if (row <= 0) {
            return 0;
        }
        int rowAbove = row - 1;
        return array[rowAbove][column];
    }

    private int getValueOnLeft(int row, int column) {
        if (column <= 0) {
            return 0;
        }
        int columnOnLeft = column - 1;
        return array[row][columnOnLeft];
    }

    private int getValueDiagonally(int row, int column) {
        if (row <= 0 || column <= 0) {
            return 0;
        }
        int rowAbove = row - 1;
        int columnOnLeft = column - 1;
        return array[rowAbove][columnOnLeft];
    }

    private void validateInput(String firstStr, String secondStr) {
        if (firstStr == null || secondStr == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
    }
}
