package app.subscriber;

public class FolderStatistic {
    private int fileCountPerFolder;
    private int fileSizePerFolder;


    public int getFileCountPerFolder() {
        return fileCountPerFolder;
    }

    public void setFileCountPerFolder(int fileCountPerFolder) {
        this.fileCountPerFolder = fileCountPerFolder;
    }

    public int getFileSizePerFolder() {
        return fileSizePerFolder;
    }

    public void setFileSizePerFolder(int fileSizePerFolder) {
        this.fileSizePerFolder = fileSizePerFolder;
    }

    public FolderStatistic(int fileCountPerFolder, int fileSizePerFolder) {
        this.fileCountPerFolder = fileCountPerFolder;
        this.fileSizePerFolder = fileSizePerFolder;
    }

    @Override
    public String toString() {
        return "FolderStatistic{" +
                "fileCountPerFolder=" + fileCountPerFolder +
                ", fileSizePerFolder=" + fileSizePerFolder +
                '}';
    }
}
