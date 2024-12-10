package coffeebreak.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import coffeebreak.config.DatabaseConfig;
import coffeebreak.interfaces.Observer;
import coffeebreak.interfaces.Subject;

public class Cafe implements Subject {
	private int id;
    private String nomCafe;
    private float prix;
    private int ventes;
    private String imageUrl;
    private List<Observer> observers = new ArrayList<>();

    public Cafe(String name) {
        this.nomCafe = name;
    }
    
    public Cafe(int id, String nomCafe, float prix, int ventes, String imageUrl) {
        this.id = id;
        this.nomCafe = nomCafe;
        this.prix = prix;
        this.ventes = ventes;
        this.imageUrl = imageUrl;
    }

    public Cafe(int id, String nomCafe, float prix, int ventes) {
        this.id = id;
        this.nomCafe = nomCafe;
        this.prix = prix;
        this.ventes = ventes;
    }

    public Cafe(String nomCafe, float prix, int ventes, String imageUrl) {
        this.nomCafe = nomCafe;
        this.prix = prix;
        this.ventes = ventes;
        this.imageUrl = imageUrl;
    }


    public Cafe() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public String getNomCafe() {
        return nomCafe;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
		this.id = id;
	}

	public void setNomCafe(String nomCafe) {
		this.nomCafe = nomCafe;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public void setVentes(int ventes) {
		this.ventes = ventes;
	}

	public int getVentes() {
        return ventes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Cafe [nomCafe=" + nomCafe + ", id=" + id + ", prix=" + prix + ", ventes=" + ventes + ", imageUrl=" + imageUrl + "]";
    }
    
    
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        try {
            Connection connection = DatabaseConfig.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT email FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                attach(new coffeebreak.models.User(rs.getString("email")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Observer observer : observers) {
            observer.update(message);
        }
    }

}
