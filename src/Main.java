import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Paths;



class CaesarCipher {
    public static char encryptChar(char character) {
        int shift = 3;

        if (Character.isLetter(character)) {
            char base = Character.isUpperCase(character) ? 'A' : 'a';
            return (char) ((character - base + shift) % 26 + base);
        } else {
            return character;
        }
    }
}

interface Cipher {
    CaesarCipher caesar = new CaesarCipher();

    public void performAction(File file) throws IOException;
}


class InMemoryCipherStrategy implements Cipher {
    public void performAction(File file) {
        try {
            byte[] read = Files.readAllBytes(file.toPath());
            String text = new String(read);

            StringBuilder builder = new StringBuilder();
            for (char c : text.toCharArray()) {
                builder.append(CaesarCipher.encryptChar(c));
            }

            System.out.println("Encrypted Text: " + builder.toString());

            // Write encrypted text to new file
            String outputPath = file.getPath() + "-shortencrypted.txt";
            Files.write(Paths.get(outputPath), builder.toString().getBytes());

            System.out.println("Encrypted content written to: " + outputPath);

        } catch (IOException e) {
            System.out.println("Error handling File: " + e);
        }
    }
}


class SwaptToDiskCipher implements Cipher {
    public void performAction(File file) throws IOException {

        // swapt partial results to file.

        String outputFilePath = file.getPath() + "-longencrypted.txt";

        FileReader reader = new FileReader(file);
        FileWriter writer = new FileWriter(outputFilePath);

        int c;
        while ((c = reader.read()) != -1) {
            char encryptedChar = CaesarCipher.encryptChar((char) c);
            writer.write(encryptedChar);
        }

        System.out.println("Encrypted content written to: " + outputFilePath);
    }
}

// Context Class
class Encrypt {
    private Cipher cipher;

    public void encrypt_file(String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File does not exist at the provided path.");
            return;
        }

        if (file.length() < 1024L * 1024L) {
            cipher = new InMemoryCipherStrategy();
        } else {
            cipher = new SwaptToDiskCipher();
        }

        cipher.performAction(file);
    }
}


public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("File Encryption System");
        System.out.println("By");
        System.out.println("Hady Mohammed Meawad - 20230455");
        System.out.println("Mohamed Mahmoud Bayoumy - 20230620");
        System.out.println("===========================");
        System.out.println("This program encrypts a text file using Caesar Cipher.");

        System.out.print("Enter path to file: ");
        String path = scanner.nextLine().trim();


        Encrypt encrypt = new Encrypt();
        encrypt.encrypt_file(path);
    }
}