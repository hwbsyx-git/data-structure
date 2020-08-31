package binarysearchwithoutrecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 10);
        System.out.println("index=" + index);
    }

    /**
     * 不用递归实现二分查找
     * @param arr 传入数组，默认为升序排列好的数组
     * @param target 查找目标值
     * @return 目标值所在数组中的下标
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
