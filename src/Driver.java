import dirsys.*;
import java.util.*;

class Driver {
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        Scanner in = new Scanner(System.in);
        System.out.println("$> Application Started. Press Ctrl+C to stop or type 'exit' ");
        while (true) {
            System.out.print("$> ");
            String[] line = in.nextLine().split("[ ]+");
            if (line[0].equalsIgnoreCase("cd")) {
                if (line.length == 2) {
                    String dir = line[1];
                    System.out.println(fm.cd(dir));
                } else {
                    System.err
                            .println("INVALID ARGUMENTS : cd EXPECTS EXACTLY ONE ARGUMENT, GIVEN " + (line.length - 1));
                }
            } else if (line[0].equalsIgnoreCase("mkdir")) {
                if (line.length >= 2) {
                    String[] argList = new String[line.length - 1];
                    System.arraycopy(line, 1, argList, 0, argList.length);
                    System.out.println(fm.mkdir(argList));
                } else {
                    System.err.println(
                            "INVALID ARGUMENTS : mkdir EXPECTS ATLEAST ONE ARGUMENT, GIVEN " + (line.length - 1));
                }
            } else if (line[0].equalsIgnoreCase("rm")) {
                if (line.length >= 2) {
                    String[] argList = new String[line.length - 1];
                    System.arraycopy(line, 1, argList, 0, argList.length);
                    System.out.println(fm.rm(argList));
                } else {
                    System.err
                            .println("INVALID ARGUMENTS : rm EXPECTS ATLEAST ONE ARGUMENT, GIVEN " + (line.length - 1));
                }
            } else if (line[0].equalsIgnoreCase("pwd")) {
                System.out.println(fm.pwd());
            } else if (line[0].equalsIgnoreCase("ls")) {
                System.out.println(fm.ls());
            } else if (line[0].equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("INVALID COMMAND");
            }
        }
    }
}