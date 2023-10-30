package Library;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmpManager extends JFrame implements ActionListener{
	JPanel jp_north = new JPanel();
	JButton jbtn_select = new JButton("조회");
	JButton jbtn_insert = new JButton("입력");
	JButton jbtn_update = new JButton("수정");
	JButton jbtn_delete = new JButton("삭제");
	String data [][]  = null;
	String header[] = {"사원번호","사원명","급여"};
	DefaultTableModel dtm = null;
	JTable jt = null;
	JScrollPane jsp = null;
	
	public EmpManager() {
		initData();	
		}
	public void initData() {
		data = new String[][]{
				{"7644", "나신입", "2500"},
				{"7499", "나초보", "4000"},
				{"7644", "나신입", "2500"},
				{"7644", "나신입", "2500"}
		};
				
		
		}
		
	public void refreshData() {
		//// >>json의 역할
		dtm = new DefaultTableModel(data, header);
		jt = new JTable(dtm);//셀로판지 포장
		jsp = new JScrollPane(jt);//속지
		jbtn_update.addActionListener(this);//자바스크립트에서는 하지마세요 이런거~
	}
	public void initDisplay() {
		
		
		this.add("Center",jsp);
		JPanel buttonPanel = new JPanel();
		
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.add(jbtn_select);
        buttonPanel.add(jbtn_insert);
        buttonPanel.add(jbtn_update);
        buttonPanel.add(jbtn_delete);
		this.add("South", buttonPanel);
		this.setSize(500, 300);
		this.setVisible(true);
	}
	
	public void test() {
		System.out.println("test 호출");
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpManager em = new EmpManager();
		em.refreshData();
		em.initDisplay();
		em.test();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(jbtn_update== obj) {
			
			for(int i = 0; i< dtm.getRowCount();i++)//로우(가로줄) 에 해당되는 곳 
			{
				if(jt.isRowSelected(i)) {
					dtm.setValueAt("5000", i, 2);
				}
				
			}
			
		}
		
	}

}
