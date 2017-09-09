package com.prcymy.ymy.ec.main.sort.content;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class Bean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * cat_id : 15
         * cat_name : 男装
         */

        private int cat_id;
        private String cat_name;

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }
    }
}
