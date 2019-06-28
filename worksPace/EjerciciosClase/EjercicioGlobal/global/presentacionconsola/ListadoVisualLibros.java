package global.presentacionconsola;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class ListadoVisualLibros {

	JFrame frame;
	JTable table;
	JTableHeader header;
	public static final String[] columnasTabla = new String[] { "isBorrado", "Genero", "Titulo", "Editorial", "Autor",
			"Fecha", "ISBN", "Id" };

	/**
	 * Create the application.
	 */
	public ListadoVisualLibros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);

		table = new JTable();

		table.setBounds(10, 42, 414, 208);
		header = table.getTableHeader();
		header.setBounds(10, 0, 414, 42);
		frame.getContentPane().add(table);
		frame.getContentPane().add(header);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
