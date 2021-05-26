package dk.sdu.mmmi.ogsiai;

import java.util.Comparator;


public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        return Float.compare(o1.getF(), o2.getF());
    }

}
