package online.omnia.conversions;

import online.omnia.conversions.entities.OffersAdv;
import online.omnia.conversions.entities.OffersTracker;
import online.omnia.conversions.entities.Trackers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

/**
 * Created by lollipop on 22.09.2017.
 */
public class MySQLDaoImpl {
    private static Configuration masterDbConfiguration;
    private static SessionFactory masterDbSessionFactory;

    private static MySQLDaoImpl instance;

    static {
        masterDbConfiguration = new Configuration()
                .addAnnotatedClass(OffersAdv.class)
                .addAnnotatedClass(OffersTracker.class)
                .addAnnotatedClass(Trackers.class)
                .configure("/hibernate.cfg.xml");
        Map<String, String> properties = FileWorkingUtils.iniFileReader();

        masterDbConfiguration.setProperty("hibernate.connection.password", properties.get("master_db_password"));
        masterDbConfiguration.setProperty("hibernate.connection.username", properties.get("master_db_username"));
        masterDbConfiguration.setProperty("hibernate.connection.url", properties.get("master_db_url"));
        while (true) {
            try {
                masterDbSessionFactory = masterDbConfiguration.buildSessionFactory();
                break;
            } catch (PersistenceException e) {
                try {
                    e.printStackTrace();
                    System.out.println("Can't connect to master db");
                    System.out.println("Waiting for 30 seconds");
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public OffersAdv getOfferAdvByName(String name) {
        Session session = masterDbSessionFactory.openSession();
        OffersAdv offersAdv;
        try {
            offersAdv = session.createQuery("from OffersAdv where name=:name", OffersAdv.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            offersAdv = null;
        }
        session.close();
        return offersAdv;
    }

    public boolean isOffersTracker(OffersTracker offersTracker) {
        Session session = masterDbSessionFactory.openSession();
        List<OffersTracker> offersTrackers = session.createQuery("from OffersTracker where prefix=:prefix and offer_id_tracker=:offerId and name=:name", OffersTracker.class)
                .setParameter("prefix", offersTracker.getPrefix())
                .setParameter("offerId", offersTracker.getOfferIdTracker())
                .setParameter("name", offersTracker.getName())
                .getResultList();
        session.close();
        return !offersTrackers.isEmpty();
    }

    public void updateOffersTracker(OffersTracker offersTracker) {
        Session session = masterDbSessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("update OffersTracker tracker set name=:name, tracker.type=:thisType, upsell=:upsell, payout=:payout where prefix=:prefix and offer_id_tracker=:offerId")
                .setParameter("name", offersTracker.getName())
                .setParameter("thisType", offersTracker.getType())
                .setParameter("upsell", offersTracker.getUpsell())
                .setParameter("payout", offersTracker.getPayout())
                .setParameter("prefix", offersTracker.getPrefix())
                .setParameter("offerId", offersTracker.getOfferIdTracker());
        session.getTransaction().commit();
        session.close();
    }

    public void addOffersTracker(OffersTracker offersTracker) {
        Session session = masterDbSessionFactory.openSession();
        session.beginTransaction();
        session.save(offersTracker);
        session.getTransaction().commit();
        session.close();
    }

    public List<Trackers> getTrackers(){
        Session session = masterDbSessionFactory.openSession();
        List<Trackers> trackers = session.createQuery("from Trackers", Trackers.class).getResultList();
        session.close();
        return trackers;
    }

    private MySQLDaoImpl() {
    }

    public static SessionFactory getMasterDbSessionFactory() {
        return masterDbSessionFactory;
    }

    public static MySQLDaoImpl getInstance() {
        if (instance == null) instance = new MySQLDaoImpl();
        return instance;
    }
}
