package checkParts;

public class Header {
    private final int idHeader;
    private final String dateTime;
    private final int storeNum;
    private final int cashboxNum;
    private int totalSum;


    public Header(int idCheckHeader, String dateTime, int storeNum, int cashboxNum) {
        this.idHeader = idCheckHeader;
        this.dateTime = dateTime;
        this.storeNum = storeNum;
        this.cashboxNum = cashboxNum;
    }

    public int getIdHeader() {
        return idHeader;
    }

    @Override
    public String toString() {
        return idHeader + ";"
                + dateTime + ";"
                + storeNum + ";"
                + cashboxNum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getStoreNum() {
        return storeNum;
    }

    public int getCashboxNum() {
        return cashboxNum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public int getTotalSum() {
        return totalSum;
    }


}
