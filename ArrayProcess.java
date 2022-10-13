class ArrayProcess {
    /**
     * Checks whether a descending sorted array contains the given value.
     * Method using simple binary search algorithm implementation.
     * @param numbers Array of integers sorted in descending order.
     * @param x Given integer value.
     * @return boolean value - {@value true} if an array {@value numbers} contains {@value x}
     *         or {@value false} if not.
     */
    private boolean search(int[] numbers, int x) {
    
        if (x > numbers[0]) {
            return false;
        }
        int startIdx = 0;
        int endIdx = numbers.length - 1;
    
        while (startIdx <= endIdx) {
            int pivotIdx = startIdx + (endIdx - startIdx) / 2;
            if (x == numbers[pivotIdx]) {
                return true;
            } else if (x < numbers[pivotIdx]) {
                startIdx = pivotIdx + 1;
            } else {
                endIdx = pivotIdx - 1;
            }
        }
        return false;
    }
}
