package order;
import java.util.Comparator;

public class OrderComparator implements Comparator<Order>{
    public int compare(Order ord1,Order ord2){
        return ord1.getDate().compareTo(ord2.getDate());
    }
}
