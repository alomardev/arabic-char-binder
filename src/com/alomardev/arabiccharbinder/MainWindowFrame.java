package com.alomardev.arabiccharbinder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowFrame extends JFrame implements DocumentListener, ActionListener {

    public interface MainWindowListener {
        void onTextUpdate(String input, boolean hinduDigits, boolean reverseChars);
    }

    private JCheckBox mHinduDigitsCb, mReverseCharsCb;
    private JTextArea mInputArea, mOutputArea;
    private JButton mCopyBtn;

    private MainWindowListener mCallback;

    public void setup() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /* Initialization components */

        mHinduDigitsCb = new JCheckBox("Convert Arabic digits");
        mReverseCharsCb = new JCheckBox("Reverse chars");
        mInputArea = new JTextArea(4, 20);
        mOutputArea = new JTextArea(4, 20);
        mCopyBtn = new JButton("Copy");

        JLabel titleLbl = new JLabel("Arabic Char Binder");
        JLabel linkLbl = new JLabel("https://github.com/alomardev/arabic-char-binder");
        JPanel optionsPanel = new JPanel();
        JScrollPane inputSP = new JScrollPane(mInputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane outputSP = new JScrollPane(mOutputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        /* Layout */
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        optionsPanel.add(mHinduDigitsCb);
        optionsPanel.add(mReverseCharsCb);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        UIUtils.gbc(c, 0, 0, 2, 1, 1, 0, -1, true, false, 2, 2, 2, 1);
        add(titleLbl, c);

        UIUtils.gbc(c, 0, 1, 2, 1, 1, 0, -1, true, false, 2, 1, 2, 0);
        add(optionsPanel, c);

        UIUtils.gbc(c, 0, 2, 2, 1, 1, 1, -1, true, true, 2, 2, 2, 0);
        add(inputSP, c);

        UIUtils.gbc(c, 0, 3, 2, 1, 1, 1, -1, true, true, 2, 2, 2, 0);
        add(outputSP, c);

        UIUtils.gbc(c, 0, 4, 1, 1, 1, 0, -1, true, false, 2, 2, 0, 2);
        add(linkLbl, c);

        UIUtils.gbc(c, 1, 4, 1, 1, 0, 0, -1, false, false, 1, 2, 2, 2);
        add(mCopyBtn, c);


        /* Setup */
        mInputArea.getDocument().addDocumentListener(this);
        mHinduDigitsCb.addActionListener(this);
        mReverseCharsCb.addActionListener(this);
        mCopyBtn.addActionListener(this);

        mInputArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        inputSP.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mInputArea.setWrapStyleWord(true);
        mInputArea.setLineWrap(true);
        mOutputArea.setEditable(false);
        mOutputArea.setWrapStyleWord(true);
        mOutputArea.setLineWrap(true);

        mReverseCharsCb.setSelected(true);
        mHinduDigitsCb.setSelected(true);

        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        Font customFont = UIUtils.FONT_SANS_SERIF.deriveFont(20f);
        titleLbl.setFont(customFont);
        mInputArea.setFont(customFont);
        mOutputArea.setFont(customFont);

        /* Final Configuration */

        pack();
        setMinimumSize(getSize());
    }

    public void setListener(MainWindowListener listener) {
        mCallback = listener;
    }

    public void setOutputText(String text) {
        mOutputArea.setText(text);
    }

    private void submitOnTextUpdate() {
        if (mCallback != null) {
            mCallback.onTextUpdate(mInputArea.getText(), mHinduDigitsCb.isSelected(), mReverseCharsCb.isSelected());
        }
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        submitOnTextUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        submitOnTextUpdate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        submitOnTextUpdate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mHinduDigitsCb || e.getSource() == mReverseCharsCb) {
            submitOnTextUpdate();
        } else if (e.getSource() == mCopyBtn) {
            StringSelection ss = new StringSelection(mOutputArea.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(ss, null);
        }
    }
}
