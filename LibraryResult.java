
		public class LibraryResult extends JFrame {
	    private JLabel titleLabel;
	    private JLabel authorLabel;
	    private JLabel publisherLabel;
	    private JLabel pubYearLabel;
	    private JLabel imageLabel;

	    public LibraryResult(String title, String author, String publisher, String pubYear, String imageUrl) {
	        setTitle("Book Information");
	        setSize(400, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        titleLabel = new JLabel("Title: " + title);
	        authorLabel = new JLabel("Author: " + author);
	        publisherLabel = new JLabel("Publisher: " + publisher);
	        pubYearLabel = new JLabel("Publication Year: " + pubYear);

	        // Create an empty label to display the image
	        imageLabel = new JLabel();

	        // Load and display the image from the URL
	        try {
	            URL url = new URL(imageUrl);
	            Image image = ImageIO.read(url);
	            ImageIcon icon = new ImageIcon(image);
	            imageLabel.setIcon(icon);
	        } catch (MalformedURLException e) {
	            // 이미지 URL이 잘못된 경우에 대한 처리
	            imageLabel.setText("Image not found"); // 텍스트로 대체 이미지 표시
	        } catch (IOException e) {
	            // 이미지를 로드할 수 없는 경우에 대한 처리
	            imageLabel.setText("Image not found"); // 텍스트로 대체 이미지 표시
	        }

	        setLayout(new GridBagLayout());
	        GridBagConstraints constraints = new GridBagConstraints();

	        constraints.gridx = 0;
	        constraints.gridy = 0;
	        constraints.gridwidth = 2; // 하나의 셀을 두 개의 컬럼에 걸쳐서 확장
	        constraints.fill = GridBagConstraints.HORIZONTAL; // 가로로 확장
	        add(imageLabel, constraints);
	       
	        constraints.gridx = 0;
	        constraints.gridy = 1;
	        add(titleLabel, constraints);

	        constraints.gridx = 1;
	        constraints.gridy = 2;
	        add(pubYearLabel, constraints);

	        constraints.gridx = 0;
	        constraints.gridy = 3;
	        constraints.gridwidth = 2; // 하나의 셀을 두 개의 컬럼에 걸쳐서 확장
	        constraints.fill = GridBagConstraints.HORIZONTAL; // 가로로 확장
	        add(authorLabel, constraints);

	        constraints.gridx = 0;
	        constraints.gridy = 4;
	        constraints.gridwidth = 2; // 하나의 셀을 두 개의 컬럼에 걸쳐서 확장
	        constraints.fill = GridBagConstraints.HORIZONTAL; // 가로로 확장
	        add(publisherLabel, constraints);

	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        // Example book information
	        String title = "Sample Book";
	        String author = "Sample Author";
	        String publisher = "Sample Publisher";
	        String pubYear = "2023";
	        String imageUrl = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxIPDxAQEBAQFRAREA0RFRAWDxAPDw8QGBIXFhYSFRUYHSggGBolHRUVITciJSkrLy4uGR9AOjQtOCgtLisBCgoKDg0OGhAQGi8mHiYtLi0tMysuLS8vLSswLSswLS0tKzAtKy8tLS0tLzAtLSstLS0tLS0tLSstLy0tLS0tK//AABEIAMEBBQMBIgACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAAAwQCBQYBBwj/xAA7EAACAQIEAwYEBAQGAwEAAAAAAQIDEQQFEiExQVEGE2FxkaEiMoGxBxRSwTNC0fAjYnKCkuEkovEV/8QAGgEBAAMBAQEAAAAAAAAAAAAAAAEDBAIFBv/EAC4RAAIBAgUCBAYDAQEAAAAAAAABAgMRBBIhMUFRcZGh0fAFEyIyYbGBwfHhM//aAAwDAQACEQMRAD8A+4gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1uad/8AA6O6vLUrxT5W48uJfheyvxtv5k2K41LzcbPS2vDv0MwAQWAAAAAAAAAAAAAAAAAAAAAAAAAGnxGcwVR04Sg5R2km7O/RLn9CtSzCvGr8Xdyotzb2cKtPZtRjylyW9uJy5e1/Z2oPnTS+ul+3vXi7OhBUwWJVampqM4puStOLhNNOzun5FslO6uctNOzAAJIAAAAAAAAAAAAAAAAMHNJpNq74K+78jMAAAAAAAAAAAAAAAAAAAAAAFDOMcsPQqVXxitl1m9or1sXyjnGAjiaM6MnZSStLnGSd0/YiV7PLud08udZ9r69j5TlmGqV8enUqXoz1OUbfG52cm7+Nn6nE5j+JOMlUlUp1pUoXTpUIwpaKcP5YzUotydrXu+N7WO4rUquHqaqb1aJtxmlxs+NujX3NLj8py2tWdeeAq99KTlKlDEd3hpze7bXzRTfJGHC16Vnqr+h7GPw9WtKMqX1RttfZv8N6LbtY7vIu06nQwlao+7nicPTrOKvoUuD8ot7q51eVZqq05U7puMIT1Kzi021y57e58bxcKlSrHEzno0RUVRpq1KNKKtCjBdF73fA+q9hcqeHwqlP+LXfez8E/lj6GinWzystjHicGqNJSk/q2to09Lu3bbnY6UAF5gAAAABG60VxlH/kgCQESrwf88f8AkjJzXVeqAMwYqa6r1NNjO0uHp3XeObXFQWpL/dsvc4nUjBXk7dyylRqVXaEW+xuwc9T7W4duz7xeLULe0mzc4XFwqx1U5qS8OXmuKOadanU+ySZ1Vw1Wl/6RaIcVl6qVIVHKS0NS0rg2ndF4Avcm0k+CiwBHrPVM5JMwY60epgHoAAAAAAAAAAAAAABFiKeuEo3tqTV7XJQAcnjuzzW8Vt1jv/68TQ4jI3NqOhNtqKa5Nu3mj6URulFyUtK1Lnbf1MNX4fRqO6WV9Y6eW3kaqeMq09n777nGw7AwVWnJ15SpRlGTpuO8mt7Xvwv4HbA1GfVasYx7mM2976E278r23txNcYRh9qIz1cTOMZy/GuhtzxuxVwc5qjCVXaehOXDZ/TmVauIc34cl0OzO1ZtF54iPiyKpXk/lsvdlaDJoggq1pu/xX+vAjTvsbCdO6s0UIw0t+hw46likrFiFNLz6klkcTnH4lYLC1e5Xe16ilokqSp6IS4NOc5Ri2uGzZLje19HE4HETw8pKonCjKlJKNWlKpzdm01p1WlFtOz32ZM5KEXLhainCVWpGC3bS8SLNM0qY6t+Xw38K7TadlUs95yf6F/fI3WXdksPBJ1b1J87tqmvBRT3+tyr2LwihQ7y3xVG1fpBbW9bnSRmzFhqKmvnVdZPXsuEvf/fTxuKdJvD4d5YR003k+W3309pKpiOyuDqRs8PCP+aF6Ul43g0cxmGX18rmq9Ocp4bUk2/4tG72U7bShyv5X6nd4afL6oyxFCNSEqc4qUJxlGUXupRas0/oX1cNCa0Vnw1ujJQx1Wk7SeaPKeqfo+383WhBlWYRxNKNSPPZrpLoXJOyZxPYWUqNfE4WTbVOdSKb4yUJ2jL6xdzs8RK0fNonD1HOneW+z7rQ5xlCNGs4x+3Rrs9UaTN8TJTjGMmrRvs2t2VP/wBKtFO0m34pMizLFLvXd7t6V5La/lsQU66lJxXLTvyZ6S+XlVNtXen5u1fxPClWtUunzt2/w3uS4qc9Snd2V7tWfl9y+qjTdutvAoZLHTTlLq/ZL/6Wk/t7syKmqSUE27aXe77npKbqfW1a/C4LtOepXMyOirRRICQAAAAAAAAAAAAU80zGnhqNStUfw04Sm0rOTSV9l9Dn8dUnhK1ec8RVqqsounRdqdLDRV1vJb2d3urX0pcdyhPPKlJa9acpP5FTi4yu27JcefmUVMVSpzUG7t9PenfbqbKOCqVI5lztvr5HW5RmEcVQpYiCmoVYqSjJJTS6NJtejLxp+zWNlXoappKanUUkk1FO+qyu23a9r+DNwXRkpLNHZmSUXFuL4AAJIKOZT2jHq7vyX9+xRRbzD51/p/dlUAkiz45+J/a6r+fnhIyksPh1TUqcZSgq1WVNTbm4u8opTS08LpvfY+wo+O/i12Ur/m5Y2hSnUpVo0+80Rc5UqsYqF5RW+lxjHfhdO9trgcx2W7SYjB4nvaE5KK1SeG1P8vVit3Bweyv+pK6PuvaXHN5biMRh27vBV61NrjvRc4NePBnwTs72fxmJrRhh8PVbfwupKnKFGmns5Tm1bbjbi+SZ+iKWEjRoUsOt4U6VOkr/AM0YQUd14pAH5airJJcDddmaku+2cmtLc0rtaFwlJdE2vK53WY/hFqqyeGxUYUm21TqU5SlTX6VJP4kuV0n1b4nV9kfw+w+Ap1dcnWrV6bpTqOKhFUnxhTjd6U2k2223ZdLHFSGeDj1Vi7D1flVY1OjT8H6G97KVFLCU7fyucH66vs0bixwuTYueW4iWHr3dN/LPlOF/hqLx5Nf9Hb0a0ZxUoSUovg07oowlTNBQf3R0a7c9rGr4jQcKrqLWE3mT6318bklN2kvNf0L5rovdGl7T9oowi6FCWqtP4XKO6pJ3Ts77z6LkXVasaUXKWxlw9CdeooU1r+vy/wAFTss+9zHF1V8jlXafJrWoxf1SudbjH8vTc1fZPKfytBalapOzkv09I/T9zfFeFhKNP6t3dv8AnUv+IVYTrvJ9sUorslY4rMsgnKTnTlqu76X8DXRJ8H7FDD0KlKolODjqvHdNptraz4Pc72eHi+G329CKVGS8fL+hPyEqiqJ63v5+PmeJPAU280XZ+K9fMr4eOmlGPOy9Xu/3M47vzf22Pe7k2rLr6kuHw7TTdtl9bmhu7ubUrKxbABBIAAAAAAAAAAABoO2M6NPCzrV76aWn4lFyktUlG1l1ujkauW1JVNFGE5VGt7rRKEeiUrKC8XufTQVyoUZtSmr/AIv9L6Zla7t0uk+UzTTxVSnBwT0OYyDCVsLiJYdUf/E7lVPzDnFuWI1JOOm+ytfr8q3u2dOAXSk5O7M7dwADkgp5hT2Uumz8igzcytZ34Wd+ljnaeNjLjt05ohtLclRbLSM4srqtH9UfVHrxMVw3DaQSbLin1ZTrT1O5FOu5eXQJnDdy2MbGcWTQmQpkiCkyHBEOaZdSxUNFWN7bxknacH1i+RzU8jxGHf8AgYqGn/NKdKo10em6l7HW6StiaWzaKK1GFTVrXqnZ+RrwuKq0VkjL6Xw1defvrc47OZY6lS11ZPu9UYu1Zy482ly/qjpOxeV0NCrp663R2tR32tFcG1z8yv2kpd/l9WMfmp6aluqhu0+u1/Y1XYrNXT2urNWafB9P78TClCliIt6prnWz/wBPWlOpXwM8lotN3UVa69+PQ+mgq4XFxqJNbN8ns/p1LR7Cdz5pprcAAkgAAAAAAAAAAAAAAAAAAAAAAAAAEdaqoRcnwSv/ANAGuz7F6KehfNPbyjzf14epz9MtYuTqSlOXF+y5IijGxnk7u5qgrKxgz2EzGRiQdFuMjOLK8JEsWSctFiLJolaMiWLOrnJOhJGMWZkkGkxtTu5Pa8ZJprk0zhcA+5rypvhGovqk9n6WZ9CzKhqsch2ry/uK1Gql8NSOl/642X2cPQ8vGwdsy4Z9B8Jqxk3S5kv1/drnZ5RiG2ori7WOoNB2ToWoKUoSjK7Sck03CytZPgjfnqUXmgpdT57ERUajguHYAAtKQAAAAAAAAAAAAAAAAAAAAACtSxUJycYyTabT81xRZBzGSls7g02b17zVPlGzfjLl7fc3Jz+axcKrb4Ss0+XDgcT2Lqa1KWPnopzlZvTFu3N2RTw+IU4prg0bCMHWapxV78XyjHm2eZplioSUqatCXLlF9Clp3utjSnFLK9ysyNnoYIMosmgysmTQkAyxFk0GVoMmgzpM4ZZizNMgUj1TOrnNiScTYYGmtOprnt4FCnFyaS4s3EIaUkuSsdR6nE+hmADsrAAAAAAAAAAAAAAAAAAAAAAAANTgsoVKo5qbcbtqNt1dc3fc2wBLbe5VSowpJqCst/fpsDCcFJWaTXRq6MwQWkdKlGKtGKS6JJL2McVQVSDi+D59HyZMBYHHV6ThJxlxT/tkDOozTL1WjdWU4rZ8mujOT75Xcbq6bT3T3+hmqfQ9TZTedXXG5IexZjc9RypI6aLFORMmVYMixuM0Rb8BKSirshQcnZF6piYx4tX6czOlVvyZzeCqNy7yW8n7LobyhjFzR5MsdOUtNF+zbLCqCtuzoMrgrOV1fh5GwNLlWKTmkuaa9rm6PZw1VVad0eVXg4zswADQVAAAAAAAAAAAAAAAAAAAAAHguLCwAuLiwsALi55YhqN9ACbWjF1UU5OT5GEqc+gBpu0WMlUn3MZNQjbVbbVLo/BdDUPAPiW80oVadST7uUottqUYufHqluiPDY220oyXnCS/Y+ZxUKk60nNP8accW/g93DyyUkoNe+pXanDxXTmXcLh6tSOqMVbhvJIVlKp8lOb8XFxj6uyNll1GVOFpNXbbduC8EbMDGs5WnfL1enm9yjFVIqN1bN74WxVjllV8ZUl/uk/sirnOStUZVO9u46XoUNmtSvvfpvw5G+1s8k7ppq6aaa4proenUw8ZRceqMMMTOMkzj8NZIsqobZZJRTulNL9OvZfuWaOApwd1TV+rvL7nkR+F1uWvP0X7N8sfS3Sfl6kvZ3CNf4stla0V16s35qqevkTRcz2aFGNKCgv9PMq1HUlmZfBWhKRLFstKyQHgAPQeXFwD0AAAAAAAAAAAAAAAAAAAAAAAA8PQAYSpp8UYPDx6EwAIe4j0Q7iPQmPACPuI9B3MehIegEXcx6Hvcx6EgAMVFI9segA8sD0AHh6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAf/9k=";

	        SwingUtilities.invokeLater(() -> new LibraryResult(title, author, publisher, pubYear, imageUrl));
	    }
	
	}
