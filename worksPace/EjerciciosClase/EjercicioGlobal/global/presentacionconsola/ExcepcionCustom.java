package global.presentacionconsola;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

class ExcepcionCustom extends Exception {
	private static final long serialVersionUID = 1L;
	String msg;
	JTextPane consolaObjetivo;

	public ExcepcionCustom(String msg, JTextPane consolaObjetivo) {
      this.msg = msg;
      this.consolaObjetivo = consolaObjetivo;
	}

	public String toString() {
	   String formattedDate = new SimpleDateFormat("hh:mm:ss a").format(new Date());
	   int num = consolaObjetivo.getText().length();
	   consolaObjetivo.setText(consolaObjetivo.getText() + "\n" + formattedDate + ": " + this.msg);
	   SimpleAttributeSet attrs = new SimpleAttributeSet();
	   StyleConstants.setForeground(attrs, Color.red);
	   StyledDocument sdoc = consolaObjetivo.getStyledDocument();
	   sdoc.setCharacterAttributes(num, consolaObjetivo.getText().length(), attrs, false);
	   return "CustomException[" + msg + "]";
	}
}
