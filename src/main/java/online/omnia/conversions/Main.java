package online.omnia.conversions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import online.omnia.conversions.entities.Offer;
import online.omnia.conversions.entities.OffersAdv;
import online.omnia.conversions.entities.OffersTracker;
import online.omnia.conversions.entities.Trackers;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lollipop on 29.09.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<Trackers> trackers = MySQLDaoImpl.getInstance().getTrackers();
        List<Offer> offerList = null;
        OffersTracker offersTracker;
        for (Trackers tracker : trackers) {
            String answer = null;
            try {
                answer = HttpMethodUtils.getMethod(tracker.getDomain()
                                + (tracker.getIndexPhp() != null ? "/" + tracker.getIndexPhp() : "")
                                + "?page=Offers&api_key=" + tracker.getApiKey(),
                        new HashMap<>());
            } catch (UnknownHostException e) {
                System.out.println("Unknown host exception");
                System.out.println("Check is url valid:");
                System.out.println(tracker.getDomain());
                System.out.println("If url is valid check your internet connection");
                continue;
            }
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(List.class, new JsonOfferListDeserializer());
            Gson gson = builder.create();
            try {
                offerList = gson.fromJson(answer, List.class);
            } catch (JsonSyntaxException e) {
                continue;
            }
            if (offerList == null) continue;
            System.out.println("offers got");
            System.out.println(offerList.size());
            for (Offer offer : offerList) {
                offersTracker = new OffersTracker();
                offersTracker.setName(offer.getName());
                OffersAdv offersAdv = MySQLDaoImpl.getInstance().getOfferAdvByName(offer.getName());
                offersTracker.setOfferIdAdv(
                         offersAdv == null ? 0 : offersAdv.getId()
                );
                offersTracker.setOfferIdTracker(Integer.parseInt(offer.getId()));
                offersTracker.setUpsell(Integer.parseInt(offer.getUpsell()));
                offersTracker.setPayout(Double.parseDouble(offer.getPayout()));
                offersTracker.setPrefix(tracker.getPrefix());
                offersTracker.setAffNet(offer.getAffNet());
                System.out.println(offersTracker);
                if (MySQLDaoImpl.getInstance().isOffersTracker(offersTracker)) {
                    System.out.println("updating offer");
                    MySQLDaoImpl.getInstance().updateOffersTracker(offersTracker);
                }
                else {
                    System.out.println("adding");
                    MySQLDaoImpl.getInstance().addOffersTracker(offersTracker);
                }
            }

        }
        MySQLDaoImpl.getMasterDbSessionFactory().close();
    }
}
