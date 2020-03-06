package ExamplesAndFoundations.Animation;

/*File Animate04.java
Revised 01/07/03

https://www.developer.com/java/other/article.php/1587091/fun-with-java-frame-animation.htm

Illustrates frame animation.  Stick figure
dances when user point to the image with the
mouse.  Stick figure stops dancing when mouse
pointer exits the image.

Also illustrates:
  Event-driven programming
  Multi-threaded programming
  Ordinary inner classes
  Anonymous inner classes
  Image icons

Tested using SDK 1.4.1 under Win 2000.
************************************************/
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Animation extends JFrame{
	Thread animate;//Store ref to animation thread
	ImageIcon images[] = {//An array of images
			new ImageIcon("src/main/resources/GameArt/PurplePlayer/player_p_down.png"),
			new ImageIcon("src/main/resources/GameArt/PurplePlayer/player_p_down_w_1.png"),
			new ImageIcon("src/main/resources/GameArt/PurplePlayer/player_p_down_w_2.png")};
	JLabel label = new JLabel(images[0]);

	//-------------------------------------------//
	public Animation(){//constructor

		getContentPane().add(label);

		//Use an anonymous inner class to register a
		// mouse listener
		getContentPane().addMouseListener(
				new MouseAdapter(){
					public void mouseEntered(MouseEvent e){
						//Get a new animation thread and start
						// the animation on it.
						animate = new Animate();
						animate.start();
					}//end mouseEntered

					public void mouseExited(MouseEvent e){
						//Terminate the animation.
						animate.interrupt();
						//Let the thread die a natural death.
						// Then make it eligible for garbage
						// collection.
						while (animate.isAlive()){}//loop;
						animate = null;
						//Restore default image.
						label.setIcon(images[0]);
						label.repaint();
					}//end MouseExited
				}//end new MouseAdapter
		);//end addMouseListener()
		//End definition of anonymous inner class

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Copyright 2003, R.G.Baldwin");
		setSize(250,200);
		setVisible(true);

	}//end constructor
	//-------------------------------------------//

	public static void main(String[] args){
		new Animation();
	}//end main
	//-------------------------------------------//

	//Ordinary inner class to animate the image
	class Animate extends Thread{

		public void run(){//begin run method
			try{
				//The following code will continue to
				// loop until the animation thread is
				// interrupted by the mouseExited
				// method.
				while(true){
					//Display several images in succession.
					display(1,500);
					display(0,500);
					display(2,500);
					display(0,500);
				}//end while loop
			}catch(Exception ex){
				if(ex instanceof InterruptedException){
					//Do nothing. This exception is
					// expected on mouseExited.
				}else{//Unexpected exception occurred.
					System.out.println(ex);
					System.exit(1);//terminate program
				}//end else
			}//end catch
		}//end run
		//-----------------------------------------//

		//This method displays an image and sleeps
		// for a prescribed period of time.  It
		// terminates and throws an
		// InterruptedException when interrupted
		// by the mouseExited method.
		void display(int image,int delay)
				throws InterruptedException{
			//Select and display an image.
			label.setIcon(images[image]);
			label.repaint();
			//Check interrupt status.  If interrupted
			// while not asleep, force animation to
			// terminate.
			if(Thread.currentThread().interrupted())
				throw(new InterruptedException());
			//Delay specified number of msec.
			//Terminate animation automatically if
			// interrupted while asleep.
			Thread.currentThread().sleep(delay);
		}//end display method
		//-----------------------------------------//
	}//end inner class named Animate

}//end class Animate04
