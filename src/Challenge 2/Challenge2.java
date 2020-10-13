
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Challenge2 {
    public static void main(String[] args) {
        writeFile();
        readFile();
    }

    private static void writeFile() {
        try {

            File file = new File("data.txt");
            OutputStream outStream = new FileOutputStream(file);
            DataOutputStream dataStream = new DataOutputStream(outStream);

            // Direkt als Zeichenkette
            String data1 = "Data as Strings" + System.lineSeparator();
            dataStream.writeBytes(data1);

            // Direkt als Bytes
            byte[] data2 = "Data as Bytes".getBytes();
            dataStream.write(data2);

            dataStream.flush();
            dataStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void readFile() {

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream("data.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
            System.exit(0);
        }

        try (Scanner scanner = new Scanner(fileStream)) {
            System.err.println("Read File: " + System.lineSeparator());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (NoSuchElementException e) {
            System.err.println("couldn’t read file");
        }
    }
}
