import java.util.*;

public class Lover{
    private String name;
    private Boolean sex;
    private HashMap<Integer, Lover> pref = new HashMap<Integer, Lover>();
    private Lover match;
    private Boolean single = true;

    public String getName()
    {
        return name;
    }
    public Boolean getSex()
    {
        return sex;
    }
    public Map<Integer, Lover> getPref()
    {
        return pref;
    }
    public Lover getMatch()
    {
        return match;
    }
    public Boolean isSingle()
    {
        return single;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setSex(boolean sex)
    {
        this.sex = sex;
    }
    public void setMatch(Lover l)
    {
        this.match = l;
    }
    public void cuffed()
    {
        single = false;
    }
    public void uncuffed()
    {
        single = true;
    }
    public void addPref(Lover l, int rank)
    {
        pref.put(rank, l);
    }

}