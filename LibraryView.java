package Library;

import javax.swing.JFrame;

//검색하는 곳

public class LibraryView extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibraryServer ls = new LibraryServer();
	       LibraryResult lr = new LibraryResult(ls);
	       lr.initDisplay();
	}

}
