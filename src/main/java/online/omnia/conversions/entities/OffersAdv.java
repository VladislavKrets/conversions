package online.omnia.conversions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lollipop on 29.09.2017.
 */
@Entity
@Table(name = "offers_adv")
public class OffersAdv {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "offer_id_adverts")
    private String offerIdAdverts;
    @Column(name = "offer_risk")
    private String offerRisk;
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public String getOfferIdAdverts() {
        return offerIdAdverts;
    }

    public String getOfferRisk() {
        return offerRisk;
    }

    public String getName() {
        return name;
    }
}
