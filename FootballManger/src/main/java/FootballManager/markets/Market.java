package FootballManager.markets;

import FootballManager.manager.Tournament;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_WEEK;

public class Market {

    private static Tournament rfpl;
    private MarketPeriod marketPeriod;
    private MarketType marketType;
    private GregorianCalendar startDate;
    private GregorianCalendar finishDate;

    public Market() {}

    public Market(MarketType marketType, MarketPeriod marketPeriod){
        this.marketPeriod = marketPeriod;
        this.marketType = marketType;
        startDate = (GregorianCalendar) rfpl.currentDate;
        setFinishDate();
    }

    private void setFinishDate() {
        finishDate = (GregorianCalendar) startDate.clone();
        int addDate;

        if(marketPeriod.equals(MarketPeriod.WEEK)) addDate = 7;
        else if (marketPeriod.equals(MarketPeriod.TWO_WEEKS)) addDate = 14;
        else addDate = 30;

        finishDate.add(Calendar.DAY_OF_MONTH, addDate);
    }

    public static void setRfpl(Tournament rfpl) {
        Market.rfpl = rfpl;
    }

    public MarketPeriod getMarketPeriod() {
        return marketPeriod;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketPeriod(MarketPeriod marketPeriod) {
        this.marketPeriod = marketPeriod;
    }

    public GregorianCalendar getFinishDate() {
        return finishDate;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }
}
