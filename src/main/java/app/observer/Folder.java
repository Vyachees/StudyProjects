package app.observer;

import app.Visitor;
import app.subscriber.FileCountObserver;

import java.util.ArrayList;
import java.util.List;

public class Folder implements Cloneable, Observer {

    private String folderName;
    private final Folder parent;
    private final List<Folder> children = new ArrayList<>();
    private final List<File> files = new ArrayList<>();

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getParentName() {
        return parent.folderName;
    }

    public Folder getParent() {
        return parent;
    }

    public List<Folder> getChildren() {
        return children;
    }

    public List<String> getChildrenName() {
        List<String> res = new ArrayList<>();
        for (Folder folder : children) {
            res.add(folder.folderName);
        }

        return res;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folderName='" + folderName + '\'' +
                ", children=" + children +
                ", files=" + files +
                '}';
    }

   /* public Folder(String folderName){
        this.folderName=folderName;
    }*/

    public Folder(String folderName, Folder parent) {
        this.folderName = folderName;
        this.parent = parent;
    }

    public void addChildren(Folder folder) {
        children.add(folder);
    }

    public void addFiles(File file) {
        files.add(file);
        updateSubscriberData();
    }

    @Override
    public Folder clone() {
        try {
            return (Folder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void accept(Visitor visitor) {
        visitor.visitAllFiles(this);
    }

    public int acceptForFileCount(Visitor visitor) {
        return visitor.calcFilesInForder(this);
    }

    public int acceptForFilesSize(Visitor visitor) {
        return visitor.calcFileSizeOfFolder(this);
    }


    @Override
    public void updateSubscriberData() {
        FileCountObserver.update(this);
    }
}
