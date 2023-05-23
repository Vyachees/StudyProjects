package checkParts;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionServiceImpl implements PositionService {

    @Override
    public void add(Position position) throws IOException {
        if (position == null) {
            System.out.println("Object is null, can't write!");
        }
        assert position != null;
        String str = position.toString();

        try (FileWriter myWriter = new FileWriter(getFile(), true)) {
            myWriter.write(str);
            myWriter.write(System.getProperty("line.separator"));
        } catch (IOException e) {
            throw new IOException("An error occurred. " + e);
        }
    }


    @Override
    public List<Position> read(int idHeader) {
        List<Position> positionList = new ArrayList<>();
        try (FileReader reader = new FileReader(getFile())) {
            StringBuilder res = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                res.append((char) i);
            }
            String[] resArr = String.valueOf(res).split(System.getProperty("line.separator"));
            for (String s : resArr) {
                String[] resArr2 = s.split(";");
                if (Integer.parseInt(resArr2[0]) == idHeader) {
                    Position position = new Position(idHeader, Integer.parseInt(resArr2[1]), Integer.parseInt(resArr2[2]), Integer.parseInt(resArr2[3]));
                    positionList.add(position);
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return positionList;

    }

    @Override
    public void delete(int idHeader, int idProduct) throws IOException {
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
                    if (Integer.parseInt(resArr2[0]) == idHeader && Integer.parseInt(resArr2[1]) == idProduct) {
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
        return new File("positions.txt");
    }

    public void recreate() throws IOException {
        try (FileWriter ignored = new FileWriter(getFile(), false)) {
        }
    }
}
