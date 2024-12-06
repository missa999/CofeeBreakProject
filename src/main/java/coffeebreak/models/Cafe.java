package coffeebreak.models;

public class Cafe {
	private int id;
    private String nomCafe;
    private float prix;
    private int ventes;
    private String imageUrl; // New field

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

}
