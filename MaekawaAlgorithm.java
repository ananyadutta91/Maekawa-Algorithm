package MaekawaAlgorithm;
class MaekawaAlgorithm {
   public static void main(String[] args) {
     MAScreen.isApplication = true;
     String s0 = "", s1 = "";
     MAWaitObject waitObject = new MAWaitObject();
     if (args.length >= 1) s0 = args[0].toLowerCase();
     if (args.length >= 2) s1 = args[1];
     while (true) {
       new MAScreen(s0, s1, waitObject);
       waitObject.waitOK();
       s0 = ""; s1 = ""; // Arguments only for first execution 
     }
   }
}
