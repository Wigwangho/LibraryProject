package Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LibraryAdd extends JFrame implements ActionListener{
	JButton jbtn_Add = new JButton("추가하기");
	JTextField jtxt_isbn = new JTextField(20); 
    JTextField jtxt_title = new JTextField(100); 
    JTextField jtxt_author = new JTextField(100); 
    JTextField jtxt_pubyear = new JTextField(10); 
    JTextField jtxt_publisher = new JTextField(20); 
    JTextField jtxt_imgurl = new JTextField(300); 
    

    JLabel isbn = new JLabel("ISBN");
	JLabel title = new JLabel("Title");
	JLabel author = new JLabel("Author");
	JLabel pubyear = new JLabel("Published Year");
	JLabel publisher = new JLabel("Publisher");
	JLabel imgurl = new JLabel("Image Url");
	LibraryManipulateServer lms = new LibraryManipulateServer();

	public void initDisplay() {
		setLayout(null);

	    // Set the location and size of each component
		isbn.setBounds(30, 10, 80, 25);
		jtxt_isbn.setBounds(120, 10, 200, 25);
		title.setBounds(30, 45, 80, 25); // 이전의 30을 45로 변경
		jtxt_title.setBounds(120, 45, 200, 25); // 이전의 30을 45로 변경
		author.setBounds(30, 80, 80, 25); // 이전의 65을 80로 변경
		jtxt_author.setBounds(120, 80, 200, 25); // 이전의 65을 80로 변경
		pubyear.setBounds(30, 115, 80, 25); // 이전의 100을 115로 변경
		jtxt_pubyear.setBounds(120, 115, 50, 25); // 이전의 100을 115로 변경
		publisher.setBounds(30, 150, 80, 25); // 이전의 135을 150로 변경
		jtxt_publisher.setBounds(120, 150, 150, 25); // 이전의 135을 150로 변경
		imgurl.setBounds(30, 185, 80, 25); // 이전의 170을 185로 변경
		jtxt_imgurl.setBounds(120, 185, 250, 25); // 이전의 170을 185로 변경
		jbtn_Add.setBounds(150, 220, 100, 30); // 이전의 210을 220로 변경
	    jbtn_Add.addActionListener(this);

	    // Add components to the frame
	    add(isbn);
	    add(jtxt_isbn);
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
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			
			String title = jtxt_title.getText();
			String author = jtxt_author.getText();
			String pubyear = jtxt_pubyear.getText();
			String publisher = jtxt_publisher.getText();
			String imgurl = jtxt_imgurl.getText();
			String isbn = jtxt_isbn.getText();
			lms.LibraryAdd(isbn, title, author, pubyear, publisher, imgurl);

		}
	}

}
