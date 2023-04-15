public class Main {
    public static void main(String[] args) {
        IntegerPair integerPair1=new IntegerPair();
        integerPair1.setFirst(1);
        integerPair1.setSecond(2);

        IntegerPair integerPair2= new IntegerPair();
        integerPair2.setFirst(300);
        integerPair2.setSecond(1212);

        IntegerPair integerPair3= new IntegerPair();
        integerPair3.setFirst(Integer.MAX_VALUE);
        integerPair3.setSecond(Integer.MAX_VALUE);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE +Integer.MAX_VALUE*32);
        // System.out.println(integerPair1.compareTo(integerPair2));
        System.out.println(integerPair3.hashCode());


        System.out.println(integerPair2.compareTo(integerPair3));

    }

    public static class IntegerPair implements Comparable<IntegerPair> {

        private int first;

        private int second;

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            // if(first>)
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public  boolean equals(IntegerPair integerPair){
            return this.first == integerPair.getFirst() && this.second == integerPair.getSecond();
        }

        @Override
        public int hashCode() {

            if(first>first+31*second || second>first+31*second){

                System.out.println("overflow sum");
                return 0;
            }
            else
                return first+31*second;
            // return integerPair.getFirst() + 31 * integerPair.getSecond();
        }


        @Override
        public int compareTo(IntegerPair o) {

            if(first>first+second || second>first+second
                    || o.getFirst()> o.getFirst()+o.getSecond()
                    ||o.getSecond()>o.getFirst()+o.getSecond()
            )
            {

                System.out.println("overflow sum");
                return 0;
            }
            return Integer.compare(this.first+this.second, o.getFirst()+o.getSecond());
        }


        // Write your code here.
    }



}
