package com.xiayang.learningforums.bean;

import java.util.List;

/**
 * desc   : 积分排行榜
 * <p>
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/4/8
 */
public final class Rank {

    /**
     * curPage : 1
     * datas : [{"coinCount":11847,"level":119,"rank":1,"userId":20382,"username":"g**eii"},{"coinCount":10992,"level":110,"rank":2,"userId":3559,"username":"A**ilEyon"},{"coinCount":9379,"level":94,"rank":3,"userId":29303,"username":"深**士"},{"coinCount":8506,"level":86,"rank":4,"userId":2,"username":"x**oyang"},{"coinCount":7918,"level":80,"rank":5,"userId":27535,"username":"1**08491840"},{"coinCount":6803,"level":69,"rank":6,"userId":28694,"username":"c**ng0218"},{"coinCount":6552,"level":66,"rank":7,"userId":3753,"username":"S**phenCurry"},{"coinCount":6325,"level":64,"rank":8,"userId":29185,"username":"轻**宇"},{"coinCount":6281,"level":63,"rank":9,"userId":12467,"username":"c**yie"},{"coinCount":6276,"level":63,"rank":10,"userId":1534,"username":"j**gbin"},{"coinCount":6269,"level":63,"rank":11,"userId":28607,"username":"S**Brother"},{"coinCount":6266,"level":63,"rank":12,"userId":9621,"username":"S**24n"},{"coinCount":6225,"level":63,"rank":13,"userId":863,"username":"m**qitian"},{"coinCount":6200,"level":62,"rank":14,"userId":7891,"username":"h**zkp"},{"coinCount":6182,"level":62,"rank":15,"userId":7809,"username":"1**23822235"},{"coinCount":6152,"level":62,"rank":16,"userId":1260,"username":"于**家的吴蜀黍"},{"coinCount":6135,"level":62,"rank":17,"userId":27,"username":"y**ochoo"},{"coinCount":6125,"level":62,"rank":18,"userId":14829,"username":"l**changwen"},{"coinCount":6120,"level":62,"rank":19,"userId":7710,"username":"i**Cola7"},{"coinCount":6111,"level":62,"rank":20,"userId":833,"username":"w**lwaywang6"},{"coinCount":6045,"level":61,"rank":21,"userId":12351,"username":"w**igeny"},{"coinCount":6017,"level":61,"rank":22,"userId":26707,"username":"p**xc.com"},{"coinCount":5990,"level":60,"rank":23,"userId":25793,"username":"F**_2014"},{"coinCount":5964,"level":60,"rank":24,"userId":6095,"username":"W**derfulMtf"},{"coinCount":5961,"level":60,"rank":25,"userId":2068,"username":"i**Cola"},{"coinCount":5939,"level":60,"rank":26,"userId":12331,"username":"R**kieJay"},{"coinCount":5939,"level":60,"rank":27,"userId":29076,"username":"f**ham"},{"coinCount":5870,"level":59,"rank":28,"userId":25419,"username":"蔡**打篮球"},{"coinCount":5839,"level":59,"rank":29,"userId":7590,"username":"陈**啦啦啦"},{"coinCount":5829,"level":59,"rank":30,"userId":2160,"username":"R**iner"}]
     * offset : 0
     * over : false
     * pageCount : 1031
     * size : 30
     * total : 30916
     */

    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<RankList> datas;

}
