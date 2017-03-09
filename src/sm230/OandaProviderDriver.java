//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import com.oanda.fxtrade.api.*;

import java.util.ArrayList;


public class OandaProviderDriver implements ProviderInterface {
    private FXClient fxClient;

    public OandaProviderDriver() {
    }

    @Override
    public void initProvider(String propertiesFile, String username, String password) throws Exception {
        fxClient = API.createFXTrade();


//
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream(new File(propertiesFile)));
//        } catch(Exception ex) {
//            System.out.println("no-no"); // TODO: Fix this
//            throw new Exception("Unable to open file " + propertiesFile);
//        }

        fxClient.login(username, password);
        RateTable rateTable = fxClient.getRateTable();
        Instrument instrument = rateTable.getInstrument("EUR/USD");
        System.out.println("Instrument: " + instrument.getBase() + "/" + instrument.getQuote());
        System.out.println("PIP: " + instrument.getPIP());
        System.out.println("Bid: " + instrument.getBid());


        System.out.println("EUR/USD rate: " +
                fxClient.getRateTable().getInstrument("EUR/USD").getMean()
        );



        //System.out.println("provider.oanda.apikey: " + properties.getProperty("provider.oanda.apikey"));
    }

    @Override
    public ArrayList<Tick> getTicks(String instrument, long beginTimestamp, long endTimestamp) {
        ArrayList<Tick> ticks = new ArrayList<>();

        return null;
    }
}
