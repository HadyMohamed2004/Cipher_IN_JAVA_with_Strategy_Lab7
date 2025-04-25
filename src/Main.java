import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;


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
    public void performAction(File file);
}


class InMemoryCipherStrategy implements Cipher {
    public void performAction(File file) {
        try{
            byte[] read = Files.readAllBytes(file.toPath());
            String text = new String(read);

            StringBuilder builder = new StringBuilder();
            for (char c : text.toCharArray()) {
                builder.append(CaesarCipher.encryptChar(c));
            }
        System.out.println(builder.toString());

        }
        catch(IOException e){
            System.out.println("Error handling File: "+e);
        }
    }
}


class SwaptToDiskCipher implements Cipher {
    public void performAction(File file) {
// swapt partial results to file.
        System.out.println("Cipher performed action");
        System.out.println(caesar.encryptChar('C'));
    }
}


// Context Class
class Encrypt{
    private Cipher cipher;

    public void encrypt_file(String fileName) {
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
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter path to file: ");
        String path = scanner.nextLine().trim();


        Encrypt encrypt = new Encrypt();
        encrypt.encrypt_file(path);
    }
}