package MaekawaAlgorithm;
import javax.swing.*;
public class MAAlgorithmApplet extends JApplet {
  public void init() {
    MAScreen.isApplication = false;
    String s0 = getParameter("alg");
    String s1 = getParameter("number");
    MAWaitObject waitObject = new MAWaitObject();
    if (s0 == null) s0 = ""; else s0 = s0.toLowerCase();
    if (s1 == null) s1 = "";
     while (true) {
       new MAScreen(s0, s1, waitObject);
       waitObject.waitOK();
       s0 = ""; s1 = ""; // Arguments only for first execution 
     }
   }
}
