public class Main {
    public static void main(String[] args) {

        ListImpl<Integer> myList= new ListImpl<>();

        myList.add(1);
        myList.add(5);
        myList.add(10);

        myList.insert(1,2);
        myList.insert(2,3);

        myList.remove(1);

        System.out.println(myList);


        ListImpl<String> myStrList = new ListImpl<>();

        myStrList.add("aa");
        myStrList.add("cc");
        myStrList.add("ee");

        myStrList.insert(1,"bb");

        //исправить удаление элементов когда - длина листа равна длине массива

      //  myStrList.remove("cc");
        //myStrList.insert(3,"dd");

        System.out.println(myStrList);

        ListIteratorCopyArrImpl<String> listIteratorCopyArr = new ListIteratorCopyArrImpl<>(myStrList);

        System.out.println(listIteratorCopyArr.hasNext());
        System.out.println(listIteratorCopyArr.next());
        System.out.println(listIteratorCopyArr.next());
        System.out.println(listIteratorCopyArr.next());
        System.out.println(listIteratorCopyArr.next());
        System.out.println(listIteratorCopyArr.hasNext());
        System.out.println("==========================");


        //ListImpl.ListIteratorInnerClassImpl listIteratorInnerClass ;

        ListImpl<String>.ListIteratorInnerClassImpl listIteratorInnerClass = myStrList.new ListIteratorInnerClassImpl();


        System.out.println(listIteratorInnerClass.hasNext());
        System.out.println(listIteratorInnerClass.next());
        System.out.println(listIteratorInnerClass.next());
        System.out.println(listIteratorInnerClass.next());
        System.out.println(listIteratorInnerClass.next());
        System.out.println(listIteratorInnerClass.hasNext());

    }
}
