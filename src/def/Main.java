package def;

import sorters.MaxSelectionSorter;
import sorters.MinSelectionSorter;
import swing_components.MenuFrame;

import java.util.ArrayList;

public final class Main {

    public static void main(final String[] args) {

//        boolean algoWorks = true;
//        for (int count = 0; count < 1000 && algoWorks; count++) {
//            final ArrayList<Integer> test = NumberListFactory.getSmallShuffled();
//            new MinSelectionSorter().sort(test);
//
//            // Test that sort worked
//            for (int i = 0; i < test.size() - 1; i++) {
//                if (test.get(i) > test.get(i + 1)) {
//                    algoWorks = false;
//                    break;
//                }
//            }
//        }
//        System.out.println("algo works: " + algoWorks);


        new MenuFrame().startApplication();
    }

}
