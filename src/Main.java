import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

        Path readFilePath = Paths.get("src", "resources", "clients_list.txt");
        Path writeFilePath = Paths.get("src", "resources", "result.txt");

        if(args.length!=0 && args[0]!=null && args[1]!=null){
            readFilePath= Paths.get(args[0]);
            writeFilePath= Paths.get(args[1]);
        }

        int camelTag=0;
            if (args.length==3 && args[2].equals("camelTag")) {
                camelTag = 1;
            }

        String newCongrats="";
        if (args.length==3 && !args[2].equals("camelTag")) {
            newCongrats=args[2];
        }

        FileManageImpl fileManage = new FileManageImpl(newCongrats);

        List<String> readedFile= fileManage.readFromFile(readFilePath);

        for(String str : readedFile){
            System.out.println(str);
        }

        System.out.println("-----------");


        fileManage.createUsers(readedFile,camelTag);
        List<User> users = fileManage.createUsers(readedFile,camelTag);

        List <String> emails= fileManage.createEmails(users);
        System.out.printf(emails.toString());

        fileManage.writeToFile(emails,writeFilePath );


    }
}
