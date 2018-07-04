package online.omnia.conversions;

import com.google.gson.*;
import online.omnia.conversions.entities.Offer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 30.09.2017.
 */
public class JsonOfferListDeserializer implements JsonDeserializer<List<Offer>>{
    @Override
    public List<Offer> deserialize(JsonElement jsonElement, Type type,
                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {


        List<Offer> offers = new ArrayList<>();
        Offer offer;
        JsonArray offersArray = null;
        try {
            offersArray = jsonElement.getAsJsonArray();
        } catch (Exception e) {
            return offers;
        }

        for (JsonElement offerElement : offersArray) {

            offer = new Offer();
            offer.setId(offerElement.getAsJsonObject().get("id") == null ? "" : offerElement.getAsJsonObject().get("id").getAsString());
            offer.setName(offerElement.getAsJsonObject().get("name") == null ? "" : offerElement.getAsJsonObject().get("name").getAsString());
            offer.setUpsell(offerElement.getAsJsonObject().get("upsell") == null ? "" : offerElement.getAsJsonObject().get("upsell").getAsString());
            offer.setPayout(offerElement.getAsJsonObject().get("payout") == null ? "" : offerElement.getAsJsonObject().get("payout").getAsString());
            offer.setAffNet(offerElement.getAsJsonObject().get("network_name") == null ? "" : offerElement.getAsJsonObject().get("network_name").getAsString());
            offers.add(offer);
        }

        return offers;
    }
}
