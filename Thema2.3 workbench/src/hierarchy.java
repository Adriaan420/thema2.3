import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

/**
 * Created by Danny on 19-2-2016.
 */
public class hierarchy extends JApplet{
    public hierarchy() {
        DefaultMutableTreeNode root, employee, customer, us_customer;

        employee = new DefaultMutableTreeNode("employee");
        employee.add(new DefaultMutableTreeNode("sales_rep"));
        employee.add(new DefaultMutableTreeNode("engineer"));

        customer = new DefaultMutableTreeNode("customer");
        us_customer = new DefaultMutableTreeNode("us_customer");
        us_customer.add(new DefaultMutableTreeNode("local_customers"));
        us_customer.add(new DefaultMutableTreeNode("regional_customers"));
        customer.add(us_customer);
        customer.add(new DefaultMutableTreeNode("non_us_customer"));


        root = new DefaultMutableTreeNode("person");
        root.add(employee);
        root.add(customer);
        /*
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        //panel.add(new JScrollPane(new JTree(root)));
        panel.add(new JScrollPane(new JTree(new DefaultTreeModel(root))));

        JTextArea info = new JTextArea();
        info.setWrapStyleWord(true);
        info.setLineWrap(true);
        add(new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, new JScrollPane(info)), BorderLayout.CENTER); */

        String breadthFirstSearchResult="";
        String preorderSearchResult="";
        String postorderSearchResult="";

        Enumeration bf = root.breadthFirstEnumeration() ;
        while (bf.hasMoreElements())
            breadthFirstSearchResult += bf.nextElement().toString() + " ";
        System.out.println("\nBreath-first traversal: " + breadthFirstSearchResult);

        Enumeration pre = root.preorderEnumeration();
        while(pre.hasMoreElements())
            preorderSearchResult += pre.nextElement().toString() + " ";
        System.out.println("\nPreorder traversal: " + preorderSearchResult);

        Enumeration post = root.postorderEnumeration();
        while(post.hasMoreElements())
            postorderSearchResult += post.nextElement().toString() + " ";
        System.out.println("\nPostorder traversal: " + postorderSearchResult);
    }
}
