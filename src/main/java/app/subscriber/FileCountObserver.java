package app.subscriber;

import app.Visitor;
import app.observer.Folder;
import java.util.HashMap;
import java.util.Map;

public class FileCountObserver  {
    private static final Map<Folder, FolderStatistic> folderStatMap = new HashMap<>();

    public static void update(Folder folder) {
        Visitor visitor = new Visitor();
        int countFiles = visitor.calcFilesInForder(folder);
        int countFilesSize = visitor.calcFileSizeOfFolder(folder);
        if (!folderStatMap.containsKey(folder)) {
            FolderStatistic folderStatistic = new FolderStatistic(countFiles, countFilesSize);
            folderStatMap.put(folder, folderStatistic);
        } else {
            FolderStatistic folderStatistic = folderStatMap.get(folder);
            folderStatistic.setFileCountPerFolder(countFiles);
            folderStatistic.setFileSizePerFolder(countFilesSize);
        }
    }

    public Map<Folder, FolderStatistic> getFolderStatMap() {
        return folderStatMap;
    }


}
