package com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.exam;

import com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.LRU.MyNode;
import com.freshbin.dataStructAndAlgo.chapter06.mycode.Link.palindromicString.MyLink;

/**
 * @author freshbin
 * @date 2020/4/12 15:34
 */
public class MyLinkExam {
    /**
     * 单链表方式实现翻转单链表
     * 思路：需要添加一个前节点用于辅助
     * 将当前节点的下一节点指向前节点，将前节点和当前节点都往后移动。
     * @param node
     * @return
     */
    public static MyNode reversalLink(MyNode node) {
        if(node == null || node.next == null) {
            return node;
        }

        MyNode preNode = null;
        MyNode currentNode = node;
        MyNode nextNode = null;

        while(currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }

        return preNode;
    }

    /**
     * 检测链表中是否有环的操作
     * 一开始我连这题目是什么意思都不知道，后来搜了一下，才恍然大悟，下面列一下大神们的解法，
     * 图解见此大佬的知乎回答https://zhuanlan.zhihu.com/p/103626709
     * 思路：设置一快一慢指针，快指针是慢指针的两倍。当快指针与慢指针能相遇的时候，说明有环，该点就是链表的环内节点，反之没有环
     * 当快慢指针相遇之后，将两个指针分别重新定位到头结点和相遇节点，两个指针继续往前走，这次都一步一步的走
     * 当两个指针再次相遇的时候，这个点就是环入口节点
     *
     * @param pHead
     * @return
     */
    public static MyNode getLinkRingNode(MyNode pHead) {
        if(pHead == null || pHead.next == null || pHead.next.next == null) {
            return null;
        }

        MyNode ringNode = null;
        MyNode slowNode = pHead.next;
        MyNode fastNode = pHead.next.next;

        while(slowNode != fastNode) {
            if(fastNode == null || fastNode.next == null || fastNode.next.next == null) {
                // 快指针一定走得比慢指针快，所以这里只要判断快指针为空的话，那么就说明链表中没有环，直接退出
                return null;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        // 执行到这里， 说明链表有环，而且快慢指针相遇了，这时候，分别两个指针放到头结点和相遇节点，继续往前走
        slowNode = pHead;
        while(slowNode != fastNode) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }

        // 退出循环执行到这里，说明已经在环节点相遇了,直接赋值返回即可
        ringNode = slowNode;
        return ringNode;
    }

    /**
     * 合并两个有序链表
     * 思路：1、当两个链表都不为空，首先遍历链表1，如果值小于等于链表2当前节点值，那么就将链表遍历写入新链表
     * 2、遍历链表2，如果值小于等于链表1的当前节点值，那么就写入新链表
     * 3、继续步骤1
     * 4、遍历链表1，将值全部写入新链表，遍历链表2，将值全部写入新链表
     * @param p1Node
     * @param p2Node
     * @return
     */
    public static MyNode mergeOrderLink(MyNode p1Node, MyNode p2Node) {
        MyNode pHeadNode = null;
        MyNode currentNode = null;

        if(p1Node == null) {
            return p2Node;
        }
        if(p2Node == null) {
            return p1Node;
        }
        if(Integer.valueOf(p1Node.token) <= Integer.valueOf(p2Node.token)) {
            pHeadNode = new MyNode(p1Node.token);
            p1Node = p1Node.next;
        } else {
            pHeadNode = new MyNode(p2Node.token);
            p2Node = p2Node.next;
        }
        MyNode returnNode = pHeadNode;

        while(p1Node != null && p2Node != null) {
            while(p1Node != null && p2Node != null && Integer.valueOf(p1Node.token) <= Integer.valueOf(p2Node.token)) {
                currentNode = new MyNode(p1Node.token);
                pHeadNode.next = currentNode;
                pHeadNode = pHeadNode.next;
                p1Node = p1Node.next;
            }
            while(p1Node != null && p2Node != null && Integer.valueOf(p2Node.token) <= Integer.valueOf(p1Node.token)) {
                currentNode = new MyNode(p2Node.token);
                pHeadNode.next = currentNode;
                pHeadNode = pHeadNode.next;
                p2Node = p2Node.next;
            }
        }

        while(p1Node != null) {
            currentNode = new MyNode(p1Node.token);
            pHeadNode.next = currentNode;
            pHeadNode = pHeadNode.next;
            p1Node = p1Node.next;
        }

        while(p2Node != null) {
            currentNode = new MyNode(p2Node.token);
            pHeadNode.next = currentNode;
            pHeadNode = pHeadNode.next;
            p2Node = p2Node.next;
        }

        return returnNode ;
    }

    /**
     * 删除链表倒数第 n 个结点
     * 先遍历一遍链表，记录链表总长度，求倒数第n个，就是求链表总数减去倒数第n个之后的值
     * @param pHeadNode
     * @return
     */
    public static MyNode deleteBackwardNode(MyNode pHeadNode, int deleteIndex) {
        if(pHeadNode == null || deleteIndex < 1) {
            return null;
        }

        MyNode countLengthNode = pHeadNode;
        int length = 0;
        int targetIndex = 0;
        while (countLengthNode != null) {
            length++;
            countLengthNode = countLengthNode.next;
        }
        if(deleteIndex > length) {
            return null;
        }

        targetIndex = length - deleteIndex;
        if(targetIndex == 0) {
            // 如果是删除头结点，这里直接把头结点删除，这样下面的代码逻辑会比较好操作
            pHeadNode = pHeadNode.next;
            return pHeadNode;
        }

        // 当前链表从1开始，因为头结点在上面已经判断过了
        int currentIndex = 1;
        MyNode nextNode = pHeadNode.next;
        MyNode preNode = pHeadNode;
        while (nextNode != null) {
            if(targetIndex == currentIndex) {
                System.out.println("需要删除的节点值：" + nextNode.token);
                preNode.next = nextNode.next;
                return pHeadNode;
            }
            currentIndex++;
            preNode = nextNode;
            nextNode = nextNode.next;
        }

        return pHeadNode;
    }

    /**
     * 求链表的中间结点
     * 思路：用快慢指针，快指针每次步长为2，慢指针步长为1
     * 快慢指针一直前进，判断快指针的下一个节点是否为空，
     * 如果为空，说明到尾节点了，而且该链表是双数链表，中间节点有两个，就是当前慢指针的节点和下一节点
     * 如果不为空，快慢指针继续前进，如果退出循环，那么表明该链表为单数链表，中间节点为慢指针当前节点
     *
     * @param pHeadNode
     * @return
     */
    public static MyNode getMiddleNode(MyNode pHeadNode) {
        if(pHeadNode == null) {
            return pHeadNode;
        }

        MyNode deleteNode = null;
        MyNode slowNode = pHeadNode;
        MyNode fastNode = pHeadNode.next;
        MyNode middleNode = null;

        while(fastNode != null) {
            if(fastNode.next == null) {
                // 说明是双数链表，中间节点为慢指针和其下一节点
                middleNode = slowNode;
                if(middleNode.next != null && middleNode.next.next != null) {
                    middleNode.next.next = null;
                }
                return middleNode;
            }

            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        // 退出循环，说明是单数链表，中间节点为当前慢节点
        middleNode = slowNode;
        middleNode.next = null;

        return middleNode;
    }

    public static void main(String[] arg) {
        // 链表反转
        String[] reversalLinkTestData = new String[]{"a", "b", "c", "d", "e"};
//        reversalLinkTest(reversalLinkTestData);

        String[] ringLinkData = new String[]{"a", "b", "c", "d", "b"};
        // 获取链表中的环入口节点
//        getLinkRingNodeTest(ringLinkData);

        // 合并两个有序链表
        String[] orderlyLink01 = new String[]{"1", "3", "5", "7", "9"};
        String[] orderlyLink02 = new String[]{"0", "2", "4", "6", "8"};
//        mergeOrderLinkTest(orderlyLink01, orderlyLink02);

        // 删除链表的倒数第n个节点
        String[] deleteLinkData = new String[]{"1", "2", "3", "4", "5"};
//        deleteBackwardNodeTest(deleteLinkData, 4);

        // 求链表的中间结点
//        String[] getMiddleNodeData = new String[]{"1"};
//        String[] getMiddleNodeData = new String[]{"1", "2"};
//        String[] getMiddleNodeData = new String[]{"1", "2", "3"};
        String[] getMiddleNodeData = new String[]{"1", "2", "3", "4"};
        getMiddleNodeTest(getMiddleNodeData);
    }

    private static void getMiddleNodeTest(String[] getMiddleNodeData) {
        MyLink myLink = initLinkFromTail(getMiddleNodeData);

        MyNode middleNode = getMiddleNode(myLink.firstNode);
        System.out.println("中间节点值为：");
        while (middleNode != null) {
            System.out.print(middleNode.token + "->");
            middleNode = middleNode.next;
        }

    }

    private static void deleteBackwardNodeTest(String[] deleteLinkData, int deleteBackwardIndex) {
        MyLink myLink = initLinkFromTail(deleteLinkData);

        MyNode pHeadNode = deleteBackwardNode(myLink.firstNode, deleteBackwardIndex);
        displayLink(pHeadNode);
    }

    private static void mergeOrderLinkTest(String[] orderlyLink01, String[] orderlyLink02) {
        MyLink p1Link = initLinkFromTail(orderlyLink01);
        MyLink p2Link = initLinkFromTail(orderlyLink02);

        MyNode pHeadNode = mergeOrderLink(p1Link.firstNode, p2Link.firstNode);
        displayLink(pHeadNode);
    }

    private static void displayLink(MyNode pHeadNode) {
        System.out.println("链表数据：");
        while (pHeadNode != null) {
            System.out.print(pHeadNode.token + "->");
            pHeadNode = pHeadNode.next;
        }
        System.out.println();
    }

    private static void getLinkRingNodeTest(String[] ringLinkData) {
        MyNode pHeadNode = new MyNode("a");
        MyNode bNode = new MyNode("b");
        MyNode cNode = new MyNode("c");
        MyNode dNode = new MyNode("d");

        pHeadNode.next = bNode;
        bNode.next = cNode;
        cNode.next = dNode;
        dNode.next = bNode;

        MyNode ringNode = getLinkRingNode(pHeadNode);
        System.out.println("环入口节点的值为：" + ringNode.token);
    }

    private static void reversalLinkTest(String[] reversalLinkTestData) {
        MyLink myLink = initLinkFromHead(reversalLinkTestData);

        System.out.print("原链表数据:");
        MyNode oldLink = myLink.firstNode;
        while(oldLink != null) {
            System.out.print(oldLink.token + "->");
            oldLink = oldLink.next;
        }

        System.out.println();
        MyNode myNode = reversalLink(myLink.firstNode);
        System.out.print("新链表数据:");
        while(myNode != null) {
            System.out.print(myNode.token + "->");
            myNode = myNode.next;
        }
    }

    public static MyLink initLinkFromHead(String[] data) {
        MyLink myLink = new MyLink();
        for(int i = 0; i < data.length; i++) {
            myLink.addLink(data[i]);
        }
        return myLink;
    }

    public static MyLink initLinkFromTail(String[] data) {
        MyLink myLink = new MyLink();
        for(int i = 0; i < data.length; i++) {
            myLink.addTailLink(data[i]);
        }
        return myLink;
    }
}
