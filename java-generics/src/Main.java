import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        List<IAnimal> animaux = new ArrayList<>();
        animaux.add(new Vache());
        animaux.add(new Mouton());
        animaux.add(new Vache());

        System.out.println(animaux);

        for (IAnimal a : animaux) {
            System.out.println(a.getClass());
        }
    }
}
