import java.awt.*;
import javax.swing.*;

/**
* This class helps load and display an image from a file.
* Original source:  http://beginwithjava.blogspot.com/2009/01/loading-and-displaying-image-in-java.html
* @since 2016-10-04
*/
public class FavoriteContactFrame extends JPanel{
	
	private Image image; // Declare a name for our Image object.
	private Image background;
	private Dimension window;  //Declare the dimensions of the window.
	private String nameInfo;
	private String numberInfo;
	private String notesInfo;
	private String emailInfo;
	private String directory;
	
	
	/**
	 * Initializes the JPanel instance variables to default values and gets image with given directory
	 * @param directory - the location of the image to be displayed
	 * */
	public FavoriteContactFrame(String directory, int fWidth, int fHeight){
		   super();     
		   window = new Dimension(fWidth, fHeight);	//Sets window dimensions of fWidth pixels wide X fHeight pixel high		   
		   image = Toolkit.getDefaultToolkit().getImage(directory);  // Load an image file into our Image object.
		   background = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Chad\\Pictures\\CECS274Proj2\\background2.jpg");
	}

	public FavoriteContactFrame(FavoriteContactFrame other)
	{
		super();
		this.image = Toolkit.getDefaultToolkit().getImage(other.directory);
		this.background = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Chad\\Pictures\\CECS274Proj2\\background2.jpg");
		this.window = new Dimension(other.window);
		this.setNameInfo(other.nameInfo);
		this.setNumberInfo(other.numberInfo);
		this.setNotesInfo(other.notesInfo);
		this.setEmailInfo(other.emailInfo);
	}


	/** 
	* Overrides the one in JPanel. This is where the drawing happens. We don't have to call it in our program, it gets called
	* automatically whenever the panel needs to be redrawn, like when it it made visible or moved.
	* @param g - Graphics object used to draw the image
	*/
	@Override
	public void paintComponent(Graphics g){
		
		  // Resizing our image to fit the window
		  Dimension originalImg = new Dimension(image.getWidth(this), image.getHeight(this));	    
		  Dimension resizedImg = getScaledDimension(originalImg, window);
		  Dimension originalBg = new Dimension(background.getWidth(this), background.getHeight(this));
		  Dimension resizedBg = getScaledDimensionBg(originalBg, window);
		  Font title = new Font("Monotype Corsiva", Font.BOLD, 55);
		  Font information = new Font("Impact", Font.PLAIN, 55);
		  	
		  
		// Draw our Image object.
		  g.drawImage(background, 0, 0, resizedBg.width, (int) resizedBg.height, this);
		  g.setColor(Color.orange);
		  g.fillRect(0, 0, resizedImg.width + 20, resizedImg.height+20);
		  g.setColor(Color.red);
		  g.drawRect(0, 0, resizedImg.width + 20, resizedImg.height+20);
		  g.drawImage(image,10,10,resizedImg.width, resizedImg.height, this); // at location 10,10, with scaled width and height
		  g.setFont(title);
		  g.drawString("Name:",(int) window.getWidth()/2 + 40, 100);
		  g.fillRect((int) window.getWidth()/2 + 40, 105, 150, 5);
		  g.setFont(information);
		  g.drawString(nameInfo, (int) window.getWidth()/2 + 60, 200 );
		  g.setFont(title);
		  g.drawString("Number:", (int) window.getWidth()/2 + 40, 300);
		  g.fillRect((int) window.getWidth()/2 + 40, 305, 200, 5);
		  g.setFont(information);
		  g.drawString(numberInfo, (int) window.getWidth()/2 + 60, 400);
		  g.setFont(title);
		  g.drawString("Notes:", (int) window.getWidth()/2 + 40, 500);
		  g.fillRect((int) window.getWidth()/2 + 40, 505, 150, 5);
		  g.setFont(information);
		  g.drawString(notesInfo, (int) window.getWidth()/2 + 60, 600);
		  g.setFont(title);
		  g.drawString("Email:", 10, (int) window.getHeight() - 100);
		  g.fillRect((int) 10, (int) window.getHeight() - 95, 150, 5);
		  g.setFont(information);
		  g.drawString(emailInfo, 185, (int) window.getHeight() - 100); 
		  
	 }
	
	/**
	 * Displays the image of the this object
	 * */
	public void displayContactImage(Contact favContact){
		//Creating the window
		  JFrame frame = new JFrame("Favorite Contact: " + favContact.getName());  //title of the frame will be "Favorite Contact"
		  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Exits the window when it is closed
		  frame.setSize(window.width, window.height);			//Sets the window size
		  
		//Displaying the image in the frame
		  frame.setContentPane(this); 
		  frame.setVisible(true);		
		
	}
	/**
	 * Opens up a window and displays favorite information
	 */
	public void displayContactImage(){
		//Creating the window
		  JFrame frame = new JFrame("Favorite Contact: " + this.getName());  //title of the frame will be "Favorite Contact"
		  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Exits the window when it is closed
		  frame.setSize(window.width, window.height);			//Sets the window size
		  
		//Displaying the image in the frame
		  frame.setContentPane(this); 
		  frame.setVisible(true);		
		
	}
  

	/**
	 * Scales picture based on window
	 * @param imgSize
	 * @param windowSize
	 * @return new window dimensions
	 */
  // helper method that scales the image based on window dimensions
  private static Dimension getScaledDimension(Dimension imgSize, Dimension windowSize) {

	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int window_width = windowSize.width;
	    int window_height = windowSize.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	   /* if (original_width > window_width/2) {
	        //scale width to fit within 1/2 the window width
	        new_width = window_width/2;
	        //scale height to maintain aspect ratio
	        //new_height = (new_width * original_height) / original_width;
	        new_height = window_height - 200;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > window_height) {
	        //scale height to fit 
	        new_height = window_height - 200;
	        //scale width to maintain aspect ratio
	        //new_width = (new_height * original_width) / original_height;
	        new_width = window_width/2;
	    }*/
	    new_height = window_height - 200;
	    new_width = window_width/2;
	    return new Dimension(new_width, new_height);
	}
  
  /**
   * Scales background to window size
   * @param bgSize
   * @param windowSize
   * @return new background dimensions
   */
  private static Dimension getScaledDimensionBg(Dimension bgSize, Dimension windowSize) {
	  int new_width = (int)windowSize.getWidth();
	  int new_height = (int)windowSize.getHeight();
	  return new Dimension(new_width, new_height); 
  }
  // SETTERS
  /**
   * Sets name of favorite
   * @param nameInfo - String
   */
  public void setNameInfo(String nameInfo){
	  this.nameInfo = nameInfo;
  }
  /**
   * Sets number of favorite
   * @param numberInfo - String
   */
  public void setNumberInfo(String numberInfo){
	  this.numberInfo = numberInfo;
  }
  /**
   * Sets notes of favorite
   * @param notesInfo - String
   */
  public void setNotesInfo(String notesInfo){
	  this.notesInfo = notesInfo;
  }
  /**
   * Sets email of favorite
   * @param emailInfo - String
   */
  public void setEmailInfo(String emailInfo){
	  this.emailInfo = emailInfo;
  }
  /**
   * Sets directory of favorite
   * @param directory - String
   */
 public void setDirectory(String directory){
	 this.directory = directory;
 }
}