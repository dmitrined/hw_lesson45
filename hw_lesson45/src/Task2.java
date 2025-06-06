import excepcion.FileNotFoundException;
import excepcion.FolderNotFoundException;

import java.io.File;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        /*
        2
Допустим, дан текстовый файл. Ваша задача, вывести содержимое этого файла на экран.
         */

        try {
            // получаем объект File на исходный файл
            File sourceFile = getSourceFile("/Users/dmitrinedioglo/Desktop/temp/input.txt");

            //здесь пользуюсь методом из Task1 где читаем и выводим текст из файла
            Task1.readAndPrintFileContent(sourceFile);

        } catch (FileNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public static File getSourceFile(String strPath) {
        File result = new File(strPath);
        if (!result.exists() || !result.isFile()) {
            throw new FileNotFoundException("Файл '" + strPath + "' отсутствует");
        }
        return result;
    }
}
