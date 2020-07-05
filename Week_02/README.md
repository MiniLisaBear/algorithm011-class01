学习笔记
## HashMap---jdk1.8 ##
**1、变量定义**

	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 
	static final int MAXIMUM_CAPACITY = 1 << 30;
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	static final int TREEIFY_THRESHOLD = 8;
	static final int UNTREEIFY_THRESHOLD = 6;
	static final int MIN_TREEIFY_CAPACITY = 64;
	
	transient Node<K,V>[] table;
	transient Set<Map.Entry<K,V>> entrySet;
	transient int size;
	transient int modCount;
	int threshold;
	final float loadFactor;

分别定义了默认的最大容量2^4=16，哈希表的最大长度2^30，默认的装载因子0.75f，树化阈值8（超过该值，把链表转换成红黑树），不树化阈值6（即当树中元素个数小于等于6时，红黑树转换为链表），最小树化容量64；哈希表、table数组存储Node对象（Node中包括hashcode，key，value，next），map中存放k-v对的个数size，modCount修改次数，threshold阈值当size>=threshold时对哈希表扩容，loadFactor装载因子。<br>
**(1)负载因子**<br>
hashmap给定的容量大小是固定的，如DEFAULT-INITIAL-CAPACITY=16，
默认负载因子是0.75，map在使用过程中不断存放数据，当数量达到16*0.75=12时，就需要将当前16的容量进行扩容，扩容操作涉及rehash、复制数据等。

**2、put(K key, V value)方法**

	public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

	final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0) /*1*/
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null) /*2*/
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash && /*3*/
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode) /*4*/
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) { /*5*/
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st /*6*/
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash && /*7*/
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key /*8*/
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold) /*9*/
            resize();
        afterNodeInsertion(evict);
        return null;
    }

注释1中，判断当前桶是否为空，空就需要初始化，在resize()进行扩容操作和判断是否进行初始化；<br>
注释2中，通过(n-1)&hash得到下标值，判断该位置当前是否有值，如果table[index]==null为true，表示没有值，则通过newNode方法在当前位置创建一个新桶；
注释3表示为当前有值，首先判断当前值是否等于新值（key、key的hashcode都要判断），满足则获取旧值赋值给e，在第8步统一进行赋值及返回。
不满足则进入注释4判断table[index]类型是否为TreeNode，满足则进行红黑树的插入，不满足则进入注释5；
1，2，3，4均不满足进入注释5遍历链表，判断链表中是否存在该值，存在就退出循环，否则直到p.next==null（p=table[index]），追加到末尾，然后判断此时链表长度是否大于TREEIFY-THRESHOLD，大于进行treeifyBin红黑树转换。
注释8，如果e!=null就相当于存在相同的key，那就将值覆盖。
注释9，最后判断k-v对数是否超过threshold，超过就再次扩容。<br>
**3、扩容操作**<br>
触发扩容：哈希table为Null或长度为0；Map中存储的k-v对数量超过了阈值
threshold；链表长度超过了TREEIFY-THRESHOLD(8)但是表长度小于MIN_TREEIFY-CAPACITY(64)。<br>
一般扩容分为两步，第一步对哈希表长度的扩展（2倍），第二步将旧table中的数据搬到新table上。<br>
**4、get(Object key)方法**<br>

	public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
	final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
根据key计算得到hash值，通过(n-1)&hash得到存储的下标，判断第一个key和hash值是不是等于查找的key和hash，若是直接返回第一个，不是就从下一个节点开始查找，判断第一个节点是不是TreeNode，是就去红黑树查找，不是就遍历链表查找。

## HashMap和HashTable的关系 ##
**（1）共同点**<br>
底层都用到了数组+链表的实现方式<br>
**（2）区别**<br>
1、hashmap在链表长度大于8时，转换成红黑树，因为当哈希冲突严重链表长度过长使得时间复杂度变成O(n)，改成红黑树之后，查询效率变成O(logn)。<br>
2、从继承和实现类和接口来看，hashmap继承了AbstractMap类，AbstractMap实现了Map接口，hashtable也实现了Map接口，还继承了一个Dictionary类，Dictionary类已经被弃用。<br>
3、hashtable是线程安全的，加了同步锁，hashmap线程不安全。<br>
4、初始值和扩容方式不同，hashtable初始值为11，扩容为原来的2d+1。容量大小都采用奇数且为素数，且采用取模法，这种方式散列更均匀。但对素数取模的性能较低（涉及除法运算），而hashmap长度都是2的幂次方，通过位运算(n-1)&hash获取位置下标，性能较好。<br>
5、hashmap的key和value都可以为null，且value可以多次为null，key多次为null时会覆盖；hashtable的key和value都不可以为null，会直接出现(NullPointException)。<br>
**（3）HashTable和HashMap的线程安全问题**<br>
能保证线程安全的方式有多个，如添加synchronized关键字，或使用lock机制。hashtable使用了synchronized关键字。<br>
put、get、remove、equals方法都使用了synchronized关键字修饰。<br>
这样做保证了hashtable对象的线程安全特性，但同样带来了效率低下的问题。synchronized关键字的特性，对于多线程共享的临界资源，同一时刻只能有一个线程占用，其他线程必须等待，当大量线程执行get操作，也必须等待一个一个处理，效率十分低下。get方法如果不加synchronized关键字，那么将会出现问题，如果A线程在进行put或remove操作，B、C线程都在进行get操作，那么如果哈希表已经被A线程更改了，那么B和C可能得到不一样的数据。<br>
hashMap线程不安全，hashmap效率低下，因此可以考虑使用ConcurrentHashMap。<br>
**（4）HashMap的线程不安全导致的问题**<br>
**数据覆盖**<br>
两个线程同时操作可能导致数据覆盖问题，如put方法，在jdk1.8中，当链表长度小于8时，新增都是接在最后一个节点的后面，因此需要找到p.next==null的节点，若此时有2个线程A,B同时进行put操作，且都获取到原链表的p.next==null节点，此时线程A的时间片用完，线程B将新数据插入完成put操作，此时轮到线程A，线程A具有的p还是原链表的p，并不是B新增的这个节点，若此时A也进行插入操作，就会抹掉B刚刚新增的数据，导致数据丢失。删除和修改同样会出现覆盖问题。<br>
**扩容导致死循环**<br>
只有JDK7及以前的版本会存在死循环现象，在JDK8中，resize()方式已经做了调整，使用两队链表，且都是使用的尾插法，即使多线程下，也顶多是从头结点再做一次尾插法，不会造成死循环。而JDK7能造成死循环，就是因为resize()时使用了头插法，将原本的顺序做了反转，才留下了死循环的机会<br>
**（5）如何规避HashMap的线程不安全**<br>
**将Map转为包装类**<br>

	Map<String, Integer> testMap = new HashMap<>();
	...
	// 转为线程安全的map
	Map<String, Integer> map = Collections.synchronizedMap(testMap);
**使用ConcurrentHashMap---推荐**<br>
	Map<String, Integer> susuMap = new ConcurrentHashMap<>();
	susuMap.put("susu1", 111);
	susuMap.put("susu2", 222);
	System.out.println(susuMap.get("susu1"));


## 参考 ##
1、作者：码不停蹄的小鼠松<br>
链接：https://juejin.im/post/5de85e05f265da33b50727f6




## 补充问题 ##
为什么哈希表的最大长度是1<<30(2^30)？<br>
因为int类型是4个字节，32位，最高位是符号位，所以只有31位，而2^31是最小的数-2147483648是负数，所以只能是2^30。<br>
补充：为什么整型范围是-2147483648~2147483647。因为int32位除去符号位是31位，所以最大的整数是0111 1111 1111 1111 1111 1111 1111 1111=2147483647；因为+0和-0原码分别为0000 0000 0000 0000 0000 0000 0000 0000，1000 0000 0000 0000 0000 0000 0000 0000，实际只需要1个0，所以把-0当作最小值即2^31=-2147483648。