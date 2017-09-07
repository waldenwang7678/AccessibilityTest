package com.yonyou.accessibilityservicetest;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by wangjt on 2017/9/7.
 * 监听包安装器的安装页面, 监听到就自动安装
 */

public class WindowAccessibilitySeivice extends AccessibilityService {

    String installPackge[] = {"com.android.packageinstaller"};

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        //可用代码配置当前Service的信息
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();

        info.packageNames = installPackge; //监听过滤的包名
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; //监听哪些行为
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN; //反馈
        info.notificationTimeout = 100; //通知的时间

        setServiceInfo(info);
    }

    /**
     * 查找当前窗口中包含某文字的按钮
     *
     * @param text
     */
    @SuppressLint("NewApi")
    private void findAndPerformAction(String text) {

        if (getRootInActiveWindow() == null) {
            return;
        }

        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for (int i = 0; i < nodes.size(); i++) {
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行按钮点击行为
            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getSource() != null) {
            findAndPerformAction("安装");
            findAndPerformAction("下一步");
            findAndPerformAction("完成");
        }
    }

    @Override
    public void onInterrupt() {
    }
}
