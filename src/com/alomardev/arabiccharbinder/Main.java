package com.alomardev.arabiccharbinder;

import javax.swing.*;

public class Main implements MainWindowFrame.MainWindowListener {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            System.err.println(ex.getMessage());
        }

        new Main();
    }

    private MainWindowFrame mMainFrame;

    public Main() {
        mMainFrame = new MainWindowFrame();
        mMainFrame.setup();
        mMainFrame.setVisible(true);
        mMainFrame.setListener(this);
    }

    @Override
    public void onTextUpdate(String input, boolean hinduDigits, boolean reverseChars) {
        int flags = (hinduDigits ? 0 : ArabicCharBinder.FLAG_IGNORE_ARABIC_DIGITS) |
                (reverseChars ? ArabicCharBinder.FLAG_REVERSE_CHARS : 0);
        mMainFrame.setOutputText(ArabicCharBinder.bind(input, flags));
    }
}
