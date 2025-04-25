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
    public void performAction();
}


class InMemoryCipherStrategy implements Cipher {
    public void performAction() {
// load in byte[] ....
    }
}


class SwaptToDiskCipher implements Cipher {
    public void performAction() {
// swapt partial results to file.
    }
}


// Context Class
class Encrypt{
    private Cipher cipher;

    public Encrypt(String filename) {}
}




public class Main {
    public static void main(String[] args) {
        Encrypt encrypt = new Encrypt("txt.txt");
    }
}