package ExamplesAndFoundations.TutorialsJava.Swing;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/*
 * LayeredPaneDemo.java requires
 * images/dukeWaveRed.gif.
 */
@SuppressWarnings("ALL")
public class LayeredPaneDemo extends JPanel
		implements ActionListener,
		MouseMotionListener {
	private String[] layerStrings = {"Yellow (0)", "Magenta (1)",
			"Cyan (2)", "Red (3)",
			"Green (4)"};
	private Color[] layerColors = {Color.yellow, Color.magenta,
			Color.cyan, Color.red,
			Color.green};

	private JLayeredPane layeredPane;
	private JLabel dukeLabel;
	private JCheckBox onTop;
	private JComboBox layerList;

	//Action commands
	private static String ON_TOP_COMMAND = "ontop";
	private static String LAYER_COMMAND = "layer";

	//Adjustments to put Duke's toe at the cursor's tip.
	private static final int XFUDGE = 40;
	private static final int YFUDGE = 57;

	public LayeredPaneDemo() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//Create and load the duke icon.
		final ImageIcon icon = createImageIcon();

		//Create and set up the layered pane.
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(300, 310));
		layeredPane.setBorder(BorderFactory.createTitledBorder(
				"Move the Mouse to Move Duke"));
		layeredPane.addMouseMotionListener(this);

		//This is the origin of the first label added.
		Point origin = new Point(10, 20);

		//This is the offset for computing the origin for the next label.
		int offset = 35;

		//Add several overlapping, colored labels to the layered pane
		//using absolute positioning/sizing.
		for (int i = 0; i < layerStrings.length; i++) {
			JLabel label = createColoredLabel(layerStrings[i],
					layerColors[i], origin);
			layeredPane.add(label, i);
			origin.x += offset;
			origin.y += offset;
		}

		//Create and add the Duke label to the layered pane.
		dukeLabel = new JLabel(icon);
		if (icon != null) {
			dukeLabel.setBounds(15, 225,
					icon.getIconWidth(),
					icon.getIconHeight());
		} else {
			System.err.println("Duke icon not found; using black square instead.");
			dukeLabel.setBounds(15, 225, 30, 30);
			dukeLabel.setOpaque(true);
			dukeLabel.setBackground(Color.BLACK);
		}
		layeredPane.add(dukeLabel, 2, 0);

		//Add control pane and layered pane to this JPanel.
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(createControlPanel());
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 */
	protected static ImageIcon createImageIcon() {
		java.net.URL imgURL = LayeredPaneDemo.class.getResource("images/dukeWaveRed.gif");
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + "images/dukeWaveRed.gif");
			return null;
		}
	}

	//Create and set up a colored label.
	private JLabel createColoredLabel(String text,
	                                  Color color,
	                                  Point origin) {
		JLabel label = new JLabel(text);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setBounds(origin.x, origin.y, 140, 140);
		return label;
	}

	//Create the control pane for the top of the frame.
	private JPanel createControlPanel() {
		onTop = new JCheckBox("Top Position in Layer");
		onTop.setSelected(true);
		onTop.setActionCommand(ON_TOP_COMMAND);
		onTop.addActionListener(this);

		layerList = new JComboBox(layerStrings);
		layerList.setSelectedIndex(2);    //cyan layer
		layerList.setActionCommand(LAYER_COMMAND);
		layerList.addActionListener(this);

		JPanel controls = new JPanel();
		controls.add(layerList);
		controls.add(onTop);
		controls.setBorder(BorderFactory.createTitledBorder(
				"Choose Duke's Layer and Position"));
		return controls;
	}

	//Make Duke follow the cursor.
	public void mouseMoved(MouseEvent e) {
		dukeLabel.setLocation(e.getX() - XFUDGE, e.getY() - YFUDGE);
	}

	public void mouseDragged(MouseEvent e) {
	} //do nothing

	//Handle user interaction with the check box and combo box.
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (ON_TOP_COMMAND.equals(cmd)) {
			if (onTop.isSelected())
				layeredPane.moveToFront(dukeLabel);
			else
				layeredPane.moveToBack(dukeLabel);

		} else if (LAYER_COMMAND.equals(cmd)) {
			int position = onTop.isSelected() ? 0 : 1;
			layeredPane.setLayer(dukeLabel,
					layerList.getSelectedIndex(),
					position);
		}
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("LayeredPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		JComponent newContentPane = new LayeredPaneDemo();
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(LayeredPaneDemo::createAndShowGUI);
	}
}

