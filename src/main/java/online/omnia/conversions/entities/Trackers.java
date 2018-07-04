package online.omnia.conversions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lollipop on 29.09.2017.
 */
@Entity
@Table(name = "trackers")
public class Trackers {
    @Id
    @Column(name = "prefix")
    private String prefix;
    @Column(name = "name")
    private String name;
    @Column(name = "domain")
    private String domain;
    @Column(name = "ip")
    private String ip;
    @Column(name = "modulename")
    private String moduleName;
    @Column(name = "index_php")
    private String indexPhp;
    @Column(name = "api_key")
    private String apiKey;
    @Column(name = "api_url")
    private String apiUrl;

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getIp() {
        return ip;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getIndexPhp() {
        return indexPhp;
    }
}
