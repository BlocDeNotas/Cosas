package global.presentacionconsola;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.joda.time.DateTime;

import global.accesodatos.Crudable;
import global.accesodatos.CrudableArchivo;
import global.accesodatos.EditorialesDAOColeccion;
import global.accesodatos.LibrosDAOArchivos;
import global.accesodatos.LibrosDAOColeccion;
import global.entidades.Editorial;
import global.entidades.Libro;

public class MantenimientoLibros {
	private static JTextPane tPaneConsola;
	private JCheckBox chkBorrado;
	private JFrame frmMantenimiento;
	private ListadoVisualLibros ventanaListado = new ListadoVisualLibros();
	private static Crudable<Libro> daoLibrosColeccion = LibrosDAOColeccion.getInstance();
	private static CrudableArchivo<Libro> daoLibrosArchivo = LibrosDAOArchivos.getInstance();
	private static Crudable<Editorial> daoEditoriales = EditorialesDAOColeccion.getInstance();

	// buttonAnulable: Contiene todos los botones que pueden llegar a ser
	// desactivados por datos erroneos ("setEnabled(false)");
	private ArrayList<JButton> buttonAnulable = new ArrayList<JButton>();
	private JComboBox<String> comboId = new JComboBox<String>(), comboTitulo = new JComboBox<String>();
	private JTextField txtISBN, txtAutor, txtGenero, txtEdicion, txtEditorial, txtId, txtTitulo, txtDescripcion,
			txtFechaImpresion;

	public void modificarLibro() throws ExcepcionCustom {
		if (daoLibrosColeccion.modificar(crearLibroTxt(), Long.parseLong(txtId.getText()))) {
			printOutput("Libro modificado");
		} else {
			throw new ExcepcionCustom("Libro no modificado, " + txtId.getText() + " no existe.",
					MantenimientoLibros.gettPaneConsola());
		}
		/*
		 * Se ha modificado un libro así que es posible que el titulo también haya
		 * cambiado, así que actualizo los index Del jComboBox de Titulos para que los
		 * titulos mostrados sean coherentes
		 */
		comboTitulo.removeAllItems();
		for (Libro libro : daoLibrosColeccion.obtenerTodos()) {
			comboTitulo.addItem(libro.getTitulo());
		}
	}

	public void borrarLibro() throws ExcepcionCustom {
		if (daoLibrosColeccion.borrar(Long.parseLong(txtId.getText()))) {
			printOutput("Libro Borrado");
		} else {
			throw new ExcepcionCustom("Libro no Borrado, el libro " + txtId.getText() + " no existe.",
					MantenimientoLibros.gettPaneConsola());
		}
		// Actualizar las comboBox.
		for (int i = 0; i < comboId.getItemCount(); i++) {
			if (txtId.getText().equals(comboId.getItemAt(i))) {
				comboId.removeItemAt(i);
				comboTitulo.removeItemAt(i);
			}
		}
		borrarTxt();
	}

	public void anadirLibro() {
		if (!checkearValidezCampos()) {
			daoLibrosColeccion.insertar(crearLibroTxt());
			comboId.addItem(txtId.getText());
			comboTitulo.addItem(txtTitulo.getText());
		}
	}

	public void listarLibros() {
		mostrarLista(daoLibrosColeccion.obtenerTodos());
	}

	public void buscarPorId(Long id) {
		mostrarLibroVisual(daoLibrosColeccion.obtenerPorId(id));
	}

	public void buscarPorTitulo(String titulo) {
		Iterable<Libro> libros = daoLibrosColeccion.obtenerTodos();
		Libro l = null;
		for (Libro libro : libros) {
			if (libro.getTitulo().equals(titulo)) {
				l = libro;
			} else
				printOutput(libro.getTitulo());
		}
		if (l == null) {
			new ExcepcionCustom("Libro con Titulo " + titulo + " no encontrado", MantenimientoLibros.gettPaneConsola());
		} else {
			mostrarLibroVisual(l);
		}
	}

	public void mostrarLista(Iterable<Libro> lista) {
		ArrayList<Libro> libros = (ArrayList<Libro>) daoLibrosColeccion.obtenerTodos();
		Object[][] objRows = new Object[libros.size()][8]; // Contiene cada linea de datos de la tabla (Cada libro);
		// Cargar cada linea de datos (cada libro);
		for (int i = 0; i < libros.size(); i++) {
			Libro l = libros.get(i);
			objRows[i][0] = l.isBorrado();
			objRows[i][1] = l.getGenero();
			objRows[i][2] = l.getTitulo();
			objRows[i][3] = l.getEditorial();
			objRows[i][4] = l.getAutor();
			objRows[i][5] = l.getFechaImpresion();
			objRows[i][6] = l.getISBN();
			objRows[i][7] = l.getId();
		}
		// Cargar la tabla con los datos;
		ventanaListado.table.setModel(new DefaultTableModel(objRows, ListadoVisualLibros.columnasTabla));
		ventanaListado.frame.setVisible(true);
	}

	public void printOutput(String s) {
		String formattedDate = new SimpleDateFormat("hh:mm:ss").format(new Date());
		gettPaneConsola().setText(gettPaneConsola().getText() + "\n" + formattedDate + ": " + s);
	}

	public void borrarTxt() {
		txtAutor.setText("");
		txtDescripcion.setText("");
		txtEdicion.setText("");
		txtEditorial.setText("");
		txtFechaImpresion.setText("");
		txtGenero.setText("");
		chkBorrado.setText("");
		txtId.setText("");
		txtTitulo.setText("");
		txtISBN.setText("");
		txtDescripcion2.setText("");
	}

	public void mostrarLibroVisual(Libro l) {
		if (l != null) {
			printOutput("Mostrando libro: " + l.getId());
			txtAutor.setText(l.getAutor());
			txtDescripcion.setText(l.getDescripcion());
			txtEdicion.setText(l.getEdicion());
			txtEditorial.setText(l.getEditorial().getNombre());
			txtFechaImpresion.setText(l.getFechaImpresion());
			txtGenero.setText(l.getGenero());
			txtISBN.setText("" + l.getISBN());
			chkBorrado.setSelected(l.isBorrado());
			txtId.setText("" + l.getId());
			txtTitulo.setText(l.getTitulo());
			txtDescripcion2.setText(l.getDescripcion2());
		}
	}

	public Libro crearLibroTxt() {
		return (new Libro(Long.parseLong(txtId.getText()), Long.parseLong(txtISBN.getText()), txtTitulo.getText(),
				txtAutor.getText(), txtDescripcion.getText(), txtGenero.getText(), txtEdicion.getText(),
				daoEditoriales.buscarPorNombre(txtEditorial.getText()), chkBorrado.isSelected(),
				txtFechaImpresion.getText()));
	}

	public void cargarCombobox() {
		ArrayList<Libro> libros = (ArrayList<Libro>) daoLibrosColeccion.obtenerTodos();
		for (Libro libro : libros) {
			comboId.addItem("" + libro.getId());
			comboTitulo.addItem(libro.getTitulo());
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MantenimientoLibros();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean checkearValidezCampos() {
		boolean saltarBoton = true; // Hace que si el dato erroneo es
		boolean datosErroneos = false;
		boolean errorFecha = false;
		try {
			DateTime.parse(txtFechaImpresion.getText());
			txtFechaImpresion.setBackground(Color.WHITE);
			if (txtFechaImpresion.getText().split("-").length < 3) {
				errorFecha = true;
			}
		} catch (Exception e) {
			errorFecha = true;
		}
		if (errorFecha) {
			txtFechaImpresion.setBackground(Color.RED);
			datosErroneos = true;
			new ExcepcionCustom("Fecha Erronea", MantenimientoLibros.gettPaneConsola());
		}
		if (!(txtISBN.getText().matches("[0-9]+"))) {
			txtISBN.setBackground(Color.RED);
			datosErroneos = true;
			new ExcepcionCustom("ISBN Erroneo", MantenimientoLibros.gettPaneConsola());
		} else {
			txtISBN.setBackground(Color.WHITE);
		}
		if (!(txtId.getText().matches("[0-9]+"))) {
			txtId.setBackground(Color.RED);
			datosErroneos = true;
			saltarBoton = false;
			new ExcepcionCustom("Id Erronea", MantenimientoLibros.gettPaneConsola());
		} else {
			txtId.setBackground(Color.WHITE);
		}

		for (int i = 0; i <= buttonAnulable.size() - 1; i++) {
			buttonAnulable.get(i).setEnabled(!datosErroneos);
		}
		if (saltarBoton) {
			buttonAnulable.get(0).setEnabled(true);
		}
		return datosErroneos;
	}

	DocumentListener checker = new DocumentListener() {
		@Override
		public void removeUpdate(DocumentEvent e) {
			checkearValidezCampos();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			checkearValidezCampos();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			checkearValidezCampos();
		}
	};
	private JTextField txtDescripcion2;

	public void insertarLibrosEjemplo() {
		/*
		 * long id, long iSBN, String titulo, String autor, String descripcion, String
		 * genero, String edicion, String editorial, boolean isBorrado, String
		 * fechaImpresion
		 */
		daoLibrosColeccion.insertar(new Libro(0, 12, "Titulo0", "autor0", "descripcion0", "genero0", "edicion0",
				daoEditoriales.buscarPorNombre("Ye boi"), "desc0", false, "1990-12-28"));
		daoLibrosColeccion.insertar(new Libro(1, 18, "ESTUDIAR EL DIA ANTERIOR", "God", "Intentar aprobar", "genero1",
				"edicion1", daoEditoriales.buscarPorNombre("O´RLY"), "Y acabar suspendiendo", true, "2016-05-11"));
		daoLibrosColeccion.insertar(new Libro(2, 900, "Titulo2", "autor2", "descripcion2", "genero2", "edicion2",
				daoEditoriales.buscarPorNombre("Ye boi"), "desc2", false, "1500-01-01"));
		cargarCombobox();
	}

	// Inicia los graficos y los listener de los botones.
	private void initialize() {
		frmMantenimiento = new JFrame();
		frmMantenimiento
				.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Covacs\\Desktop\\book-icon.png"));
		frmMantenimiento.setTitle("Mantenimiento");
		frmMantenimiento.setBounds(100, 100, 400, 527);
		frmMantenimiento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMantenimiento.getContentPane().setLayout(null);
		frmMantenimiento.setVisible(true);

		txtISBN = new JTextField();
		txtISBN.setText("");
		txtISBN.setBounds(105, 35, 86, 20);
		frmMantenimiento.getContentPane().add(txtISBN);
		txtISBN.setColumns(10);

		JLabel lblDatosLibro = new JLabel("Datos Libro");
		lblDatosLibro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosLibro.setBounds(10, 7, 115, 20);
		frmMantenimiento.getContentPane().add(lblDatosLibro);

		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(10, 38, 46, 14);
		frmMantenimiento.getContentPane().add(lblISBN);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(204, 38, 46, 14);
		frmMantenimiento.getContentPane().add(lblAutor);

		txtAutor = new JTextField();
		txtAutor.setBounds(279, 35, 86, 20);
		frmMantenimiento.getContentPane().add(txtAutor);
		txtAutor.setColumns(10);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(204, 105, 46, 14);
		frmMantenimiento.getContentPane().add(lblGenero);

		txtGenero = new JTextField();
		txtGenero.setBounds(279, 102, 86, 20);
		frmMantenimiento.getContentPane().add(txtGenero);
		txtGenero.setColumns(10);

		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(10, 74, 46, 14);
		frmMantenimiento.getContentPane().add(lblEditorial);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(204, 136, 79, 14);
		frmMantenimiento.getContentPane().add(lblDescripcion);

		JLabel lblEdicion = new JLabel("Edicion");
		lblEdicion.setBounds(204, 74, 46, 14);
		frmMantenimiento.getContentPane().add(lblEdicion);

		txtEdicion = new JTextField();
		txtEdicion.setBounds(279, 66, 86, 20);
		frmMantenimiento.getContentPane().add(txtEdicion);
		txtEdicion.setColumns(10);

		txtEditorial = new JTextField();
		txtEditorial.setBounds(105, 66, 86, 20);
		frmMantenimiento.getContentPane().add(txtEditorial);
		txtEditorial.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(279, 133, 86, 20);
		frmMantenimiento.getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);

		JLabel lblFechaimpresion = new JLabel("FechaImpresion");
		lblFechaimpresion.setBounds(10, 136, 115, 14);
		frmMantenimiento.getContentPane().add(lblFechaimpresion);

		txtFechaImpresion = new JTextField();
		txtFechaImpresion.setBounds(105, 133, 86, 20);
		frmMantenimiento.getContentPane().add(txtFechaImpresion);
		txtFechaImpresion.setColumns(10);

		JButton btnAnadir = new JButton("Anadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anadirLibro();
			}
		});
		btnAnadir.setBounds(44, 186, 163, 23);
		frmMantenimiento.getContentPane().add(btnAnadir);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtId.getText().matches("[0-9]+"))
						borrarLibro();
					else
						printOutput("Id no válida, libro no borrado");
				} catch (ExcepcionCustom e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBorrar.setBounds(217, 186, 148, 23);
		frmMantenimiento.getContentPane().add(btnBorrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!checkearValidezCampos()) {
						modificarLibro();
					}
				} catch (ExcepcionCustom e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(217, 220, 148, 23);
		frmMantenimiento.getContentPane().add(btnModificar);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarLibros();
			}
		});
		btnListado.setBounds(10, 285, 197, 23);
		frmMantenimiento.getContentPane().add(btnListado);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (daoLibrosArchivo.cargar()) {
					printOutput("Json cargado en libros");
					comboTitulo.removeAllItems();
					comboId.removeAllItems();
					cargarCombobox();
				} else
					printOutput("Error al cargar el Json");
			}
		});
		btnCargar.setBackground(new Color(59, 89, 182));
		btnCargar.setForeground(Color.BLACK);
		btnCargar.setBounds(217, 254, 148, 34);
		frmMantenimiento.getContentPane().add(btnCargar);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 161, 46, 14);
		frmMantenimiento.getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setBounds(105, 161, 86, 20);
		frmMantenimiento.getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(204, 161, 46, 14);
		frmMantenimiento.getContentPane().add(lblTitulo);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(279, 158, 86, 20);
		frmMantenimiento.getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		comboId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					buscarPorId(Long.parseLong(e.getItem().toString()));
				}
			}
		});

		comboId.setBounds(105, 221, 102, 20);
		frmMantenimiento.getContentPane().add(comboId);

		JLabel lblBuscarporid = new JLabel("BuscarPorId");
		lblBuscarporid.setBounds(10, 224, 85, 14);
		frmMantenimiento.getContentPane().add(lblBuscarporid);
		comboTitulo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					buscarPorTitulo(arg0.getItem().toString());
				}
			}
		});
		comboTitulo.setBounds(105, 254, 102, 20);
		frmMantenimiento.getContentPane().add(comboTitulo);

		JLabel lblBuscarportitulo = new JLabel("BuscarPorTitulo");
		lblBuscarportitulo.setBounds(10, 254, 94, 14);
		frmMantenimiento.getContentPane().add(lblBuscarportitulo);

		chkBorrado = new JCheckBox("IsBorrado");
		chkBorrado.setBounds(10, 96, 94, 23);
		frmMantenimiento.getContentPane().add(chkBorrado);

		txtISBN.getDocument().addDocumentListener(checker);
		txtId.getDocument().addDocumentListener(checker);
		txtFechaImpresion.getDocument().addDocumentListener(checker);

		buttonAnulable.add(btnBorrar);
		buttonAnulable.add(btnAnadir);
		buttonAnulable.add(btnModificar);

		JLabel lblOutput = new JLabel("OutPut");
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOutput.setBounds(10, 318, 85, 14);
		frmMantenimiento.getContentPane().add(lblOutput);

		settPaneConsola(new JTextPane());
		gettPaneConsola().setForeground(Color.WHITE);
		gettPaneConsola().setBackground(Color.BLACK);
		gettPaneConsola().setBounds(10, 339, 355, 50);
		frmMantenimiento.getContentPane().add(gettPaneConsola());

		JScrollPane scrollBar = new JScrollPane(gettPaneConsola());
		scrollBar.setBounds(10, 337, 355, 140);
		frmMantenimiento.getContentPane().add(scrollBar);

		gettPaneConsola().setEditable(false); // set textArea non-editable
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JLabel lblNewLabel = new JLabel("AA-MM-DD");
		lblNewLabel.setBounds(105, 116, 86, 14);
		frmMantenimiento.getContentPane().add(lblNewLabel);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (daoLibrosArchivo.guardar())
					printOutput("Libros guardados en Json");
				else
					printOutput("Error al cargar los libros");
			}
		});
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setBackground(Color.GREEN);
		btnGuardar.setBounds(217, 299, 148, 34);
		frmMantenimiento.getContentPane().add(btnGuardar);

		JButton btnVerLibro = new JButton("Ver Libro");
		btnVerLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MostrarLibro(txtDescripcion.getText(), txtTitulo.getText(), txtEditorial.getText(),
						txtAutor.getText(), txtFechaImpresion.getText(), txtDescripcion2.getText());
			}
		});
		btnVerLibro.setBackground(Color.YELLOW);
		btnVerLibro.setBounds(276, 8, 89, 23);
		frmMantenimiento.getContentPane().add(btnVerLibro);

		JLabel lblDesc2 = new JLabel("Descripcion2");
		lblDesc2.setBounds(117, 12, 79, 14);
		frmMantenimiento.getContentPane().add(lblDesc2);

		txtDescripcion2 = new JTextField();
		txtDescripcion2.setColumns(10);
		txtDescripcion2.setBounds(178, 9, 86, 20);
		frmMantenimiento.getContentPane().add(txtDescripcion2);

		for (JButton jb : buttonAnulable) {
			jb.setEnabled(false);
		}
	}

	public MantenimientoLibros() {
		initialize();
		insertarLibrosEjemplo();
	}

	public static JTextPane gettPaneConsola() {
		return tPaneConsola;
	}

	public void settPaneConsola(JTextPane tPaneConsola) {
		MantenimientoLibros.tPaneConsola = tPaneConsola;
	}
}
