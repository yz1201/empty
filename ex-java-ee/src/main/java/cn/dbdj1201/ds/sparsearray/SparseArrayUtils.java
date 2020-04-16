package cn.dbdj1201.ds.sparsearray;

import java.io.*;

/**
 * @author tyz1201
 * @datetime 2020-04-15 21:29
 **/
public class SparseArrayUtils {


    /**
     * 二维数组转稀疏数组
     *
     * @param arrays
     * @return
     */
    public static int[][] toSparseArray(int[][] arrays) throws IOException {

        /*
         * 初始化二维数组
         * <p>
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 1 0 0 0 0 0 0 0 0
         *     0 0 0 0 2 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         * </p>
         */

        /*
         * 稀疏数组
         * <p>
         *     11 11 2
         *     1  2  1
         *     2  4  2
         * </p>
         */
        int sum = 0;
        for (int[] array : arrays) {
            for (int i : array) {
                if (i != 0)
                    sum++;
            }
        }

        int rowLen = arrays.length;
        int columnLen = arrays[0].length;
        int[][] sparseArray = new int[sum + 1][3];

        //稀疏数组默认3列，行数由原始数组的不同值确定。
        sparseArray[0][0] = rowLen;              //第一行初始化，第一列原始数组行数
        sparseArray[0][1] = columnLen;           //第二列原始数组列数
        sparseArray[0][2] = sum;                        //第三列为不同值的总数

        //将非0的数放入稀疏数组
        //count：标识第几个非0数
        int count = 1;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                if (arrays[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = arrays[i][j];
                    count++;
                }
            }
        }


        File file = new File("D:\\test\\sparsearray\\data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        for (int[] ints : sparseArray) {
            for (int j = 0; j < 3; j++) {
                writer.write(String.valueOf(ints[j]));
            }
        }
        writer.flush();
        writer.close();
        return sparseArray;
    }


    /**
     * 稀疏数组转正常二维数组
     *
     * @param sparseArray
     * @return
     */
    public static int[][] toArray(int[][] sparseArray) {
        int rowLen = sparseArray[0][0];
        int columnLen = sparseArray[0][1];
        int[][] array = new int[rowLen][columnLen];

        for (int i = 1; i <= sparseArray[0][2]; i++) {
            array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        //初始化原数组
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][4] = 2;

        int[][] sparseArray = SparseArrayUtils.toSparseArray(array);
        int[][] newArr = SparseArrayUtils.toArray(sparseArray);

//        Arrays.stream(SparseArrayUtils.toSparseArray(array)).forEach(ints -> Arrays.stream(ints).forEach(System.out::println));
//        Arrays.stream(newArr).forEach(ints -> Arrays.stream(ints).forEach(System.out::println));
    }
}
