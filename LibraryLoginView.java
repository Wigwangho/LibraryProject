package Library;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class LibraryLoginView extends JFrame implements ActionListener{
	 private DefaultTableModel dtm;
	    private JTable jt;
	    JButton jbtn_login = new JButton("·Î±×ÀÎ");
	    JTextField jtxt_ID = new JTextField(20); 
	    JTextField jtxt_pw = new JTextField(20); 
	    JPanel jp_south = new JPanel();
	    JPanel jp_center = new JPanel();
	    public void initDisplay() {
	    	 jp_south.setLayout(new GridBagLayout());
	         jp_center.setLayout(new GridBagLayout());
	    	GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        jp_center.add(new JLabel("ID"), gbc);

	        gbc.gridy = 1;
	        jp_center.add(new JLabel("Password"), gbc);

	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        jp_center.add(jtxt_ID, gbc);

	        gbc.gridy = 1;
	        jp_center.add(jtxt_pw, gbc);

	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        gbc.gridwidth = 2;
	        jp_south.add(jbtn_login, gbc);

	        jbtn_login.addActionListener(this);
	        this.add(jp_south, BorderLayout.SOUTH);
	        this.add(jp_center, BorderLayout.CENTER);
	        this.setVisible(true);
	        this.setSize(400, 200); // Adjusted the height
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//LibraryLoginView lv = new LibraryLoginView();
		//lv.initDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == jbtn_login) {
			String ID = jtxt_ID.getText();
			String pw = jtxt_pw.getText();
			LibraryLoginDAO ld = new LibraryLoginDAO(ID, pw);
			dispose();
		}
	}

}
