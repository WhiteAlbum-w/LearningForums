package com.xiayang.learningforums.bean;

import java.util.List;

/**
 * Date: 2020/2/17
 * Author: aaronzzxup@gmail.com
 */
public final class Article {

    /**
     * apkLink :
     * audit : 1
     * author : xiaoyang
     * canEdit : false
     * chapterId : 440
     * chapterName : 官方
     * collect : false
     * courseId : 13
     * desc : <p>单例的实现有很多种，面对：</p>
     * <p>“枚举底层是如何实现的呢？”</p>
     * <p>如何解答呢？</p>
     *
     * <p>开工大吉，不能再刷微博了，该学习啦！今天正式网站、公众号都开始更新。</p>
     * descMd :
     * envelopePic :
     * fresh : false
     * id : 11689
     * link : https://www.wanandroid.com/wenda/show/11689
     * niceDate : 2020-02-02 17:21
     * niceShareDate : 2020-02-02 17:21
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1580635275000
     * selfVisible : 0
     * shareDate : 1580635265000
     * shareUser :
     * superChapterId : 440
     * superChapterName : 问答
     * tags : [{"name":"问答","url":"/article/list/0?cid=440"}]
     * title : 每日一问  很多时候我们说单例实现方式会提到「枚举实现」，那么枚举底层是如何实现的呢？
     * type : 1
     * userId : 2
     * visible : 1
     * zan : 1
     */
    public String apkLink;
    public int audit;
    public String author;
    public boolean canEdit;
    public int chapterId;
    public String chapterName;
    public boolean collect;
    public int courseId;
    public String desc;
    public String descMd;
    public String envelopePic;
    public boolean fresh;
    public int id;
    public String link;
    public String niceDate;
    public String niceShareDate;
    public String origin;
    public String prefix;
    public String projectLink;
    public long publishTime;
    public int selfVisible;
    public long shareDate;
    public String shareUser;
    public int superChapterId;
    public String superChapterName;
    public String title;
    public int type;
    public int userId;
    public int visible;
    public int zan;
    public List<Tags> tags;

    public class Tags {

        /**
         * name : 公众号
         * url : /wxarticle/list/408/1
         */
        public String name;
        public String url;
    }
}
