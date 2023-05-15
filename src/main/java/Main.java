import app.observer.File;
import app.observer.Folder;
import app.subscriber.FileCountObserver;

public class Main {
    public static void main(String[] args) {

        FileCountObserver fileCountObserver = new FileCountObserver();

        Folder parentFolder = new Folder("parentFolder", null);

        Folder folder1 = new Folder("folder1", parentFolder);
        Folder folder2 = new Folder("folder2", parentFolder);
        Folder folder3 = new Folder("folder3", folder1);
        Folder folder4 = new Folder("folder4", folder1);
        Folder folder5 = new Folder("folder5", folder4);
        Folder folder6 = new Folder("folder6", folder4);
        Folder folder7 = new Folder("folder7", folder4);

        File file1 = new File("File1", 100);
        File file2 = new File("File2", 200);
        File file3 = new File("File3", 300);
        File file4 = new File("File4", 400);
        File file5 = new File("File5", 400);

        parentFolder.addChildren(folder1);
        parentFolder.addChildren(folder2);

        folder1.addFiles(file1);
        folder1.addFiles(file2);
        folder1.addChildren(folder3);
        folder1.addChildren(folder4);


        folder4.addFiles(file3);
        folder4.addFiles(file4);
        folder4.addFiles(file5);
        folder4.addChildren(folder5);
        folder4.addChildren(folder6);
        folder4.addChildren(folder7);


        System.out.println(fileCountObserver.getFolderStatMap().get(folder4).getFileCountPerFolder());
        System.out.println(fileCountObserver.getFolderStatMap().get(folder4).getFileSizePerFolder());

        System.out.println(fileCountObserver.getFolderStatMap());

    }
}
