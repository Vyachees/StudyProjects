package MyApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyAppTest {

    @Test
    void searchInGoodTree() {
        MyApp.Node treeNode = new MyApp.Node(50);
        MyApp.Node node =  createGoodTree(treeNode);
        assertEquals(40,MyApp.search(40,node).key);
        assertEquals(70,MyApp.search(70,node).key);
        assertNull(MyApp.search(10,node));

        MyApp.Node node1= MyApp.search(70,node);
        node1.right= new MyApp.Node(71);
        assertEquals(71, MyApp.search(71,node).key);

    }

    @Test
    void searchInBadTree(){
        MyApp.Node treeNode = new MyApp.Node(50);
        MyApp.Node node =  createBadTree(treeNode);
        assertNull(MyApp.search(40,node));
        assertNull(MyApp.search(60,node));
        assertNull(MyApp.search(30,node));
        assertNull(MyApp.search(55,node));
    }

    @Test
    void searchInOneNodeTree(){
        MyApp.Node treeNode = new MyApp.Node(50);
        assertEquals(treeNode,MyApp.search(50,treeNode));
        assertNull(MyApp.search(11,treeNode));
    }

    @Test
    void searchInNullTree(){
        MyApp.Node treeNode = null;
        assertNull(MyApp.search(11,treeNode));
    }





    MyApp.Node createGoodTree(MyApp.Node node){
        /*
         *       50
         *   40       60
         * 30  45   55   70
         * */
        node.left= new MyApp.Node(40);
        node.right=new MyApp.Node(60);
        node.left.left = new MyApp.Node(30);
        node.left.right= new MyApp.Node(45);
        node.right.left=new MyApp.Node(55);
        node.right.right=new MyApp.Node(70);
        return node;
    }

    MyApp.Node createBadTree(MyApp.Node node){
        /*
         *       50
         *  60       40
         *55   70   30  45
         * */
        node.left= new MyApp.Node(60);
        node.right=new MyApp.Node(40);
        node.left.left = new MyApp.Node(55);
        node.left.right= new MyApp.Node(70);
        node.right.left=new MyApp.Node(30);
        node.right.right=new MyApp.Node(45);
        return node;
    }




}
