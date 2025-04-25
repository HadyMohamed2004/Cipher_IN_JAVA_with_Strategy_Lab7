import java.io.File;
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
    public void performAction();
}


class InMemoryCipherStrategy implements Cipher {
    public void performAction() {
// load in byte[] ....
        System.out.println("Cipher performed action");
        System.out.println(caesar.encryptChar('A'));
    }
}


class SwaptToDiskCipher implements Cipher {
    public void performAction() {
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

        cipher.performAction();
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