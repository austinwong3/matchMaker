import java.util.*;

public class MatchMakerManager
{
    private ArrayList<Lover> bois = new ArrayList();
    private ArrayList<Lover> gorls = new ArrayList();
    Scanner s = new Scanner(System.in);

    public void start()
    {
        //intro
        System.out.println("Welcome to Match Maker!");
        System.out.println("Time to get yo ass cuffed");

        //get lists of participants
        boolean cont = true;
        int participants = 0;
        while(cont)
        {
            
            System.out.println("Please input how many people are participating (Must be even amount of guys and girls): ");
            String temp = s.nextLine();
            if(temp.length() != 1 || Character.isLetter(temp.charAt(0)))
            {
                System.out.println("Error: Not a valid number");
            }
            else{
                if(Integer.parseInt(temp)%2 > 0)
                {
                    System.out.println("Error: Not a valid number");
                }
                else{
                    participants = Integer.parseInt(temp);
                    cont = false;
                }
            }
        }
        signUpMen(1, participants/2);
        signUpWomen(1, participants/2);

        //set all preferences
        for(Lover l: bois)
        {
            setPref(1, participants/2, l);
        }
        for(Lover l: gorls)
        {
            setPref(1, participants/2, l);
        }

        //algorithm
        stableMarriageManager();

        for(int k = 0; k < bois.size(); k++)
        {
            System.out.println(String.format("%s is matched with %s", bois.get(k).getName(), bois.get(k).getMatch().getName()));
        }

        //closing
        System.out.println("Enjoy the rest of your lives together UWU!");

    }

    public void setPref(int added, int remaining, Lover l)
    {
        if(remaining == 0)
        {
            return;
        }
        else{
            System.out.println(String.format("Who is Rank %s for %s? ", added, l.getName()));
            String gName = s.nextLine();
            for(Lover g: gorls)
            {
                if(g.getName().contains(gName))
                {
                    l.addPref(g, added);
                    break;
                }
            }
            setPref(added+1, remaining-1, l);

        }
    }

    public void signUpMen(int added, int remaining)
    {
        if(remaining == 0)
        {
            return;
        }
        else{
            Lover l = new Lover();
            l.setSex(true);
            System.out.println(String.format("Input name of Guy %s: ", added));
            l.setName(s.nextLine());
            //System.out.println("What is %s's first preference? ", l.getName());
            bois.add(l);
            signUpMen(added+1, remaining-1);
            return;
        }
    }

    public void signUpWomen(int added, int remaining)
    {
        if(remaining == 0)
        {
            return;
        }
        else{
            Lover l = new Lover();
            l.setSex(false);
            System.out.print(String.format("Input name of Girl %s: ", added));
            l.setName(s.nextLine());
            //System.out.println("What is %s's first preference? ", l.getName());
            gorls.add(l);
            signUpWomen(added+1, remaining-1);
            return;
        }
    }

    public void stableMarriageManager()
    {
        for(int k = 0; k < bois.size(); k++)
        {
            if(bois.get(k).isSingle() == true)
            {
                stableMarriage(bois.get(k));
            }
        }
    }

    public void stableMarriage(Lover male)
    {
            Map<Integer, Lover> pref = male.getPref();
            int i = 1;
            while(pref.keySet().contains(i))
            {
                Lover female = pref.get(i);
                if(female.isSingle() == true)
                {
                    male.setMatch(pref.get(i));
                    male.cuffed();
                    female.setMatch(male);
                    female.cuffed();
                    break;
                }
                else
                {
                    Lover currentMatch = female.getMatch();
                    Map<Integer, Lover> fpref = female.getPref();
                    int j = 1;
                    int currentRank=0;
                    int newRank=0;
                    while(fpref.keySet().contains(j))
                    {
                        if(fpref.get(j).getName().contains(male.getName()))
                        {
                            newRank = j;
                        }
                        if(fpref.get(j).getName().contains(currentMatch.getName()))
                        {
                            currentRank = j;
                        }
                    }
                    if(newRank<currentRank)
                    {
                        currentMatch.uncuffed();
                        currentMatch.setMatch(null);
                        male.setMatch(pref.get(i));
                        male.cuffed();
                        female.setMatch(male);
                        female.cuffed();

                        stableMarriage(currentMatch);
                        break;
                    }
                }
                i++;
            }
        }
        public void printOptions(ArrayList<Lover> people)
        {
            for(int i = 0; i < people.size(); i++)
            {
                System.out.println(String.format(""));
            }
        }
    }
