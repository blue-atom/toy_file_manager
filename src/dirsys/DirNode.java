package dirsys;

import java.util.*;

class DirNode {
    private HashMap<String, DirNode> dirMap;
    private String dirPath;

    DirNode(String dirName, DirNode parent) {
        this.dirPath = parent.toString() + "/" + dirName;
        dirMap = new HashMap<String, DirNode>();
        dirMap.put(".", this);
        dirMap.put("..", parent);
    }

    DirNode() {
        this.dirPath = "/root";
        dirMap = new HashMap<String, DirNode>();
        dirMap.put(".", this);
        dirMap.put("root", this);
        dirMap.put("..", this);
    }

    boolean addChild(DirNode fn) throws DirectoryExistsException {
        String dirName = fn.toString();
        if (dirMap.containsKey(dirName))
            throw new DirectoryExistsException(dirName, this.dirPath);
        dirMap.put(dirName, fn);
        return true;
    }

    boolean addChild(String dirName) throws DirectoryExistsException {
        if (dirMap.containsKey(dirName))
            throw new DirectoryExistsException(dirName, this.dirPath);
        dirMap.put(dirName, new DirNode(dirName, this));
        return true;
    }

    boolean removeChild(String dirName) throws DirectoryNotExistsException {
        if (!dirMap.containsKey(dirName))
            throw new DirectoryNotExistsException(dirName, this.dirPath);
        dirMap.remove(dirName);
        return true;
    }

    DirNode getChild(String dirName) throws DirectoryNotExistsException {
        if (!dirMap.containsKey(dirName))
            throw new DirectoryNotExistsException(dirName, this.dirPath);
        return dirMap.get(dirName);
    }

    ArrayList<String> listDir() {
        ArrayList<String> result = new ArrayList<String>();
        for (String dir : dirMap.keySet())
            if (!dir.equals("root"))
                result.add(dir);
        return result;
    }

    DirNode getParent() {
        return dirMap.get("..");
    }

    public String toString() {
        return this.dirPath;
    }

}
