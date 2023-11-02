package Library;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class LibraryAdvanced extends JFrame implements ActionListener {
	 private DefaultTableModel dtm;
	    private JTable jt;
	    private Object[][] DataforTable = {};
	    String[] header = {"Title", "Author", "Publisher", "Publication Year"};
	    JButton jbtn_select = new JButton("��ȸ");
	    JButton jbtn_delete = new JButton("����");
	    JButton jbtn_addframe = new JButton("�߰�");
	    JTextField jtxt_search = new JTextField();
	    JPanel jp_south = new JPanel();
	    String[] checknums = {"���������� �˻�","�۰������� �˻�","���ǻ������ �˻�"};
	    JList jlist = new JList(checknums);
	    List<Map<String, Object>> searchResult;
	    
	    LibraryManipulateServer LibraryManipulateServer = new LibraryManipulateServer();
		LibraryServer LibraryServer = new LibraryServer();			        
		LibraryAddJframe LibraryAddJframe = new LibraryAddJframe();
		
	


	    public void updateTableModel(List<Map<String, Object>> list) {
	    	DefaultTableModel model = (DefaultTableModel) jt.getModel();
	    	// Clear the current data
	        model.setRowCount(0);
	        Vector<Vector<Object>> data = new Vector<>();
	        for (Map<String, Object> item : list) {
	            Vector<Object> row = new Vector<>();
	            row.add(item.get("title"));
	            row.add(item.get("author"));
	            row.add(item.get("publisher"));
	            row.add(item.get("pubyear"));
	            data.add(row);
	        }
	        

	        // Set the new data
	        for (Vector<Object> row : data) {
	            model.addRow(row);
	        }
	        dtm = model;
	    }

	    public void initDisplay() {
	    	jp_south.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();

	        // �� ������Ʈ�� ���� �г� ����
	        JPanel textFieldPanel = new JPanel();
	        jtxt_search.setPreferredSize(new Dimension(200, 30)); // �ؽ�Ʈ �ʵ� ũ�� ����
	        textFieldPanel.add(jtxt_search);
	        gbc.gridx = 1;
	        jp_south.add(textFieldPanel, gbc);

	        JPanel buttonPanel = new JPanel();
	        jbtn_select.setPreferredSize(new Dimension(100, 30)); // ��ư ũ�� ����
	        buttonPanel.add(jbtn_select);
	        gbc.gridx = 0;
	        jp_south.add(buttonPanel, gbc);

	        JPanel listPanel = new JPanel();
	        listPanel.add(jlist);
	        gbc.gridx = 2;
	        jp_south.add(listPanel, gbc);
	        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // �ϳ��� �����۸� ���� �����ϵ��� ����

	        JPanel AddbuttonPanel = new JPanel();
	        jbtn_addframe.setPreferredSize(new Dimension(100, 30)); // ��ư ũ�� ����
	        AddbuttonPanel.add(jbtn_addframe);
	        gbc.gridx = 3;
	        jp_south.add(AddbuttonPanel, gbc);

	        JPanel deletebuttonPanel = new JPanel();
	        jbtn_delete.setPreferredSize(new Dimension(100, 30)); // ��ư ũ�� ����
	        deletebuttonPanel.add(jbtn_delete);
	        gbc.gridx = 4;
	        jp_south.add(deletebuttonPanel, gbc);

	        dtm = new DefaultTableModel(new Object[0][], header);
	        jt = new JTable(dtm);
	        jbtn_select.addActionListener(this);
	        jbtn_delete.addActionListener(this);
	        jbtn_addframe.addActionListener(this);

	        this.add(jp_south, BorderLayout.SOUTH);
	        JScrollPane jsp = new JScrollPane(jt);
	        Container con = this.getContentPane();
	        con.add(jsp, BorderLayout.CENTER);
	        this.setSize(800, 400); // �ʿ��Ѵ�� ������ ũ�⸦ ����
	        addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                System.exit(0);
	            }
	        });
	        this.setVisible(true);
	    }

	  

		@Override
			public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
		    	if(obj == jbtn_select) {
					String searchtxt = jtxt_search.getText();
			        int searchnum = jlist.getSelectedIndex();

			     
					searchResult = LibraryServer.searchInit(searchtxt, searchnum);
			        updateTableModel(searchResult);
		    } else if (obj == jbtn_delete) {
		        int selectedRow = jt.getSelectedRow();
		        if (selectedRow >= 0) {
		            String title = (String) dtm.getValueAt(selectedRow, 0);
		            System.out.println(title);
		            LibraryManipulateServer.book_Delete(title);
		            // ���⿡�� ���õ� ���� ������ �� �ʿ��� �۾��� ������ �� �ֽ��ϴ�
		           
		            if (LibraryManipulateServer.yes == 1) {
		                JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�.", "deleted", JOptionPane.INFORMATION_MESSAGE);
		                
		                List<Map<String, Object>> searchResult = LibraryServer.searchInit(jtxt_search.getText(), jlist.getSelectedIndex());
		                updateTableModel(searchResult);
		            } else {
		                JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�.", "can't deleted", JOptionPane.WARNING_MESSAGE);
		            }
		        } else {
		            System.out.println("���� �������� �ʾҽ��ϴ�.");
		        }
		    }
		    else if(obj == jbtn_addframe) {
		    	
		    	LibraryAddJframe.initDisplay();
		    }
			        
			    
}
}
