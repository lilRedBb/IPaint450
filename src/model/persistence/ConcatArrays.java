package model.persistence;

/**
 * @author lilred
 * @date 2023/08/07
 **/
public class ConcatArrays {
    public static int[] concatArrays(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}
