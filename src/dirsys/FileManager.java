package dirsys;

import java.util.*;

public class FileManager {
    private DirNode root;
    private DirNode pwd;

    public FileManager() {
        init();
    }

    private void init() {
        this.root = new DirNode();
        pwd = root;
    }

    public String cd(String path) {
        try {
            pwd = getPath(path);
            return "SUCC: REACHED";
        } catch (DirectoryNotExistsException e) {
            System.err.println(e);
            return "ERR: INVALID PATH";
        }
    }

    private DirNode getPath(String path) throws DirectoryNotExistsException {
        StringTokenizer st = new StringTokenizer(path, "/");
        DirNode dest = path.equals("/") ? root : pwd;
        while (st.hasMoreTokens()) {
            String next = st.nextToken();
            dest = dest.getChild(next);
        }
        return dest;
    }

    public String mkdir(String... dirs) {
        String msg = "SUCC: CREATED";
        for (String dir : dirs) {
            try {
                int lastSeperator = dir.lastIndexOf("/");
                String path = (lastSeperator >= 0) ? dir.substring(0, lastSeperator) : "";
                DirNode temp = getPath(path);
                temp.addChild(dir.substring(lastSeperator + 1));
            } catch (DirectoryExistsException e) {
                System.err.println(e);
                msg = "ERR: DIRECTORY(S) ALREADY EXISTS";
            } catch (DirectoryNotExistsException e) {
                System.err.println(e);
                msg = "ERR: INVALID PATH OR NAME";
            }
        }
        return msg;

    }

    public String rm(String... dirs) {
        String msg = "SUCC: DELETED";
        for (String dir : dirs) {
            try {
                int lastSeperator = dir.lastIndexOf("/");
                String path = (lastSeperator >= 0) ? dir.substring(0, lastSeperator) : "";
                DirNode temp = getPath(path);
                temp.removeChild(dir.substring(lastSeperator + 1));
            } catch (DirectoryNotExistsException e) {
                System.err.println(e);
                msg = "ERR: DIRECTORY(S) DOESN'T EXISTS";
            }
        }
        return msg;

    }

    public String ls(String path) {
        try {

            DirNode temp = this.getPath(path);
            StringBuilder sb = new StringBuilder();
            for (String s : temp.listDir()) {
                sb.append(s);
                sb.append(' ');
            }
            return "DIRS: " + sb.toString();

        } catch (DirectoryNotExistsException e) {
            System.err.println(e);
            return "ERR: INVALID PATH";
        }
    }

    public String session(String cmd) {
        if (cmd.equalsIgnoreCase("CLEAR")) {
            init();
            return "SESSION CLEARED";
        }
        return "NO MATCHING ACTION FOUND FOR \" " + cmd + " \" ";
    }

    public String ls() {
        return ls("");
    }

    public String pwd() {
        return "PATH: " + pwd.toString();
    }
}