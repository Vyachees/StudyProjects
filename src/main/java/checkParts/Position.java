package checkParts;

public class Position {
    private final int idHeader;
    private final int idProduct;
    private final int count;
    private final int pricePerId;
    private int priceTotalPerId;


    public Position(int idHeader, int idProduct, int count, int pricePerId) {
        this.idHeader = idHeader;
        this.idProduct = idProduct;
        this.count = count;
        this.pricePerId = pricePerId;
    }

    public int getId() {
        return idProduct;
    }


    public int getCount() {
        return count;
    }


    public int getPricePerId() {
        return pricePerId;
    }

    public int getIdHeader() {
        return idHeader;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setPriceTotalPerId(int priceTotalPerId) {
        this.priceTotalPerId = priceTotalPerId;
    }

    public int getPriceTotalPerId() {
        return priceTotalPerId;
    }

    @Override
    public String toString() {
        return idHeader + ";"
                + idProduct + ";"
                + count + ";"
                + pricePerId;
    }
}
