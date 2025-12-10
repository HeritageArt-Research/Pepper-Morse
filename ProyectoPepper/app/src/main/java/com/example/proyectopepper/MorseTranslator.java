package com.example.proyectopepper;

// MorseTranslator.java
import java.util.HashMap;
import java.util.Map;

public class MorseTranslator {

    // Use a static block for thread-safe and efficient initialization of the map
    private static final Map<Character, String> MORSE_CODE_DICT = new HashMap<>();

    static {
        // --- Letters (A-Z) ---
        MORSE_CODE_DICT.put('A', ".-");      // Alpha
        MORSE_CODE_DICT.put('B', "-...");     // Bravo
        MORSE_CODE_DICT.put('C', "-.-.");     // Charlie
        MORSE_CODE_DICT.put('D', "-..");      // Delta
        MORSE_CODE_DICT.put('E', ".");        // Echo
        MORSE_CODE_DICT.put('F', "..-.");     // Foxtrot
        MORSE_CODE_DICT.put('G', "--.");      // Golf
        MORSE_CODE_DICT.put('H', "....");     // Hotel
        MORSE_CODE_DICT.put('I', "..");       // India
        MORSE_CODE_DICT.put('J', ".---");     // Juliet
        MORSE_CODE_DICT.put('K', "-.-");      // Kilo
        MORSE_CODE_DICT.put('L', ".-..");     // Lima
        MORSE_CODE_DICT.put('M', "--");       // Mike
        MORSE_CODE_DICT.put('N', "-.");       // November
        MORSE_CODE_DICT.put('O', "---");      // Oscar
        MORSE_CODE_DICT.put('P', ".--.");     // Papa
        MORSE_CODE_DICT.put('Q', "--.-");     // Quebec
        MORSE_CODE_DICT.put('R', ".-.");      // Romeo
        MORSE_CODE_DICT.put('S', "...");      // Sierra
        MORSE_CODE_DICT.put('T', "-");        // Tango
        MORSE_CODE_DICT.put('U', "..-");      // Uniform
        MORSE_CODE_DICT.put('V', "...-");     // Victor
        MORSE_CODE_DICT.put('W', ".--");      // Whiskey
        MORSE_CODE_DICT.put('X', "-..-");     // X-ray
        MORSE_CODE_DICT.put('Y', "-.--");     // Yankee
        MORSE_CODE_DICT.put('Z', "--..");     // Zulu

        // --- Numbers (0-9) ---
        MORSE_CODE_DICT.put('1', ".----");
        MORSE_CODE_DICT.put('2', "..---");
        MORSE_CODE_DICT.put('3', "...--");
        MORSE_CODE_DICT.put('4', "....-");
        MORSE_CODE_DICT.put('5', ".....");
        MORSE_CODE_DICT.put('6', "-....");
        MORSE_CODE_DICT.put('7', "--...");
        MORSE_CODE_DICT.put('8', "---..");
        MORSE_CODE_DICT.put('9', "----.");
        MORSE_CODE_DICT.put('0', "-----");

        // --- Punctuation and Spacing ---
        MORSE_CODE_DICT.put('.', ".-.-.-");   // Period/Full Stop
        MORSE_CODE_DICT.put(',', "--..--");   // Comma
        MORSE_CODE_DICT.put('?', "..--..");   // Question Mark
        MORSE_CODE_DICT.put('/', "-..-.");    // Slash/Fraction bar
        MORSE_CODE_DICT.put('=', "-...-");    // Equals sign (also BT: New paragraph)
        MORSE_CODE_DICT.put('+', ".-.-.");    // Plus sign
        MORSE_CODE_DICT.put('!', "-.-.--");   // Exclamation Mark
        MORSE_CODE_DICT.put('\'', ".----.");  // Apostrophe
        MORSE_CODE_DICT.put('(', "-.--.");    // Open Parenthesis
        MORSE_CODE_DICT.put(')', "-.--.-");   // Close Parenthesis
        MORSE_CODE_DICT.put('&', ".-...");    // Ampersand
        MORSE_CODE_DICT.put(':', "---...");   // Colon
        MORSE_CODE_DICT.put(';', "-.-.-.");   // Semicolon
        MORSE_CODE_DICT.put('"', ".-..-.");   // Quotation Mark
        MORSE_CODE_DICT.put('@', ".--.-.");   // At sign

        // --- Word Separator ---
        // Note: The forward slash '/' is technically the word break,
        // but we map a regular space to '/' to simplify input.
        MORSE_CODE_DICT.put(' ', "/");
    }

    /**
     * Translates a String of English text into a String of Morse code.
     * Characters are separated by a space. Words are separated by a '/'.
     */
    public static String translate(String text) {
        if (text == null || text.isEmpty()) return "";

        StringBuilder morseCode = new StringBuilder();
        String upperText = text.toUpperCase();

        for (char c : upperText.toCharArray()) {
            if (MORSE_CODE_DICT.containsKey(c)) {
                // Append the Morse code and a single space as a character separator
                morseCode.append(MORSE_CODE_DICT.get(c)).append(" ");
            }
            // Ignore any characters (like symbols or extended characters) not in the map
        }

        // Trim trailing whitespace at the end
        return morseCode.toString().trim();
    }
}
