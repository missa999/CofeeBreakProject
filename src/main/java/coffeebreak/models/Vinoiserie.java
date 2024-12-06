package coffeebreak.models;

public class Vinoiserie {
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
}
