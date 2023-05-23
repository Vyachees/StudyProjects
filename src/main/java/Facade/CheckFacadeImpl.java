package Facade;

import checkParts.Header;
import checkParts.HeaderServiceImpl;
import checkParts.Position;
import checkParts.PositionServiceImpl;

import java.io.IOException;
import java.util.List;

public class CheckFacadeImpl implements CheckFacadeInterface {
    private final HeaderServiceImpl headerService;
    private final PositionServiceImpl positionService;


    public CheckFacadeImpl(HeaderServiceImpl headerService, PositionServiceImpl positionService) {
        this.headerService = headerService;
        this.positionService = positionService;
    }

    //Метод для добавления позиции в чек
    @Override
    public void add(Header header, Position position) throws IOException {
        if (headerService.read(header.getIdHeader()) == null) {
            headerService.add(header);
        }
        positionService.add(position);
    }


    //Метод для чтения чека с позициями
    @Override
    public String read(int idHeader) {
        Header header = headerService.read(idHeader);
        List<Position> positionList = positionService.read(idHeader);
        int headerTotalSum = 0;

        StringBuilder resHeader = new StringBuilder("idHeader;dateTime;storeNum;cashBox;totalSum");
        StringBuilder resPositions = new StringBuilder("idHeader;idProduct;count;pricePerId;priceTotalPerId");
        for (Position pos : positionList) {
            pos.setPriceTotalPerId(pos.getPricePerId() * pos.getCount());
            headerTotalSum += pos.getPriceTotalPerId();
            resPositions.append("\n").append(pos).append(";").append(pos.getPriceTotalPerId());
        }
        header.setTotalSum(headerTotalSum);
        resHeader.append("\n").append(header).append(";").append(header.getTotalSum());

        return resHeader +
                "\n" +
                resPositions;
    }

    //Метод для удаления позиции из чека
    @Override
    public void delete(int idHeader, int idProduct) throws IOException {
        positionService.delete(idHeader, idProduct);
    }

    public void recreate() throws IOException {
        headerService.recreate();
        positionService.recreate();
    }
}
