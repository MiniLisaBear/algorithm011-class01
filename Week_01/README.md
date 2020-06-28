学习笔记
## 作业一：程序改写 ##
	Deque<String> deque = new LinkedList<String>();
    deque.addFirst("a");
    deque.addFirst("b");
    deque.addFirst("c");
	//deque.addLast("a");
	//deque.addLast("b");
	//deque.addLast("c");
    System.out.println(deque);

    String str = deque.peek();
    System.out.println(str);
    System.out.println(deque);

    while (deque.size() > 0){
       System.out.println(deque.pop());
    }
    System.out.println(deque);
pop()方法在addFirst()和addLast()方法下都是从第一个开始移出

## 作业二： Queue 和 Priority Queue 的源码 ##
**1、Queue源码分析<br>**
Queue是接口，继承Collection类，其中的一个实现类LinkedList中各个操作源码如下。<br>
**（1）boolean add(E e)方法<br>**

	public boolean add(E e) {
        linkLast(e);
        return true;
    }

	void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
Node类型在LinkedList中是双向链表，有前指针和后指针。<br>

	private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
新增的节点添加在linkedList的最后，需要先获得前一个节点即链表的最后一个节点last，将l指向最后一个节点所在位置，新增节点的pre设置为l，后继为null，将last指向新增节点的位置，如果得到的l为空，表明原链表为空，此时新增节点为第一个第一个节点，否则将l的后继指向新增的节点。记录链表中节点数量的size++，修改计数modCount++。最后，新增完成返回true。<br>
**（2）boolean offer(E e)方法<br>**

	public boolean offer(E e) {
        return add(e);
    }
	public boolean add(E e) {
        linkLast(e);
        return true;
    }
offer()方法，最终调用的也是LinkedList中的linkLast(E e)方法。<br>
**（3）E remove()方法<br>**

	public E remove() {
        return removeFirst();
    }
	public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }
	private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }
remove()方法移除的链表的第一个节点，LinkedList维护了一个first和last节点，直接获取first节点，如果为空抛出异常，否则调用unlinkFirst方法。在unlinkFirst方法中，声明了一个变量element保存了第一个节点的值，声明了一个Node型变量next获取f.next，然后将f的值和next都置为null，next置为null可以唤起gc。然后改变first指向的地址空间。如果链表中只有一个节点，那么next为空，此时将last也置为null，否则将next.pre指向null。同样size--,modCount++。最后返回移除的元素。<br>
**（4）E poll()方法<br>**

	public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

poll与remove的区别在于，poll失败返回null，remove失败直接抛出异常。poll方法也是调用的unlinkFirst方法。

**2、PriorityQueue 源码分析<br>**
PriorityQueue是类，继承于AbstractQueue类。<br>
**（1）boolean add(E e)方法<br>**

	 boolean add(E e) {
        return offer(e);
    }
	public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }
add方法调用了自己的offer方法，首先判断新增元素是否为空，空就抛出异常。然后modCount++，PriorityQueue自己维护了一个Object[]类型的queue队列和数组大小size，判断size是否大于quque的大小，如果是，通过grow方法扩大数组，否则将size+1。然后判断原始size是否为0，如果是，将新增的值存放在queue[0]位置，不是通过siftUp方法进行存储，最后返回true。<br>
**grow(int minCapacity)方法：**

	private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                         (oldCapacity + 2) :
                                         (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        queue = Arrays.copyOf(queue, newCapacity);
    }
grow方法传入的值为size+1。首先对oldCapacity（初始值为queue大小）进行扩充，扩充方式分为两种，如果原始容量oldCapacity<64(2^6)，就加2，否则增加oldCapacity的一半作为新的大小。然后判断得到的扩容后的新的大小newCapacity是否超过整型的最大大小-8（2^32-8，2147483640），如果超过了通过hugeCapacity处理得到最新的容量大小，然后通过Arrays.copyOf方法将原始queue的值拷贝值新的长度newCapacity的数组。<br>
**hugeCapacity(minCapacity)方法**

	private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
判断传入的值size+1是否超过整型的最大大小-8（2^32-8，2147483640），如果超过了就取最大大小(2^32)，否则用2^32-8。<br>
**siftUp(int k, E x)方法：**

	private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }
	private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }
	private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
comparator为自己重写的比较方法，如果有重写改方法，siftUpUsingComparator中按照重写方法指定的比较方式调整元素位置，如果没有重写，默认是小顶堆，将新加元素按照小顶堆的方式调整到指定位置。

**（2）boolean offer(E e)方法<br>**
也可以用于新增元素，add方法就是调用的offer方法

**（3）E peek()方法<br>**
	public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }
返回null或者数组的第一个元素

**（4）int indexOf(Object o)方法<br>**

	private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++)
                if (o.equals(queue[i]))
                    return i;
        }
        return -1;
    }
遍历数组，定位数组元素位置

**（4）boolean remove(Object o)方法<br>**

	public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1)
            return false;
        else {
            removeAt(i);
            return true;
        }
    }
首先定位待移除元素的位置，然后调用removeAt方法移除。
**removeAt(int i)**

	private E removeAt(int i) {
        // assert i >= 0 && i < size;
        modCount++;
        int s = --size;
        if (s == i) // removed last element
            queue[i] = null;
        else {
            E moved = (E) queue[s];
            queue[s] = null;
            siftDown(i, moved);
            if (queue[i] == moved) {
                siftUp(i, moved);
                if (queue[i] != moved)
                    return moved;
            }
        }
        return null;
    }
移除最后一个元素，直接将元素置为null。不是最后一个元素，移除的时候也要进行堆的维护，默认是小顶堆。
**siftDown(int k, E x)**

	private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }