package com.yongf.android.contactsapp.util;

import com.yongf.android.contactsapp.R;
import com.yongf.android.contactsapp.domain.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
public class ContactsHelper {

    /**
     * mock 联系人数据
     *
     * @return
     */
    public static List<ContactInfo> mockData() {
        List<ContactInfo> infos = new ArrayList<>();

        ContactInfo info1 = new ContactInfo();
        info1.setAvatarResId(R.drawable.app_marvel1);
        info1.setDesc("《钢铁侠》（Iron Man）是由美国漫威电影工作室出品的一部科幻冒险电影，改编自同名系列漫画，由乔恩·费儒执导，小罗伯特·唐尼及格温妮斯·帕特洛、杰夫·布里吉斯等主演。该作也是“漫威电影宇宙”系列的首部电影。该片于2008年5月2日在美国上映。");
        info1.setFirstName("Iron");
        info1.setLastName("Man");
        info1.setTitle("SUPERHERO");
        infos.add(info1);

        ContactInfo info2 = new ContactInfo();
        info2.setAvatarResId(R.drawable.app_marvel2);
        info2.setDesc("《雷神》（Thor）是漫威影业出品的一部科幻动作电影，取材自漫威漫画，是漫威电影宇宙的第四部电影。由肯尼思·布拉纳执导，克里斯·海姆斯沃斯，汤姆·希德勒斯顿，娜塔丽·波特曼等主演。");
        info2.setFirstName("Thor");
        info2.setLastName("Thor");
        info2.setTitle("SUPERHERO");
        infos.add(info2);

        ContactInfo info3 = new ContactInfo();
        info3.setAvatarResId(R.drawable.app_marvel3);
        info3.setDesc("卡魔拉·贞·忽贝莉·本·泰坦（Gamora Zen Whoberi Ben Titan）是美国漫威漫画旗下超级英雄，初次登场于银河护卫队一，简称卡魔拉（Gamora），是泽侯贝里族的最后一人、灭霸的养女，善用武器是手上的双刀。");
        info3.setFirstName("Gamora");
        info3.setLastName("Zen");
        info3.setTitle("SUPERHERO");
        infos.add(info3);

        ContactInfo info4 = new ContactInfo();
        info4.setAvatarResId(R.drawable.app_marvel4);
        info4.setDesc("《蜘蛛侠：英雄归来》（Spider-Man:Homecoming）是2017年漫威影业与哥伦比亚影业联合出品，索尼电影娱乐公司发行的一部科幻动作电影，由乔恩·沃茨执导，汤姆·赫兰德、迈克尔·基顿、小罗伯特·唐尼、玛丽莎·托梅、雅各·巴塔伦、乔恩·费儒、劳拉·海瑞尔、赞达亚·科尔曼等主演。");
        info4.setFirstName("Spider");
        info4.setLastName("Man");
        info4.setTitle("SUPERHERO");
        infos.add(info4);

        ContactInfo info5 = new ContactInfo();
        info5.setAvatarResId(R.drawable.app_marvel5);
        info5.setDesc("本名娜塔莎·罗曼诺夫，原是秘密组织神盾局的一名俄国籍女探员，体态婀娜面容姣好，头脑清晰行动敏捷，身手不凡强硬凶狠，是神盾局的精英特工。娜塔莎最初被安排在斯塔克工业内部作为卧底，负责监视钢铁侠托尼·斯塔克，后正式加入复仇者联盟。");
        info5.setFirstName("Black");
        info5.setLastName("Widow");
        info5.setTitle("SUPERHERO");
        infos.add(info5);

        ContactInfo info6 = new ContactInfo();
        info6.setAvatarResId(R.drawable.app_marvel6);
        info6.setDesc("火箭浣熊（Rocket Raccoon）是美国漫威漫画旗下超级英雄，初次登场于《Marvel Preview》第7期（1976年6月），他的名字及性格来自披头士乐队1968年的歌曲《Rocky Raccoon》。");
        info6.setFirstName("Rocket");
        info6.setLastName("Raccoon");
        info6.setTitle("SUPERHERO");
        infos.add(info6);

        ContactInfo info7 = new ContactInfo();
        info7.setAvatarResId(R.drawable.app_marvel7);
        info7.setDesc("格鲁特（Groot）是美国漫威漫画旗下超级英雄，初次登场于《惊奇故事》第13期（1960年11月），由斯坦·李、杰克·科比和迪克·埃尔斯联合创造。");
        info7.setFirstName("Groot");
        info7.setLastName("");
        info7.setTitle("SUPERHERO");
        infos.add(info7);

        ContactInfo info8 = new ContactInfo();
        info8.setAvatarResId(R.drawable.app_marvel8);
        info8.setDesc("《奇异博士》是由美国漫威影业制作，迪士尼影业公司出品的奇幻动作电影，由斯科特·德瑞克森执导,，本尼迪克特·康伯巴奇、蒂尔达·斯文顿、切瓦特·埃加福特、瑞秋·麦克亚当斯、麦斯·米科尔森、本尼迪特·王联合主演。");
        info8.setFirstName("Doctor");
        info8.setLastName("Strange");
        info8.setTitle("SUPERHERO");
        infos.add(info8);

        return infos;
    }
}
