import excepcion.CreateFileException;
import excepcion.CreateFolderException;
import excepcion.FolderNotFoundException;

import java.io.*;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        /*
        1
С помощью методов File создайте папку \temp Напишите метод который, позволяет пользователю вводить
строки со сканера и записывать их в файл \temp\input.txt Как только пользователь введет "-exit"
ваша программа должна вывести на экран весь введенный пользователем текст.
         */
        try {
            // получаем объект File на исходную папку
            File desktopDir = getSourceFolder("/Users/dmitrinedioglo/Desktop");

            // создаем папку для копирования
            File tempDir = createFolder(desktopDir, "temp");

            // создаем Файл для копирования
            File inputFile = createFile(tempDir, "input.txt");

            //предоставляем возможность пользователю написать строки и записываем в Файл
            writeUserInputToFile(inputFile);

            //читаем и выводим текст из файла
            readAndPrintFileContent(inputFile);

        } catch (FolderNotFoundException | CreateFolderException | CreateFileException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static File getSourceFolder(String strPath) {
        File result = new File(strPath);
        if (!result.exists() || !result.isDirectory()) {
            throw new FolderNotFoundException("Папка '" + strPath + "' отсутствует");
        }
        return result;
    }

    public static File createFolder(File targetFolder, String FolderName) {
        File newFolder = new File(targetFolder, FolderName);
        boolean created = newFolder.mkdir();
        if (!created) {
            throw new CreateFolderException("Не получилось создать папку " + newFolder);
        }
        return newFolder;
    }

    public static File createFile(File targetFolder, String fileName) throws IOException {
        File newFile = new File(targetFolder, fileName);
        boolean created = newFile.createNewFile();
        if (!created) {
            throw new CreateFileException("Не получилось создать файл " + newFile);
        }
        return newFile;
    }

    public static void writeUserInputToFile(File file) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            System.out.println("Введите текст для записи в файл. Введите 'exit' на отдельной строке для завершения.");
            String line;
            while (true) {
                line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                bw.write(line);
                bw.newLine();
            }
        }catch (IOException e){
            System.out.println("Не получилось записать файл "+file);
        }
    }

    public static void readAndPrintFileContent(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Не получилось прочитать файл "+file);
        }
    }

}


