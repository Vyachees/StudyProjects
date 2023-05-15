package app;

import app.observer.File;
import app.observer.Folder;

import static app.ManageFileTree.folderOne;

public class Visitor {
    private int fileCount = 0;
    private int fileSize = 0;

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public void visitAllFiles(Folder folder) {
        folderOne(folder);
        if (folder.getFiles().size() != 0) {
            fileCount += calcFilesInForder(folder);
            fileSize += calcFileSizeOfFolder(folder);
        }
        for (Folder fld : folder.getChildren()) {
            visitAllFiles(fld);
        }
    }

    public int calcFilesInForder(Folder folder) {
        return folder.getFiles().size();
    }

    public int calcFileSizeOfFolder(Folder folder) {
        int res = 0;
        for (File file : folder.getFiles()) {
            res += file.getSize();
        }
        return res;
    }
}
