
import com.sun.javafx.collections.ArrayListenerHelper;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Train {

    public static void main(String[] args) throws ParseException, IOException, SAXException, ParserConfigurationException {

        XmlWorker x = new XmlWorker();
        ArrayList<Ticket> arr = x.work();
        System.out.println("Enter criterion of min rout:1)time 2)price");
        Scanner in=new Scanner(System.in);
        boolean choise;
        int choose=in.nextInt();
        if(choose>1)
        {
            choise=true;
        }
        else
        {
            choise=false;
        }
        
        ArrayList< ArrayList<Ticket>> a = new ArrayList<>();
         Comparator c = new Comparator(arr, choise);
        while(!arr.isEmpty()) {
            c.minRout(c.minPriceElement(c.getCurrentChain()));
            if(c.getRout().size()<5)
            {
                break;
            }
            a.add(c.getRout());
            for(int j=0;j<c.getRout().size();j++)
            {
            arr.remove(c.getRout().get(j));
            }
            
            if (c.getSelect()) {
                c.printPrice();
            } else {
                c.printTimes();
            }
            c = new Comparator(arr, choise);
        }
        System.out.println("");

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).size(); j++) {
                a.get(i).get(j).print();
            }
            System.out.println("");
        }

    }
}
