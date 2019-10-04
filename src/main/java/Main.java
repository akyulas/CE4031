/**
 * Created by jodiakyulas on 4/9/19.
 */


public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter the file name as the first argument!");
            System.exit(1);
        }
        try {
            Parser parser = new Parser();
            long startTime = System.nanoTime();
            parser.parse(args[0]);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println(duration);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
