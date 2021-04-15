package v2;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.Locale;
// Gioi han ten nguoi choi
public class JTextFieldLimit extends PlainDocument {
    private int limit;

    public JTextFieldLimit(int limit) {
        this.limit = limit;
    }


    public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
        if (str == null)
            return;
        else if((getLength() + str.length())<=limit){
            str = str.toUpperCase(Locale.ROOT);
            super.insertString(offset,str,set);
        }
    }
}



