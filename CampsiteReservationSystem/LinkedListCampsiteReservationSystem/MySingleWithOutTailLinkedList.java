package FinalProject;

import java.io.Serializable;

/**
 * Linked List that contains RV's and Tents for a Campsite
 * reservation System and relevant functions
 * @author Seth Ockerman
 * @author Max Foreback
 */
public class  MySingleWithOutTailLinkedList implements Serializable {
    // node at the head (top) of the list
    private Node top;

    /**
     * the size of the linked list as an int
     */
    public int size;

    /**
     * Linked list constructor
     */
    public MySingleWithOutTailLinkedList() {
        top = null;
        size = 0;
    }

    /**
     * returns size of list
     * @return size of list as an int
     */
    public int size() {
        return size;
    }

    /**
     * clears the list
     */
    public void clear() {
        top = null;
        size = 0;
    }

    /**
     * adds a campsite to the link list
     * @param s the campsite to be added
     */
    public void add(CampSite s) {

        // general node to be added and trackers
        Node campsiteNode = new Node(s, null);
        Node before = null;

        // make sure the campsite passed in wasn't null
        if (campsiteNode != null) {
            if (campsiteNode.getData() != null) {
                // if the date in the node is invalid or null
                if (campsiteNode.getData().getEstimatedCheckOut() == null) {
                    throw new NullPointerException("Null date entered for campsite. Create new campsite");
                }
                // case for if it is the fist element
                else if (this.size == 0) {
                    // top is the node campsite
                    this.top = campsiteNode;
                    //increase size of list to reflect changes
                    ++this.size;
                }
                // case for general elements
                //sorts by tent than RV. Within sub, by checkout date
                else if (this.size > 0) {
                    // start at the top by setting x equal to it
                    Node x = top;
                    // variable for index. While loop works better than for
                    int i = 0;

                    // if its an RV keep going till you find a RV
                    if (s instanceof RV) {
                        while (i <= size) {

                            // if null, you reached the end of the list. Add in
                            if (x == null) {
                                before.setNext(campsiteNode);
                                i++;
                            }
                            // if x is a tent, move forward in the list
                            else if (x.getData() instanceof TentOnly) {
                                before = x;
                                x = x.getNext();
                                ++i;
                            }

                            // if x is an RV, check its estimated checkout date
                            else if (x.getData() instanceof RV) {

                                // if x's date is before s's date, keep going
                                if (x.getData().getEstimatedCheckOut().compareTo(s.getEstimatedCheckOut()) < 0) {
                                    before = x;
                                    x = x.getNext();
                                    ++i;

                                }

                                // They are equal
                                else if (x.getData().getEstimatedCheckOut().compareTo(s.getEstimatedCheckOut()) == 0) {
                                    //If name is less or name is equal, move on
                                    if(x.getData().getGuestName().compareTo(s.getGuestName()) <= 0){
                                        before = x;
                                        x = x.getNext();
                                        ++i;
                                    }
                                    //If name is greater, insert
                                    else if(x.getData().getGuestName().compareTo(s.getGuestName()) > 0){
                                        if (i == size - 1 && size > 0) {

                                            //general if it needs to go before
                                            if(size > 1){
                                                before.setNext(campsiteNode);
                                                ++i;
                                            }
                                            // its the new top and needs to go before
                                            else if(size == 1){
                                                campsiteNode.setNext(top);
                                                top = campsiteNode;
                                                i = size + 1;
                                            }
                                        }

                                        // case for it is the start
                                        else if (i == 0 && size > 0) {
                                            this.top = campsiteNode;
                                            campsiteNode.setNext(x);
                                            // making sure it stops
                                            i = size + 1;
                                        }

                                        // general case
                                        else if (i > 0 && i <= size) {
                                            before.setNext(campsiteNode);
                                            campsiteNode.setNext(x);
                                            ++i;
                                        }
                                    }
                                }
                                // if x's date is after s's date, stop. Insert into list
                                else if (x.getData().getEstimatedCheckOut().compareTo(s.getEstimatedCheckOut()) > 0) {
                                    // case for if it is the end
                                    if (i == size - 1 && size > 0) {
                                        //general if it is needs to go before
                                        if(size > 1){
                                            before.setNext(campsiteNode);
                                            ++i;
                                        }
                                        // its the new top and needs to go before
                                        else if(size == 1){
                                            campsiteNode.setNext(top);
                                            top = campsiteNode;
                                            // making sure it stops
                                            i = size + 1;
                                        }
                                    }

                                    // case for it is the start
                                    else if (i == 0 && size > 0) {
                                        this.top = campsiteNode;
                                        campsiteNode.setNext(x);
                                        // making sure it stops
                                        i = size + 1;
                                    }

                                    // general case
                                    else if (i > 0 && i <= size) {
                                        before.setNext(campsiteNode);
                                        campsiteNode.setNext(x);
                                        ++i;
                                    }
                                }
                            }
                        }
                        ++size;
                    }
                    // if it is a tent, stop and sort among tents
                    else if (s instanceof TentOnly) {
                        while (i <= size) {


                            // if null, you reached the end of the list. Add in
                            if (x == null) {
                                before.setNext(campsiteNode);
                                i++;
                            }

                            // if x is a RV, place tent before x
                            else if (x.getData() instanceof RV) {

                                // general past 1st index
                                if (before != null) {
                                    before.setNext(campsiteNode);
                                    campsiteNode.setNext(x);
                                    ++i;
                                }
                                //  it is becoming the new head
                                else {
                                    top = campsiteNode;
                                    campsiteNode.setNext(x);
                                    i = size + 1;
                                }
                            }

                            // if x is an Tent, check its estimated checkout date
                            else if (x.getData() instanceof TentOnly) {
                                // if x's date is before s's date, keep going
                                if (x.getData().getEstimatedCheckOut().compareTo(s.getEstimatedCheckOut()) < 0) {
                                    before = x;
                                    x = x.getNext();
                                    ++i;

                                }

                                //dates are equal
                                else if (x.getData().getEstimatedCheckOut().compareTo(s.getEstimatedCheckOut()) == 0) {
                                    //if name is less or it is equal, continue
                                    if(x.getData().getGuestName().compareTo(s.getGuestName()) <= 0){
                                        before = x;
                                        x = x.getNext();
                                        ++i;
                                    }
                                    //insert if name is greater
                                    else if(x.getData().getGuestName().compareTo(s.getGuestName()) > 0){

                                        // case for if it is the end
                                        if (i == size - 1 && size > 0) {
                                            if(size > 1) {
                                                before.setNext(campsiteNode);
                                                ++i;
                                            }
                                            // its the new top
                                            else if(size == 1){
                                                campsiteNode.setNext(top);
                                                top = campsiteNode;
                                                i = size + 1;
                                            }
                                        }

                                        // case for it is the start
                                        else if (i == 0 && size > 0) {
                                            this.top = campsiteNode;
                                            campsiteNode.setNext(x);
                                            // making sure it stops
                                            i = size + 1;

                                        }

                                        // general case
                                        else if (i > 0 && i <= size) {
                                            before.setNext(campsiteNode);
                                            campsiteNode.setNext(x);
                                            ++i;

                                        }
                                    }
                                }
                                // if x's date is after s's date, stop. Insert into list
                                else if (x.getData().getEstimatedCheckOut().compareTo(s.getEstimatedCheckOut()) > 0) {

                                    // case for if it is the end
                                    if (i == size - 1 && size > 0) {
                                        if(size > 1) {
                                            before.setNext(campsiteNode);
                                            ++i;
                                        }
                                        // its the new top
                                        else if(size == 1){
                                           campsiteNode.setNext(top);
                                           top = campsiteNode;
                                           // make sure it exits the loop
                                           i = size + 1;
                                        }
                                    }

                                    // case for it is the start
                                    else if (i == 0 && size > 0) {
                                        this.top = campsiteNode;
                                        campsiteNode.setNext(x);
                                        // making sure it stops
                                        i = size + 1;

                                    }

                                    // general case
                                    else if (i > 0 && i <= size) {
                                            before.setNext(campsiteNode);
                                            campsiteNode.setNext(x);
                                            ++i;

                                    }
                                }
                            }
                        }
                        ++size;
                    }

                }
            }
            if(campsiteNode.getData() == null){
                throw new NullPointerException("Data is null for campsite: try again");
            }
        }
    }

    /**
     * removes campsite at specified index
     * @param index the index of the desired element
     * @return the campsite at the index you want removed
     */
    public CampSite remove(int index) {
       // target and tracker node
        Node before = null;
        Node target = null;

        //if out of index bounds
        if(index < 0 || index > size -1 ){
            throw new IndexOutOfBoundsException("Invalid removal index for list of size " + size);
        }
        // series of if statements; only one triggers to ensure O(n) work approach
        // if size is bigger than one
        if(size > 2 && index != 0) {
             before = this.getNode(index - 1);
             target = before.getNext();
        }

        // removing first with size 2
        else if(size == 2 && index == 0){
            target = this.getNode(0);
            top = target.getNext();
            --size;
            return target.getData();
        }
        // removing last with size 2
        else if(size == 2 && index == 1){
            target = top.getNext();
            this.top.setNext(null);
            --size;
            return target.getData();
        }
        // only one element or first; don't need before tracker
        else if( size == 1 || index == 0){
            target = this.getNode(index);
        }
        // handling first with lists > 1
        if(index == 0 && size > 1){
            this.top = target.getNext();
            --size;
            return target.getData();
        }
        //handling last with lists > 1
        else if(index == this.size -1 && size > 1){
            before.setNext(null);
            --size;
            return target.getData();
        }
        // with lists of size 1
        else if(index == 0 && size == 1){
            top = null;
            --size;
        }
        // general cases
        else if(index > 0 && index < size -1){
            before.setNext(target.getNext());
            --size;
            return target.getData();
        }
        if(target == null){
            return null;
        }
        else{
            return target.getData();
        }
    }


    /**
     * gets the campsite at the specified index
     * @param index the index of the desired element
     * @return the campsite at that index
     */
    public CampSite get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for list of size " + size);
        }
        else {
            int i = 0;
            Node cur = top;
            while (i < index) {
                cur = cur.getNext();
                i++;
            }
            return cur.getData();
        }
    }



    /**
     * gets the node at the specified position in the list
     * @param index the index of the desired element
     * @return the node at that index
     */
    public Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for list of size " + size);
        }
        else {
            int i = 0;
            Node cur = top;
            while (i < index) {
                cur = cur.getNext();
                i++;
            }
            return cur;
        }
    }

    /**
     * displays linked lists
     */
    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    /**
     * prints out the linked list
     * @return  a string representation of the linked list
     */
    @Override
    public String toString() {
        return "MySingleWithOutTailLinkedList{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }
}
