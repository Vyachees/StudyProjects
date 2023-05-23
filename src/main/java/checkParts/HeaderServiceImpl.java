package checkParts;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class HeaderServiceImpl implements HeaderService {

    @Override
    public void add(Header header) throws IOException {
        if (header == null) {
            System.out.println("Object is null, can't write!");
        }
        assert header != null;
        String str = header.toString();

        try (FileWriter myWriter = new FileWriter(getFile(), true)) {
            myWriter.write(str);
            myWriter.write(System.getProperty("line.separator"));
        } catch (IOException e) {
            throw new IOException("An error occurred. " + e);
        }
    }

    @Override
    public Header read(int idHeader) {
        Header header = null;
        try (FileReader reader = new FileReader(getFile())) {
            StringBuilder res = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                res.append((char) i);
            }
            if (res.length() == 0) {
                return null;
            }
            String[] resArr = String.valueOf(res).split(System.getProperty("line.separator"));

            for (String s : resArr) {
                String[] resArr2 = s.split(";");
                if (Integer.valueOf(resArr2[0]).equals(idHeader)) {
                    header = new Header(idHeader, resArr2[1], Integer.parseInt(resArr2[2]), Integer.parseInt(resArr2[3]));
                   /* result=String.format("idHeader; dateTime; storeNum; cashBox" +
                                    "\n%d; %s; %d; %d"
                            , idHeader,resArr2[1],Integer.parseInt(resArr2[2]) ,Integer.parseInt(resArr2[3])

                    );*/
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return header;

    }

    @Override
    public void delete(int idHeader) throws IOException {
        String strToWrite = "";

        try (FileReader reader = new FileReader(getFile())) {
            StringBuilder res = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                res.append((char) i);
            }
            String[] resArr = String.valueOf(res).split(System.getProperty("line.separator"));

            for (int y = 0; y < resArr.length; y++) {
                String[] resArr2 = resArr[y].split(";");
                for (String ignored : resArr2) {
                    if (Integer.parseInt(resArr2[0]) == idHeader) {
                        resArr[y] = "";
                        break;
                    }
                }
            }

            String[] resArr3 = new String[resArr.length - 1];
            int resArrIndex = 0;
            for (String s : resArr) {
                if (!s.equals("")) {
                    resArr3[resArrIndex] = s;
                    resArrIndex++;
                }
            }
            strToWrite = Arrays.toString(resArr3).replace(",", System.getProperty("line.separator"));
            strToWrite = strToWrite.replace("[", "").replace("]", "").replace(" ", "");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        recreate();
        try (FileWriter myWriter = new FileWriter(getFile(), true)) {
            myWriter.write(strToWrite);
        } catch (IOException e) {
            throw new IOException("An error occurred. " + e);
        }
    }

    private File getFile() {
        return new File("headers.txt");
    }

    public void recreate() throws IOException {
        try (FileWriter ignored = new FileWriter(getFile(), false)) {
        }
    }


}
