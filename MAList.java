package MaekawaAlgorithm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class MAList {

  MAList(int max, int def) {
    MAXNODES = max;
    DEFAULTNODES = def;
  }

  // Accessor for algorithm names.
  String getAlgName(int index)
  {
    return titles[index];
  }

  // Get number of nodes if entered interactively
  int getNodes(int parm) {
    if (node != 0) return node;
    else return parm;
  }

  // Sentinel search for the algorithm code
  //   or obtain algorithm from GUI is no code entered.
  int getAlg(String s) {
    if (s.equals("")) {
        Interactive frame = new Interactive();
        frame.setup();
        waitObject.waitOK();  // Wait for reply from frame event handler.
        frame.dispose();
        return select;
    }
    int index = algs.length-1;
    algs[0] = s;
    while (! s.equals(algs[index])) index--;
    if (index == 0) badParam();
    return index;
  }

  // Display message and exit if bad algorithm code.
  private void badParam() {
    System.err.println("Usage: java alg [num] or " + 
      "java -jar daj.jar alg [num], where");
    System.out.println("  num is the number of nodes 2.." + 
      MAXNODES + " default " + DEFAULTNODES);
    System.out.println("  alg is the algorithm:");
    for (int i = 0; i <= algs.length; i++)
      System.out.println("    "+algs[i]+" - "+titles[i]);
  }

  // Inner class to create JFrame for GUI

  class Interactive extends JFrame {

  void setup() {
      
      if (MAScreen.isApplication) setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(450,130);
      setLocationRelativeTo(null);
      setLayout(new BorderLayout());
 
      final JComboBox algBox = new JComboBox();
      algBox.setEditable(false);
      for (int i = 1; i < algs.length; i++)
          algBox.addItem(titles[i]);
     // algBox.setSelectedIndex(DEFAULTALG);
      algBox.setBorder(BorderFactory.createEtchedBorder());
      algBox.setMaximumRowCount(MAXROWS);
      add(algBox, BorderLayout.CENTER);

      final JComboBox numBox = new JComboBox();
      numBox.setEditable(false);
      for (int i = 1; i <= 4; i++)
          numBox.addItem("  " + i + "  ");
      numBox.setSelectedIndex(DEFAULTNODES-1);
      numBox.setBorder(BorderFactory.createEtchedBorder());
      add(numBox, BorderLayout.EAST);

      // Label for title of panel.
      JLabel label = new JLabel(CHOOSE, JLabel.CENTER);
      label.setBorder(BorderFactory.createEtchedBorder());
      add(label, BorderLayout.NORTH);

      // Panel for buttons.
      JPanel buttonPanel = new JPanel();
      JButton OKButton = new JButton(START);
      JButton exitButton = new JButton(EXIT);
     
      OKButton.setMnemonic(START.charAt(1));
      exitButton.setMnemonic(EXIT.charAt(1));
    
      buttonPanel.add(OKButton);
      buttonPanel.add(exitButton);
   
      buttonPanel.setBorder(BorderFactory.createEtchedBorder());
      add(buttonPanel, BorderLayout.SOUTH);

      KeyListener kl = new KeyListener() {
          //public boolean isFocusable() { return true; }
          public void keyReleased(KeyEvent evt) {}
          public void keyPressed(KeyEvent evt) {}
          public void keyTyped(KeyEvent evt) {
            char c = evt.getKeyChar();
            if ((int) c == KeyEvent.VK_ENTER) {
                  select = algBox.getSelectedIndex() + 1;
                  node = numBox.getSelectedIndex() + 1;
                  waitObject.signalOK();
            }
            else if ((int) c == KeyEvent.VK_ESCAPE)
                System.exit(0);
          }
      };
      algBox.addKeyListener(kl);
      numBox.addKeyListener(kl);

      // Listener for buttons.
      ActionListener bl = new ActionListener() { 
          public void actionPerformed(ActionEvent e) {
              String cmd = e.getActionCommand();
              if (cmd.equals(START)) {
                  select = algBox.getSelectedIndex() + 1;
                  node = numBox.getSelectedIndex() + 1;
                  waitObject.signalOK();
              }
              else if (cmd.equals(EXIT)) 
                  System.exit(0);
             
            } // actionPerformed
      };  // anonymous class
  
      OKButton.addActionListener(bl);
      exitButton.addActionListener(bl);
    
      validate();
      setVisible(true);
    } // setup
  } // class Interactive

  // Titles and codes of the algorithms.

  private static final String[] titles = {
    "",
    
    "Maekawa Mutual Exclusion Algorithm ",
    
  };
  private static String[] algs =
    {"help",  "ma"};

  // Create and return object for an algorithm.
  MADistAlg getAlgObject(int index, int i, MADistAlg[] da, MAScreen s) {
    switch (index) {
     
        case  1: return new MA(i, da, s);
     
     }
    return null; // Dummy statement for compiler
  }

  private int     select;          // Algorithm selected by GUI
  private int     node = 0;        // Number of nodes selected by GUI
  private MAWaitObject waitObject = new MAWaitObject();  
                                   // For synchronization with event handlers
  private static int MAXNODES;     // Maximum number of nodes
  private static int DEFAULTNODES; // Default number of nodes
 // private static int DEFAULTALG = 1; // Default alg is MA
  private static final int MAXROWS = 15; // Maximum algs without scrolling
  private static final String      // GUI strings
    START = "Start",
    //ABOUT = "About",
    EXIT  = "Exit",
    CHOOSE = "Choose the algorithm and the number of cars";
}
