package com.freshbin.dataStructAndAlgo.chapter06.Link.LRU;

/**
 * 使用链表实现LRU缓存淘汰策略
 * @author freshbin
 * @date 2020/4/11 21:24
 */
public class MyLinkLRU {
    private volatile static MyLinkLRU myLinkLRU = null;
    private Integer maxSize = 3;
    private Integer currentCount = 0;
    public MyNode firstNode;

    private MyLinkLRU() {

    }

    public static synchronized MyLinkLRU getMyLinkLRU() {
        if(myLinkLRU == null) {
            synchronized (MyLinkLRU.class) {
                if(myLinkLRU == null) {
                    myLinkLRU = new MyLinkLRU();
                }
            }
        }

        return myLinkLRU;
    }

    /**
     * 0、判断缓存是否为空，如果为空，则直接插入头结点
     * 1、先查询该值是否在缓存中，如果存在，则将其从原来位置删除，并插入到头结点中
     * 2、如果该值不存在缓存中，则判断缓存是否满了，如果没满，那么就直接插入头结点，否则就删除尾结点，再插入头结点
     * @param token
     * @return
     */
    public MyNode useCacheByToken(String token) {
        MyNode cacheNode = new MyNode(token);

        // 0
        if(this.firstNode == null) {
            insertToFirstNode(cacheNode);
            return cacheNode;
        }

        // 1
        MyNode oldNode = this.firstNode;
        while (oldNode != null) {
            if(oldNode.token.equals(token)) {
                removeNodeByToken(token);
                insertToFirstNode(cacheNode);
                return firstNode;
            }
            oldNode = oldNode.next;
        }

        // 2
        if(currentCount < maxSize) {
            currentCount++;
            MyNode oldFirstNode = this.firstNode;
            this.firstNode = cacheNode;
            this.firstNode.next = oldFirstNode;
        } else {
            removeTailNode();
            insertToFirstNode(cacheNode);
        }

        return firstNode;
    }

    private void insertToFirstNode(MyNode newNode) {
//        if(newNode == null || newNode.token == this.firstNode.token) {
//            // 如果需要新插入的节点是头结点，那么不用再插入
//            return;
//        }
        currentCount++;
        MyNode node = firstNode;
        this.firstNode = newNode;
        this.firstNode.next = node;
    }

    public Boolean removeNodeByToken(String token) {
        if(this.firstNode == null) {
            return true;
        }
        if(currentCount > 0) {
            currentCount--;
        }
        if(this.firstNode.token == token) {
            // 删除头结点
            this.firstNode = this.firstNode.next;
            return true;
        }
        MyNode node = this.firstNode;
        while (node != null) {
            if(node.next != null && node.next.token == token) {
                node.next = node.next.next;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public Boolean removeTailNode() {
        if(this.firstNode == null) {
            return false;
        }

        MyNode preNode = firstNode;
        MyNode currentNode = firstNode;
        while (currentNode.next != null) {
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        preNode.next = null;
        currentCount--;
        return true;
    }

    /**
     * 每次都从头结点插入
     * @param value
     * @return
     */
    public MyNode addLink(String value) {
        MyNode myNode = new MyNode(value);
        MyNode oldFirstNode = this.firstNode;
        this.firstNode = myNode;
        this.firstNode.next = oldFirstNode;
        return this.firstNode;
    }

    public MyNode getFirstNode() {
        return this.firstNode;
    }

    public void setFirstNode(MyNode firstNode) {
        this.firstNode = firstNode;
    }

    public Integer getCurrentCount() {
        return this.currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }
}
