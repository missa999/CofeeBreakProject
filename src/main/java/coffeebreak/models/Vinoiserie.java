package coffeebreak.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import coffeebreak.config.DatabaseConfig;
import coffeebreak.interfaces.Observer;
import coffeebreak.interfaces.Subject;

public class Vinoiserie implements Subject {
    private List<Observer> observers = new ArrayList<>();

    public Vinoiserie(String name) {
        this.nom = name;
    }

    public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public void setVentes(int ventes) {
		this.ventes = ventes;
	}


	private int id;
    private String nom;
    private float prix;
    private int ventes;
    private String imageUrl; // New field

    public Vinoiserie(int id, String nom, float prix, int ventes, String imageUrl) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.ventes = ventes;
        this.imageUrl = imageUrl;
    }

    public Vinoiserie(int id, String nom, float prix, int ventes) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.ventes = ventes;
    }

    public Vinoiserie(String nom, float prix, int ventes, String imageUrl) {
        this.nom = nom;
        this.prix = prix;
        this.ventes = ventes;
        this.imageUrl = imageUrl;
    }

    public Vinoiserie() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public float getPrix() {
        return prix;
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
        return "Vinoiserie [nom=" + nom + ", id=" + id + ", prix=" + prix + ", ventes=" + ventes + ", imageUrl=" + imageUrl + "]";
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
        // Fetch all users from the database
        try {
            Connection connection = DatabaseConfig.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT email FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                attach(new User(rs.getString("email")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
