package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LibraryAdd extends JFrame implements ActionListener{
	JButton jbtn_Add = new JButton("추가하기");
    JTextField jtxt_title = new JTextField(100); 
    JTextField jtxt_author = new JTextField(100); 
    JTextField jtxt_pubyear = new JTextField(10); 
    JTextField jtxt_publisher = new JTextField(20); 
    JTextField jtxt_imgurl = new JTextField(300); 

	JLabel title = new JLabel("Title");
	JLabel author = new JLabel("Author");
	JLabel pubyear = new JLabel("Published Year");
	JLabel publisher = new JLabel("Publisher");
	JLabel imgurl = new JLabel("Image Url");
	LibraryManipulateServer lms;

	public void initDisplay() {
		setLayout(null);

	    // Set the location and size of each component
	    title.setBounds(30, 30, 80, 25);
	    jtxt_title.setBounds(120, 30, 200, 25);
	    author.setBounds(30, 65, 80, 25);
	    jtxt_author.setBounds(120, 65, 200, 25);
	    pubyear.setBounds(30, 100, 80, 25);
	    jtxt_pubyear.setBounds(120, 100, 50, 25);
	    publisher.setBounds(30, 135, 80, 25);
	    jtxt_publisher.setBounds(120, 135, 150, 25);
	    imgurl.setBounds(30, 170, 80, 25);
	    jtxt_imgurl.setBounds(120, 170, 250, 25);
	    jbtn_Add.setBounds(150, 210, 100, 30);
	    jbtn_Add.addActionListener(this);

	    // Add components to the frame
	    add(title);
	    add(jtxt_title);
	    add(author);
	    add(jtxt_author);
	    add(pubyear);
	    add(jtxt_pubyear);
	    add(publisher);
	    add(jtxt_publisher);
	    add(imgurl);
	    add(jtxt_imgurl);
	    add(jbtn_Add);

	    // Set the frame properties
	    setTitle("Library Add");
	    setSize(400, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setVisible(true);
	}
	public static void main(String[] args) {
		LibraryAdd la = new LibraryAdd();
		la.initDisplay();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == jbtn_Add) {
			lms = new LibraryManipulateServer();
			String title = jtxt_title.getText();
			String author = jtxt_author.getText();
			String pubyear = jtxt_pubyear.getText();
			String publisher = jtxt_publisher.getText();
			String imgurl = jtxt_imgurl.getText();
			lms.LibraryAdd(title, author, pubyear, publisher, imgurl);
		}
	}

}
