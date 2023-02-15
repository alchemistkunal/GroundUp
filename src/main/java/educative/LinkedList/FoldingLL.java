package educative.LinkedList;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
class LinkedListNode {
    public int data;
    public LinkedListNode next;
    // Constructor will be used to make a LinkedListNode type object
    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }

    public LinkedListNode head;

    // insertNodeAtHead method will insert a LinkedListNode at head
    // of a linked list.
    public void insertNodeAtHead(LinkedListNode node) {
        if (this.head == null) {
            this.head = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
    }
    // createLinkedList method will create the linked list using the
    // given integer array with the help of InsertAthead method.
    public void createLinkedList(int[] lst) {
        for (int i = lst.length - 1; i >= 0; i--) {
            LinkedListNode newNode = new LinkedListNode(lst[i]);
            insertNodeAtHead(newNode);
        }
    }


}

public class FoldingLL {

    public static String printList(LinkedListNode node) {
        String returnVal = "";
        while(node!=null){
            returnVal += node.data + " -> ";
            node = node.next;
        }
        return returnVal+"null";
    }
    public static void insert(LinkedListNode target, LinkedListNode source){
        LinkedListNode nextSource=null, next, sourceIterate = source;

        while(target != null) {
//            System.out.println(target.data);
            next = target.next;
            target.next = sourceIterate;

            if(sourceIterate!=null) {
                nextSource = sourceIterate.next;
                sourceIterate.next = next;
                sourceIterate = nextSource;
            }
            target = next;
        }

    }
    public static LinkedListNode reverseList(LinkedListNode head){
        LinkedListNode pre = null, curr=head, next;
        while(curr != null){
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static LinkedListNode reorderList(LinkedListNode head) {

        LinkedListNode midPointer=null, fastPointer=head;
        while(fastPointer!=null){
            midPointer = midPointer == null ? head : midPointer.next;
            fastPointer = fastPointer.next;
            fastPointer = (fastPointer != null) ? fastPointer.next : fastPointer ;
        }

//        System.out.println(printList(midPointer));
        midPointer = reverseList(midPointer);
//        System.out.println(printList(head));
//        System.out.println(printList(midPointer));

        insert(head, midPointer);

        return head;
    }

    public static final List<Integer[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<>(){{
        add(new Integer[]{1, 1, 2, 2, 3, -1, 10, 12});      // output: [1, 12, 1, 10, 2, -1, 2, 3]
        add(new Integer[]{10, 20, -22, 21, -12});      // output: [10, -12, 20, 21, -22]


    }});

    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        LinkedListNode node = new LinkedListNode(1);
        for (var rowData : INPUT_DATA.subList(0,2)) {
            node.createLinkedList(Arrays.stream(rowData).mapToInt(Integer::intValue).toArray());
            var result = reorderList(node.head);
            System.out.println(String.format("For Input v1: %s , result is %s ", Arrays.toString(rowData), printList(result)));//
        }

    }


}
