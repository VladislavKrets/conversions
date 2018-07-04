package online.omnia.conversions.entities;

import javax.persistence.*;

/**
 * Created by lollipop on 29.09.2017.
 */
@Entity
@Table(name = "offers_tracker")
public class OffersTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "prefix")
    private String prefix;
    @Column(name = "offer_id_tracker")
    private int offerIdTracker;
    @Column(name = "offer_id_adv")
    private int offerIdAdv;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private int type;
    @Column(name = "upsell")
    private int upsell;
    @Column(name = "payout")
    private double payout;
    @Column(name = "aff_net")
    private String affNet;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getOfferIdTracker() {
        return offerIdTracker;
    }

    public void setOfferIdTracker(int offerIdTracker) {
        this.offerIdTracker = offerIdTracker;
    }

    public int getOfferIdAdv() {
        return offerIdAdv;
    }

    public void setOfferIdAdv(int offerIdAdv) {
        this.offerIdAdv = offerIdAdv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUpsell() {
        return upsell;
    }

    public void setUpsell(int upsell) {
        this.upsell = upsell;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    @Override
    public String toString() {
        return "OffersTracker{" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                ", offerIdTracker=" + offerIdTracker +
                ", offerIdAdv=" + offerIdAdv +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", upsell=" + upsell +
                ", payout=" + payout +
                '}';
    }

    public String getAffNet() {
        return affNet;
    }

    public void setAffNet(String affNet) {
        this.affNet = affNet;
    }
}
