package dirsys;

class DirectoryNotExistsException extends Exception {
    DirectoryNotExistsException(String dirName, String pwd) {
        super(dirName + " doesn't exists at " + pwd);
    }
}