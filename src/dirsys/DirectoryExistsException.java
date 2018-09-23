package dirsys;

class DirectoryExistsException extends Exception {
    DirectoryExistsException(String dirName, String pwd) {
        super("Can't create " + dirName + " at " + pwd);
    }
}