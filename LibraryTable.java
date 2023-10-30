package Library;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class LibraryTable extends JFrame implements ActionListener {
	 private DefaultTableModel dtm;
	    private JTable jt;
	    private Object[][] DataforTable = {};
	    String[] header = {"Title", "Author", "Publisher", "Publication Year"};
	    JButton jbtn_select = new JButton("조회");
	    JButton jbtn_delete = new JButton("삭제");
	    JTextField jtxt_search = new JTextField();
	    JPanel jp_south = new JPanel();
	    String[] checknums = {"도서명으로 검색","작가명으로 검색","출판사명으로 검색"};
	    JList jlist = new JList(checknums);
	    List<Map<String, Object>> searchResult;
	    
	    LibraryManipulateServer lms;
		LibraryServer ls = new LibraryServer();			        
		LibraryResult lr = new LibraryResult(ls);
		
	    

	    public LibraryTable(LibraryManipulateServer server) {
			// TODO Auto-generated constructor stub
	    	this.lms = server;
		}

		public void setLibraryTable(LibraryResult libraryResult) {
	        this.lr = libraryResult;
	    }

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
	        
	        // Create a panel for each component
	        JPanel textFieldPanel = new JPanel();
	        jtxt_search.setPreferredSize(new Dimension(150, 30)); // Set text field size
	        textFieldPanel.add(jtxt_search);
	        gbc.gridx = 1;
	        jp_south.add(textFieldPanel, gbc);
	        
	        JPanel buttonPanel = new JPanel();
	        jbtn_select.setPreferredSize(new Dimension(80, 30)); // Set button size
	        buttonPanel.add(jbtn_select);
	        gbc.gridx = 0;
	        jp_south.add(buttonPanel, gbc);
	      
	        
	        JPanel listPanel = new JPanel();
	        listPanel.add(jlist);
	        gbc.gridx = 2;
	        jp_south.add(listPanel, gbc);
	        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one item to be selected
	       
	        JPanel loginbuttonPanel = new JPanel();
	        jbtn_delete.setPreferredSize(new Dimension(80, 30)); // Set button size
	        buttonPanel.add(jbtn_delete);
	        gbc.gridx = 3;
	        jp_south.add(loginbuttonPanel, gbc);
	        
	        dtm = new DefaultTableModel(new Object[0][], header);
	        jt = new JTable(dtm);
	        jbtn_select.addActionListener(this);
	        jbtn_delete.addActionListener(this);
	       
	        this.add(jp_south, BorderLayout.SOUTH);
	        JScrollPane jsp = new JScrollPane(jt);
	        Container con = this.getContentPane();
	        con.add(jsp, BorderLayout.CENTER);
	        this.setSize(500, 300);
	        this.setVisible(true);
	    }

	  

		@Override
			public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
		    	if(obj == jbtn_select) {
					String searchtxt = jtxt_search.getText();
			        int searchnum = jlist.getSelectedIndex();

			     
					searchResult = ls.searchInit(searchtxt, searchnum);
			        updateTableModel(searchResult);
		    } else if (obj == jbtn_delete) {
		        int selectedRow = jt.getSelectedRow();
		        if (selectedRow >= 0) {
		            String title = (String) dtm.getValueAt(selectedRow, 0);
		            System.out.println(title);

		            // 여기에서 선택된 행을 삭제한 후 필요한 작업을 수행할 수 있습니다
		            LibraryLoginDAO lld = new LibraryLoginDAO(title);
		            if (lld.yes == 1) {
		                JOptionPane.showMessageDialog(null, "삭제에 성공했습니다.", "deleted", JOptionPane.INFORMATION_MESSAGE);
		                
		                List<Map<String, Object>> searchResult = lms.searchInit();
		                updateTableModel(searchResult);
		            } else {
		                JOptionPane.showMessageDialog(null, "삭제에 실패했습니다.", "can't deleted", JOptionPane.WARNING_MESSAGE);
		            }
		        } else {
		            System.out.println("행을 선택하지 않았습니다.");
		        }
		    }
			        
			    
}
}
