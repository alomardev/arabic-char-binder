package com.alomardev.arabiccharbinder;

import java.util.ArrayList;
import java.util.Arrays;

public class ArabicCharBinder {

    public static final int FLAG_IGNORE_ARABIC_DIGITS = 0b1;
    public static final int FLAG_REVERSE_CHARS = 0b10;

    public static final char[] sBoundCharPN = new char[]{
            '\0', 'ﺒ', 'ﺘ', 'ﺜ', 'ﺠ', 'ﺤ', 'ﺨ', '\0', '\0', '\0', '\0', 'ﺴ', 'ﺸ', 'ﺼ', 'ﻀ', 'ﻄ', 'ﻈ', 'ﻌ', 'ﻐ', 'ﻔ', 'ﻘ', 'ﻜ', 'ﻠ', 'ﻤ', 'ﻨ', 'ﻬ', '\0', 'ﻴ', 'ﯩ', '\0', '\0', '\0', 'ﺌ', '\0', '\0', '\0', '\0', '\0', '\0', '\0'
    };

    public static final char[] sBoundCharsP = new char[]{
            'ﺎ', 'ﺐ', 'ﺖ', 'ﺚ', 'ﺞ', 'ﺢ', 'ﺦ', 'ﺪ', 'ﺬ', 'ﺮ', 'ﺰ', 'ﺲ', 'ﺶ', 'ﺺ', 'ﺾ', 'ﻂ', 'ﻆ', 'ﻊ', 'ﻎ', 'ﻒ', 'ﻖ', 'ﻚ', 'ﻞ', 'ﻢ', 'ﻦ', 'ﻪ', 'ﻮ', 'ﻲ', 'ﻰ', 'ﺄ', 'ﺈ', 'ﺆ', 'ﺊ', 'ﺂ', 'ﻼ', 'ﻸ', 'ﻺ', 'ﻶ', 'ﺔ', '\0'
    };

    public static final char[] sBoundCharsN = new char[]{
            '\0', 'ﺑ', 'ﺗ', 'ﺛ', 'ﺟ', 'ﺣ', 'ﺧ', '\0', '\0', '\0', '\0', 'ﺳ', 'ﺷ', 'ﺻ', 'ﺿ', 'ﻃ', 'ﻇ', 'ﻋ', 'ﻏ', 'ﻓ', 'ﻗ', 'ﻛ', 'ﻟ', 'ﻣ', 'ﻧ', 'ﻫ', '\0', 'ﻳ', 'ﯨ', '\0', '\0', '\0', 'ﺋ', '\0', '\0', '\0', '\0', '\0', '\0', '\0'
    };

    public static boolean isConnector(char c) {
        switch (c) {
            case 'ب':
            case 'ت':
            case 'ث':
            case 'ج':
            case 'ح':
            case 'خ':
            case 'س':
            case 'ش':
            case 'ص':
            case 'ض':
            case 'ط':
            case 'ظ':
            case 'ع':
            case 'غ':
            case 'ف':
            case 'ق':
            case 'ك':
            case 'ل':
            case 'م':
            case 'ن':
            case 'ه':
            case 'ي':
            case 'ى':
            case 'ئ':
            case 'ـ':
                return true;
            default:
                return false;
        }
    }

    public static boolean isIsolator(char c) {
        return getArabicIndex(c) > -1 && !isConnector(c);
    }

    public static int getArabicIndex(char c) {
        switch (c) {
            case 'ا':
                return 0;
            case 'ب':
                return 1;
            case 'ت':
                return 2;
            case 'ث':
                return 3;
            case 'ج':
                return 4;
            case 'ح':
                return 5;
            case 'خ':
                return 6;
            case 'د':
                return 7;
            case 'ذ':
                return 8;
            case 'ر':
                return 9;
            case 'ز':
                return 10;
            case 'س':
                return 11;
            case 'ش':
                return 12;
            case 'ص':
                return 13;
            case 'ض':
                return 14;
            case 'ط':
                return 15;
            case 'ظ':
                return 16;
            case 'ع':
                return 17;
            case 'غ':
                return 18;
            case 'ف':
                return 19;
            case 'ق':
                return 20;
            case 'ك':
                return 21;
            case 'ل':
                return 22;
            case 'م':
                return 23;
            case 'ن':
                return 24;
            case 'ه':
                return 25;
            case 'و':
                return 26;
            case 'ي':
                return 27;
            case 'ى':
                return 28;
            case 'أ':
                return 29;
            case 'إ':
                return 30;
            case 'ؤ':
                return 31;
            case 'ئ':
                return 32;
            case 'آ':
                return 33;
            case 'ﻻ':
                return 34;
            case 'ﻹ':
                return 35;
            case 'ﻷ':
                return 36;
            case 'ﻵ':
                return 37;
            case 'ة':
                return 38;
            case 'ـ':
                return 39;
            default:
                return -1;
        }
    }

    public static char convertToHinduDigit(char c) {
        switch (c) {
            case '0':
                return '٠';
            case '1':
                return '١';
            case '2':
                return '٢';
            case '3':
                return '٣';
            case '4':
                return '٤';
            case '5':
                return '٥';
            case '6':
                return '٦';
            case '7':
                return '٧';
            case '8':
                return '٨';
            case '9':
                return '٩';
            default:
                return c;
        }
    }

    public static String bind(String input) {
        return bind(input, 0);
    }

    public static String bind(String original, int flags) {
        boolean ignoreArabicDigits = (flags & FLAG_IGNORE_ARABIC_DIGITS) == FLAG_IGNORE_ARABIC_DIGITS;
        boolean reverseChars = (flags & FLAG_REVERSE_CHARS) == FLAG_REVERSE_CHARS;

        // Convert chars from normal to bound
        char[] input = original.toCharArray();
        int len = input.length;

        char[] output = new char[len];

        // Splitting each line
        ArrayList<char[]> lines = split('\n', input);
        int pointer = 0;
        boolean first = true;
        for (char[] line : lines) {
            if (!first) {
                output[pointer] = '\n';
                pointer++;
            }

            int l = line.length;
            for (int i = 0; i < l; i++) {
                int fi = !reverseChars ? i : l - i - 1;
                char p = i > 0 ? line[i - 1] : '\0';
                char c = line[i];
                char n = i < l - 1 ? line[i + 1] : '\0';

                if (c == 'ل') {
                    switch (n) {
                        case 'ا':
                            i++;
                            c = 'ﻻ';
                            break;
                        case 'أ':
                            i++;
                            c = 'ﻷ';
                            break;
                        case 'إ':
                            i++;
                            c = 'ﻹ';
                            break;
                        case 'آ':
                            i++;
                            c = 'ﻵ';
                    }
                }

                boolean connectP = isConnector(p);
                boolean connectN = getArabicIndex(n) > -1 && !isIsolator(c);

                char newChar = '\0';
                int index = getArabicIndex(c);
                if (index > -1) {
                    if (connectP && connectN) {
                        newChar = sBoundCharPN[index];
                    } else if (connectP) {
                        newChar = sBoundCharsP[index];
                    } else if (connectN) {
                        newChar = sBoundCharsN[index];
                    }
                }

                if (c >= '0' && c <= '9' && !ignoreArabicDigits) {
                    newChar = convertToHinduDigit(c);
                }
                newChar = newChar == '\0' ? c : newChar;
                output[pointer + fi] = newChar;
            }

            pointer += l;
            first = false;
        }

        return remove('\0', output);
    }

    private static ArrayList<char[]> split(char d, char[] arr) {
        ArrayList<char[]> list = new ArrayList<>();
        int len = arr.length;
        int s = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == d) {
                list.add(Arrays.copyOfRange(arr, s, i));
                s = i + 1;
                continue;
            }
            if (i == len - 1) {
                list.add(Arrays.copyOfRange(arr, s, len));
                break;
            }
        }
        return list;
    }

    private static String remove(char c, char[] arr) {
        int len = arr.length;
        char[] output = new char[len];
        int pointer = 0;
        int skipped = 0;
        for (char ch : arr) {
            if (ch == c) {
                skipped++;
                continue;
            }
            output[pointer++] = ch;
        }
        return new String(output, 0, len - skipped);
    }
}
