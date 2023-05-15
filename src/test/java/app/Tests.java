package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.observer.File;
import app.observer.Folder;
import app.subscriber.FileCountObserver;

class Tests {
    FileCountObserver fileCountObserver;

    ManageFileTree manageFileTree;
    Folder parentFolder;
    Folder folder1;
    Folder folder2;
    Folder folder3;
    Folder folder4;
    Folder folder5;
    Folder folder6;
    Folder folder7;

    File file1;
    File file2;
    File file3;
    File file4;
    File file5;

    @BeforeEach
    void init(){
        fileCountObserver = new FileCountObserver();

        manageFileTree=new ManageFileTree();

        parentFolder = new Folder("parentFolder", null);

        folder1 = new Folder("folder1", parentFolder);
        folder2 = new Folder("folder2", parentFolder);
        folder3 = new Folder("folder3", folder1);
        folder4 = new Folder("folder4", folder1);
        folder5 = new Folder("folder5", folder4);
        folder6 = new Folder("folder6", folder4);
        folder7 = new Folder("folder7", folder4);

        file1 = new File("File1", 100);
        file2 = new File("File2", 200);
        file3 = new File("File3", 300);
        file4 = new File("File4", 400);
        file5 = new File("File5", 400);

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
    }

    @Test
    void testUpdateFromFolder() {
        Assertions.assertEquals(3,fileCountObserver.getFolderStatMap().get(folder4).getFileCountPerFolder());
        Assertions.assertEquals(1100,fileCountObserver.getFolderStatMap().get(folder4).getFileSizePerFolder());
    }

    @Test
    void folderOne() {
       // System.out.println(ManageFileTree.folderOne(folder1));
        Assertions.assertEquals("folderName: folder1\n" +
                "children: [folder3, folder4]\n" +
                "files: [File{name='File1', size=100}, File{name='File2', size=200}]\n" +
                "=============================", ManageFileTree.folderOne(folder1));
    }

    @Test
    void parentChildPass() {
        Assertions.assertEquals("parent.folderName: null\n" +
                "folderName: parentFolder\n" +
                "children: [folder1, folder2]\n" +
                "files: []\n" +
                "=============================folderName: folder1\n" +
                "children: [folder3, folder4]\n" +
                "files: [File{name='File1', size=100}, File{name='File2', size=200}]\n" +
                "=============================folderName: folder3\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================folderName: folder4\n" +
                "children: [folder5, folder6, folder7]\n" +
                "files: [File{name='File3', size=300}, File{name='File4', size=400}, File{name='File5', size=400}]\n" +
                "=============================folderName: folder5\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================folderName: folder6\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================folderName: folder7\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================folderName: folder2\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================",ManageFileTree.parentChildPassAppend(parentFolder));
    }

    @Test
    void childParentPass() {
        Assertions.assertEquals("folderName: folder3\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================\n" +
                "folderName: folder5\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================\n" +
                "folderName: folder6\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================\n" +
                "folderName: folder7\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================\n" +
                "folderName: folder4\n" +
                "children: [folder5, folder6, folder7]\n" +
                "files: [File{name='File3', size=300}, File{name='File4', size=400}, File{name='File5', size=400}]\n" +
                "=============================\n" +
                "folderName: folder1\n" +
                "children: [folder3, folder4]\n" +
                "files: [File{name='File1', size=100}, File{name='File2', size=200}]\n" +
                "=============================\n" +
                "folderName: folder2\n" +
                "children: []\n" +
                "files: []\n" +
                "=============================\n" +
                "parent.folderName: null\n" +
                "folderName: parentFolder\n" +
                "children: [folder1, folder2]\n" +
                "files: []\n" +
                "============================="+
                "\n"
                ,ManageFileTree.childParentPassApend(parentFolder));
    }

}
