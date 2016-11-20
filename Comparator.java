
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Comparator {

    private ArrayList<Ticket> currentChain;
    private ArrayList<Ticket> currentStart;
    private ArrayList<Ticket> rout;
    private int counter = 5;
    private boolean select;//if true - price,else time
    
    public Comparator(ArrayList<Ticket> tickets,boolean sel) throws IOException, SAXException, ParserConfigurationException {
        currentStart = tickets;
        currentChain = tickets;
        rout = new ArrayList<>();
        select=sel;
    }

    public boolean getSelect()
    {
        return select;
    }
    public ArrayList<Ticket> getCurrentStart() {
        return currentStart;
    }

    public ArrayList<Ticket> getRout() {
        return rout;
    }

    public Ticket minPriceElement(ArrayList<Ticket> tikets) {
        Ticket tik = new Ticket();
        if (tikets.size() > 0) {
            tik = tikets.get(0);
        } else {
            tik = null;
        }
        for (int i = 0; i < tikets.size(); i++) {
            if (tik.getPrice() > tikets.get(i).getPrice()) {
                tik = tikets.get(i);
            }
        }
        return tik;
    }
    public Ticket minTimeElement(ArrayList<Ticket> tikets) {
        Ticket tik = new Ticket();
        if (tikets.size() > 0) {
            tik = tikets.get(0);
        } else {
            tik = null;
        }
        for (int i = 0; i < tikets.size(); i++) {
            if(tik.getTime()>tikets.get(i).getTime())
            {
                tik=tikets.get(i);
            }
        }
        return tik;
    }

    private Ticket nextStation() {
        ArrayList<Ticket> choise = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < currentChain.size(); i++) {
            flag = true;
            int currentDeparture = currentChain.get(i).getDepartureStationId();
            if (currentDeparture == rout.get(rout.size() - 1).getArrivalStationId()) {//последн
                for (int j = 0; j < rout.size(); j++) {
                    if (rout.get(j).getArrivalStationId() == currentChain.get(i).getArrivalStationId() || rout.get(0).getDepartureStationId() == currentChain.get(i).getArrivalStationId()) {
                        flag = false;
                        break;
                    }

                }
                if (flag) {
                    choise.add(currentChain.get(i));
                }
            }
        }
        if(select)
        {
        return minPriceElement(choise);
        }
        return minTimeElement(choise);
        
    }
    public void printTimes()
    {
         long times=0;
                    for(int i=0;i<rout.size();i++)
                    {
                        times+=rout.get(i).getTime();
                    }
                    LocalTime ti=LocalTime.of((int)(times%86400)/3600,(int)((times%86400)%6300)/60%60);
                    System.out.println("total time= "+times/86400+"days"+" "+ti);
    }
    public void printPrice()
    {
        
                    int prices=0;
                    for(int i=0;i<rout.size();i++)
                    {
                        prices+=rout.get(i).getPrice();
                    }
                    System.out.println("total price = "+prices);
    }

    public void minRout(Ticket tiket) {
        rout.add(tiket);
        counter--;
        if (nextStation() != null) {
            minRout(nextStation());
            if (counter == 0) {
                return;

            } else {
                currentStart.remove(minPriceElement(currentStart));
                removeRout();
                counter = 5;
                minRout(minPriceElement(currentStart));
                
            }
        }
    }

    public ArrayList<Ticket> getCurrentChain() {
        return currentChain;
    }

    void removeRout()
    {
        for(int i=0;i<rout.size();i++)
        {
        currentChain.remove(rout.get(i));
        currentStart.remove(rout.get(i));
        }
        rout.removeAll(rout);
    }
}
