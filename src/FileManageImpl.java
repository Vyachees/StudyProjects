import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileManageImpl implements FileManage {

    String newCongrats;

    FileManageImpl(String newCongrats){
        this.newCongrats=newCongrats;
    }
    @Override
    public List<String> readFromFile(Path readFilePath) {
        List <String> result;
        if (Files.exists(readFilePath)) {
            try {
                result=Files.readAllLines(readFilePath, StandardCharsets.UTF_8);
                System.out.println("****Read successful****");
                return result;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Read file does not exist");
        }
    }

    @Override
    public void writeToFile(List<String> readedFile, Path writeFilePath) {
        if (Files.exists(writeFilePath)) {
            try {
                Files.write(writeFilePath, readedFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("\n****Save successful****");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Write file does not exist");
        }
    }



    @Override
    public List<User> createUsers(List<String> readedFile, int camelTag) {
        List<User> result=new ArrayList<>();
        for(String str: readedFile){
            String[] data=str.split("/");
            User user= new User(data[0],data[1],data[2],data[3],data[4], data[5] ,data[6],camelTag);
            result.add(user);
        }
        return result;
    }
    @Override
    public List<String> createEmails(List<User> users) throws ParseException {
        List<String> result= new ArrayList<>();
        for(int i=0;i<users.size();i++){
            if(i==0){
                result.add(createOneMsg(users.get(i)));
            }
            else{
                result.add("\n"+"\n"+createOneMsg(users.get(i)));
            }
        }
        return result;
    }

    public String createOneMsg(User user) throws ParseException {

        String congrats="Поздравляем Вас с наступлением нового года!";

        if(!newCongrats.isEmpty()){
            congrats=newCongrats;
        }

        String form="To: %s\n%s %s. " +
                "%s " +
                "Благодарим, что Вы уже %s %s с нами и дарим Вам купон на %s рублей!";

        String appeal="Уважаемый";
        if(user.getGender().equals("женский")){
            appeal="Уважаемая";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date pastDate = sdf.parse(user.getRegistration());
        Date now = sdf.parse(String.valueOf(LocalDateTime.now()));
        long years = (long) Math.floor(now.getTime() - pastDate.getTime() )/1000/60/60/24/365;

        String yearsSuffix ="лет";
        boolean isExclusion = (years % 100 >= 11) && (years % 100 <= 14);
        long yearsLastNumber = years % 10;
        if (yearsLastNumber == 1)
            yearsSuffix = "год";
        else if(yearsLastNumber == 0 || yearsLastNumber >= 5)
            yearsSuffix = "лет";
        else if(yearsLastNumber >= 2)
            yearsSuffix = "года";
        if (isExclusion)
            yearsSuffix = "лет";

        long sum=100;
        if(years!=0){
        sum=user.getBuyCounts()*100/years;
        }
        if(sum<100){
            sum=100;
        }
        else if(sum%50!=0) {
            if(sum%50<25){
                sum=sum-sum%50;
            }
            else{
                sum=sum+50-sum%50;
            }

        }

        return String.format(form,
                user.getEmail()
                ,appeal
                ,user.getName()+" "+user.getSurname1()+" "+user.getSurname2()
                ,congrats
                ,years
                ,yearsSuffix
                ,sum);
    }

}
