package matej.petric.androidwarsapplication;

public class WarsItem {
    private String mName;
    private int mMass;
    private int mHeight;
    private String mGender;
    private String mFilms;

    public WarsItem(String name, int mass, int height, String gender, String films) {
        mName = name;
        mMass = mass;
        mHeight = height;
        mGender = gender;
        mFilms = films;

    }

    public String getName() {
        return mName;
    }

    public int getMass() {
        return mMass;
    }

    public int getHeight() {
        return mHeight;
    }

    public String getGender() {
        return mGender;
    }

    public String getFilms() {
        return mFilms;
    }

}


