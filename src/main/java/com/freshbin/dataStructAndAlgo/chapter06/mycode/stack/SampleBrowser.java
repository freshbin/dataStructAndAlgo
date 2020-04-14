package com.freshbin.dataStructAndAlgo.chapter06.mycode.stack;

/**
 * 用栈实现浏览器前进后退效果
 * 一开始写得十分乱，而且还有问题，后来就瞄了一下大佬们的答案了
 * @author freshbin
 * @date 2020/4/14 10:37
 */
public class SampleBrowser {

    private String currentUrl;
    private StackBasedOnLinkedList backwardStack;
    private StackBasedOnLinkedList forwardStack;

    public SampleBrowser() {
        backwardStack = new StackBasedOnLinkedList();
        forwardStack = new StackBasedOnLinkedList();
    }

    public String openUrl(String openUrl) {
        if(this.currentUrl != null) {
            this.backwardStack.push(this.currentUrl);
            this.forwardStack.clear();
        }

        this.currentUrl = openUrl;
        showUrl("openUrl");
        return openUrl;
    }

    public String forwardUrl() {
        if(canForwardUrl()) {
            String forwardUrl = this.forwardStack.pop();
            this.backwardStack.push(this.currentUrl);
            this.currentUrl = forwardUrl;
            showUrl("forwardUrl");
        }
        return this.currentUrl;
    }

    public Boolean canForwardUrl() {
        if(this.forwardStack.getTop() != null) {
            return true;
        }
        System.out.println("不能前进！");
        return false;
    }

    public String backwardUrl() {
        if(canBackwardUrl()) {
            String backwardUrl = this.backwardStack.pop();
            this.forwardStack.push(this.currentUrl);
            this.currentUrl = backwardUrl;
            showUrl("backwardUrl");
        }
        return this.currentUrl;
    }

    public Boolean canBackwardUrl() {
        if(this.backwardStack.getTop() != null) {
            return true;
        }
        System.out.println("不能后退！");
        return false;
    }

    public void showUrl(String opt) {
        System.out.println(opt + ":" + this.currentUrl);
    }

    public String getCurrentUrl() {
        System.out.println("当前url:" + this.currentUrl);
        return this.currentUrl;
    }

    public static void main(String[] arg) {
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        SampleBrowser sampleBrowser = new SampleBrowser();
        String currentUrl = null;

        // 测试步骤
        // 1、依次点击：前进，后退，新页面a
        // 期望值：不允许前进；不允许后退；显示新页面a
        System.out.println("=======1======");
        currentUrl = sampleBrowser.forwardUrl();
        currentUrl = sampleBrowser.backwardUrl();
        currentUrl = sampleBrowser.openUrl(a);

        System.out.println("=======2======");
        // 2、依次点击：前进，后退，新页面b
        // 期望值：不允许前进；不允许后退；显示新页面b
        currentUrl = sampleBrowser.forwardUrl();
        currentUrl = sampleBrowser.backwardUrl();
        currentUrl = sampleBrowser.openUrl(b);

        System.out.println("======3=======");
        // 3、依次点击：前进，后退，新页面c
        // 期望值：不允许前进；允许后退并显示后退的页面a；显示新页面c
        currentUrl = sampleBrowser.forwardUrl();
        currentUrl = sampleBrowser.backwardUrl();
        currentUrl = sampleBrowser.openUrl(c);

        System.out.println("======4=======");
        // 4、依次点击：后退，前进，新页面d
        // 期望值：允许后退并显示后退的页面a；允许前进显示新页面c；显示新页面d
        currentUrl = sampleBrowser.backwardUrl();
        currentUrl = sampleBrowser.forwardUrl();
        currentUrl = sampleBrowser.openUrl(d);

        System.out.println("======5=======");
        // 5、依次点击：前进，后退，后退，后退
        // 期望值：不允许前进，后退到页面c，页面a，不允许后退。
        currentUrl = sampleBrowser.forwardUrl();
        currentUrl = sampleBrowser.backwardUrl();
        currentUrl = sampleBrowser.backwardUrl();
        currentUrl = sampleBrowser.backwardUrl();

        System.out.println("=======显示最后的页面=======");
        sampleBrowser.getCurrentUrl();
    }
}
