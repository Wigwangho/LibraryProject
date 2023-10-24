package Library;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class LibraryResult extends JFrame implements ActionListener {
    private DefaultTableModel dtm;
    private JTable jt;
    private Object[][] DataforTable = {};
    String[] header = {"Title", "Author", "Publisher", "Publication Year"};
    JButton jbtn_select = new JButton("검색");
    JTextField jtxt_search = new JTextField();
    JPanel jp_north = new JPanel();
    String[] checknums = {"도서명","작가명","출판사명"};
    JList jlist = new JList(checknums);
    
    LibraryServer ls;
    
    

    public LibraryResult(LibraryServer server) {
    	ls = server;
       
        
    }



    public void updateTableModel(List<Map<String, Object>> list) {
        Vector<Vector<Object>> data = new Vector<>();
        for (Map<String, Object> item : list) {
            Vector<Object> row = new Vector<>();
            row.add(item.get("title"));
            row.add(item.get("author"));
            row.add(item.get("publisher"));
            row.add(item.get("pubyear"));
            data.add(row);
        }
        DefaultTableModel model = (DefaultTableModel) jt.getModel();

        // Clear the current data
        model.setRowCount(0);

        // Set the new data
        for (Vector<Object> row : data) {
            model.addRow(row);
        }
    }
    
    public void initDisplay() {
    	jp_north.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Create a panel for each component
        JPanel buttonPanel = new JPanel();
        jbtn_select.setPreferredSize(new Dimension(80, 30)); // Set button size
        buttonPanel.add(jbtn_select);
        gbc.gridx = 0;
        jp_north.add(buttonPanel, gbc);
        
        JPanel textFieldPanel = new JPanel();
        jtxt_search.setPreferredSize(new Dimension(150, 30)); // Set text field size
        textFieldPanel.add(jtxt_search);
        gbc.gridx = 1;
        jp_north.add(textFieldPanel, gbc);
        
        JPanel listPanel = new JPanel();
        listPanel.add(jlist);
        gbc.gridx = 2;
        jp_north.add(listPanel, gbc);
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one item to be selected
       
        dtm = new DefaultTableModel(new Object[0][], header);
        jt = new JTable(dtm);
        jbtn_select.addActionListener(this);
       
        this.add(jp_north, BorderLayout.NORTH);
        JScrollPane jsp = new JScrollPane(jt);
        Container con = this.getContentPane();
        con.add(jsp, BorderLayout.CENTER);
        this.setSize(500, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
    	LibraryServer ls = new LibraryServer();
       LibraryResult lr = new LibraryResult(ls);
       lr.initDisplay();
       
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtn_select) {
            String searchtxt = jtxt_search.getText();
            int searchnum = jlist.getSelectedIndex();
            System.out.println(searchnum + searchtxt);
            List<Map<String, Object>> searchResult = ls.searchInit(searchtxt, searchnum);
            updateTableModel(searchResult);
        }
		
	}
}
