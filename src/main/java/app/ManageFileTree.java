package app;

import app.observer.Folder;

public class ManageFileTree {
    public static String folderOne(Folder folder) {
        StringBuilder res=new StringBuilder();
        if (folder.getParent() == null) {
          //  System.out.println("parent.folderName: null");
            res.append("parent.folderName: null"+"\n");
        }
     //   System.out.println("folderName: " + folder.getFolderName());
        res.append("folderName: ").append(folder.getFolderName()).append("\n");
       // System.out.println("children: " + folder.getChildrenName());
        res.append("children: ").append(folder.getChildrenName()).append("\n");
       // System.out.println("files: " + folder.getFiles());
        res.append("files: ").append(folder.getFiles()).append("\n");
        //System.out.println("=============================");
        res.append("=============================");
        return String.valueOf(res);
    }
    static StringBuilder ress= new StringBuilder();

    public static String parentChildPassAppend(Folder folder){
        ress = new StringBuilder();
        parentChildPass(folder);
        return String.valueOf(ress);

    }

    private static void parentChildPass(Folder folder) {
        ress.append(folderOne(folder)) ;
        for (Folder fld : folder.getChildren()) {
            parentChildPass(fld);
        }

    }

    static StringBuilder ress2=new StringBuilder();

    public static String childParentPassApend(Folder folder){
        ress2=new StringBuilder();
        childParentPass(folder);
        return String.valueOf(ress2) ;
    }

    private static void childParentPass(Folder folder) {
        for (Folder fld : folder.getChildren()) {
            childParentPass(fld);
            if (fld.getChildren().size() == 0) {
                ress2.append(folderOne(fld)).append("\n") ;
            } else {
                ress2.append(folderOne(fld)).append("\n") ;
            }
            if (fld.getParent().getParent() == null && fld == fld.getParent().getChildren().get(fld.getParent().getChildren().size() - 1)) {
                ress2.append(folderOne(fld.getParent())).append("\n");
            }
        }
    }


}
