package Library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryResult extends JFrame {
    private DefaultTableModel dtm;
    private JTable jt;
    private List<Map<String, Object>> DataforTable = new ArrayList<>();
    String[] header = {"Title", "Author", "Publisher", "Publication Year"};

    public LibraryResult() {
        initDisplay();
    }

    public void addDatastoMap(String title, String author, String publisher, int pubyear, String imageUrl) {
        // 새로운 데이터 맵 생성
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Title", title);
        dataMap.put("Author", author);
        dataMap.put("Publisher", publisher);
        dataMap.put("Publication Year", pubyear);
        DataforTable.add(dataMap);
        // 데이터 추가 후 모델 업데이트
        updateTableModel();
    }

    public void updateTableModel() {
        Object[][] data = new Object[DataforTable.size()][header.length];
        for (int i = 0; i < DataforTable.size(); i++) {
            Map<String, Object> rowData = DataforTable.get(i);
            data[i][0] = rowData.get("Title");
            data[i][1] = rowData.get("Author");
            data[i][2] = rowData.get("Publisher");
            data[i][3] = rowData.get("Publication Year");
        }
        dtm.setDataVector(data, header);
    }

    public void initDisplay() {
        dtm = new DefaultTableModel(new Object[0][], header);
        jt = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(jt);
        Container con = this.getContentPane();
        con.add(jsp, BorderLayout.CENTER);
        this.setSize(500, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryResult lr = new LibraryResult();
            lr.addDatastoMap("Sample Book 1", "Sample Author 1", "Sample Publisher 1", 2023, "Image URL 1");
            lr.addDatastoMap("Sample Book 2", "Sample Author 2", "Sample Publisher 2", 2022, "Image URL 2");
        });
    }
}
