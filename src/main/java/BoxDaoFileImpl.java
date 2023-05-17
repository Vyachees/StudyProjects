import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BoxDaoFileImpl implements BoxDao {

    //File file = getFile();

    @Override
    public void add(Box box) throws IOException {
        if (box == null) {
            System.out.println("Object is null, can't write!");
        }
        assert box != null;
        String str = box.toString();

        try (FileWriter myWriter = new FileWriter(getFile(), true)) {
            myWriter.write(str);
            myWriter.write(System.getProperty("line.separator"));
        } catch (IOException e) {
            throw new IOException("An error occurred. " + e);
        }
    }

    @Override
    public Box get(Long id) {

        Box resBox = null;
        try (FileReader reader = new FileReader(getFile())) {
            StringBuilder res = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                res.append((char) i);
            }
            String[] resArr = String.valueOf(res).split(System.getProperty("line.separator"));

            for (String s : resArr) {
                String[] resArr2 = s.split(";");
                for (String ignored : resArr2) {
                    if (Long.valueOf(resArr2[0]).equals(id)) {
                        resBox = new Box(id, resArr2[1]);

                    }
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return resBox;

    }

    @Override
    public void update(Long id, String name) throws IOException {

        String strToWrite = "";

        try (FileReader reader = new FileReader(getFile())) {
            StringBuilder res = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                res.append((char) i);
            }
            String[] resArr = String.valueOf(res).split(System.getProperty("line.separator"));

            String newName = id + ";" + name;

            for (int y = 0; y < resArr.length; y++) {
                String[] resArr2 = resArr[y].split(";");
                for (String ignored : resArr2) {
                    if (Long.valueOf(resArr2[0]).equals(id)) {
                        resArr[y] = newName;
                        break;
                    }
                }
            }
            strToWrite = Arrays.toString(resArr).replace(",", System.getProperty("line.separator"));
            strToWrite = strToWrite.replace("[", "").replace("]", "").replace(" ", "");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        recreateBoxes();
        try (FileWriter myWriter = new FileWriter(getFile(), true)) {
            myWriter.write(strToWrite);
        } catch (IOException e) {
            throw new IOException("An error occurred. " + e);
        }
    }

    @Override
    public void delete(String delName) throws IOException {

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
                    if (resArr2[1].equals(delName)) {
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
        recreateBoxes();
        try (FileWriter myWriter = new FileWriter(getFile(), true)) {
            myWriter.write(strToWrite);
        } catch (IOException e) {
            throw new IOException("An error occurred. " + e);
        }
    }

    private File getFile() {
        return new File("fileBoxes.txt");
    }

    public void recreateBoxes() throws IOException {
        try (FileWriter ignored = new FileWriter(getFile(), false)) {
        }

       /* FileOutputStream writer = new FileOutputStream("fileBoxes.txt");
        writer.write(("").getBytes());
        writer.close();*/
    }
}
