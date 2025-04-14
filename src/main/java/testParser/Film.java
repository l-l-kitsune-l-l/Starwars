package testParser;

public class Film {
    public String title;
    public int episode_id;
    public String director;
    public String release_date;

    // Constructeur vide requis par Gson
    public Film() {}

    @Override
    public String toString() {
        return "🎬 " + title + " (Episode " + episode_id + ") - 🎬 " + director + ", 📅 " + release_date;
    }
}
