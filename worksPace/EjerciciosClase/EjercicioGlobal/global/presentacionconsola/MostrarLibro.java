package global.presentacionconsola;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MostrarLibro {

	private JFrame frame;

	public MostrarLibro(String desc, String tit, String ed, String aut, String fecha,String desc2) {
		initialize(desc,tit,ed,aut,fecha,desc2);
		this.frame.setVisible(true);
	}

	private void initialize(String desc, String tit, String ed, String aut, String fecha,String desc2) {
		frame = new JFrame();
		frame.setBounds(100, 100, 354, 484);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTest = new JLabel(tit);
		lblTest.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTest.setBounds(20, 289, 308, 50);
		frame.getContentPane().add(lblTest);
		
		JLabel lblEditorial = new JLabel(ed);
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEditorial.setBounds(10, 415, 93, 14);
		frame.getContentPane().add(lblEditorial);
		
		JLabel lblNewLabel_2 = new JLabel(aut);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(214, 399, 118, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(fecha);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(214, 415, 118, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblTestdescripcion = new JLabel(desc);
		lblTestdescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTestdescripcion.setBounds(31, 11, 253, 14);
		frame.getContentPane().add(lblTestdescripcion);
		
		JLabel lblYAcabarSuspendiendo = new JLabel(desc2);
		lblYAcabarSuspendiendo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYAcabarSuspendiendo.setBounds(20, 390, 152, 14);
		frame.getContentPane().add(lblYAcabarSuspendiendo);
		
		JLabel lblNewLabel = new JLabel("");
		String rutaImg = "/com/ipartek/formacion/uf2216/ejercicios/global/presentacionconsola/";
		int num = (int)Math.round(Math.random()*3);
		System.out.println(num);
		switch (num) {
		case 0:
			rutaImg+="raton.jpg";
			break;
		case 1:
			rutaImg+="oso.jpg";
			break;

		case 2:
			rutaImg+="coala.jpg";
			break;
		case 3:
			rutaImg+="pinguino.jpg";
			break;
		}
		System.out.println(rutaImg);
		lblNewLabel.setIcon(new ImageIcon(MostrarLibro.class.getResource(rutaImg)));
		lblNewLabel.setBounds(0, 0, 343, 450);
		frame.getContentPane().add(lblNewLabel);
	}
}
